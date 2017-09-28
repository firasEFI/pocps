package dk.rim.is.abt.integration.jms;

import com.ibm.jms.JMSMessage;
import com.ibm.mq.jms.MQConnection;
import com.ibm.mq.jms.MQMessageProducer;
import com.ibm.mq.jms.MQQueue;
import com.ibm.mq.jms.MQSession;
import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;

/**
 * @author Radoslaw Domanski (rdo)
 * @since 31.01.2017
 */
public class JmsConnector {
    private static final Logger LOG = LoggerFactory.getLogger(JmsConnector.class);

    private final ConnectionFactory connectionFactory;
    private final String queueName;

    public JmsConnector(String queueName, String host, String port, String chanelName, String queueManagerName) throws JMSException {
        this(queueName, createConnectionFactory(host, parseInt(port), chanelName, queueManagerName));
    }

    public JmsConnector(String queueName, ConnectionFactory connectionFactory) {
        this.queueName = queueName;
        this.connectionFactory = connectionFactory;
    }

    private static ConnectionFactory createConnectionFactory(String host, int port, String chanelName, String queueManagerName) throws JMSException {
        JmsFactoryFactory jmsFactory = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);

        JmsConnectionFactory connectionFactory = jmsFactory.createConnectionFactory();
        connectionFactory.setStringProperty(WMQConstants.WMQ_HOST_NAME, host);
        connectionFactory.setIntProperty(WMQConstants.WMQ_PORT, port);
        connectionFactory.setStringProperty(WMQConstants.WMQ_CHANNEL, chanelName);
        connectionFactory.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
        connectionFactory.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "INTEGRATION_COMPONENT");
        connectionFactory.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, queueManagerName);

        return connectionFactory;
    }

    public List<String> readAllMessages() throws JMSException {
        List<String> messageIds = readQueue();
        LOG.info("messages {} {}", messageIds.size(), messageIds);

        return messageIds;
    }

    private List<String> readQueue() throws JMSException {
        List<String> messageIds = new LinkedList<>();
        try (Connection connection = connectionFactory.createConnection();
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {
            Queue destination = session.createQueue(queueName);
            try (QueueBrowser queueBrowser = session.createBrowser(destination)) {
                Enumeration enumeration = queueBrowser.getEnumeration();
                int nr = 0;
                while (enumeration.hasMoreElements()) {
                    JMSMessage textMessage = (JMSMessage) enumeration.nextElement();
                    String jmsMessageID = textMessage.getJMSMessageID();
                    LOG.info("messsage {} {} \n{} \n{}", ++nr, jmsMessageID, getMessageContent(textMessage), textMessage);
                    LOG.info("------------------- ");
                    messageIds.add(jmsMessageID);
                }
            }
        }
        return messageIds;
    }

    private String getMessageContent(JMSMessage jmsMessage) throws JMSException {
        if (jmsMessage instanceof TextMessage) {
            return ((TextMessage) jmsMessage).getText();
        } else if (jmsMessage instanceof BytesMessage) {
            BytesMessage msg = ((BytesMessage) jmsMessage);
            byte[] data = new byte[(int) msg.getBodyLength()];
            msg.readBytes(data);
            return new String(data, StandardCharsets.UTF_8);
        } else {
            throw new RuntimeException("Unknown messageType " + jmsMessage);
        }
    }

    public String sendMessage(String messageText) throws JMSException {
        String msgId;

        try (MQConnection connection = (MQConnection) connectionFactory.createConnection();
             MQSession session = (MQSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {
            MQQueue destination = (MQQueue) session.createQueue(queueName);

            destination.setMessageBodyStyle(WMQConstants.WMQ_MESSAGE_BODY_MQ);
            destination.setCCSID(1208);
            destination.setEncoding(546);

            try (MQMessageProducer producer = (MQMessageProducer) session.createProducer(destination)) {
                TextMessage message = session.createTextMessage(messageText);

                connection.start();
                producer.send(message);
                LOG.info("Message sent: " + message);

                msgId = message.getJMSMessageID();
                LOG.info("messageId: " + msgId);
            }
        }

        return msgId;
    }

    public void consumeAllMessages() throws JMSException {
        LOG.info("Consuming all messages that were in the queue. Queue name: {}", queueName);
        List<String> messageIds = readQueue();
        for (String messageId : messageIds) {
            consumeMessage(messageId);
        }
    }

    public void consumeMessage(String messageId) throws JMSException {
        Objects.requireNonNull(messageId, "MessageId is null");
        try (Connection connection = connectionFactory.createConnection();
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {
            Queue destination = session.createQueue(queueName);
            connection.start();

            String messageSelector = String.format("JMSMessageID = '%s'", messageId);
            try (MessageConsumer messageConsumer = session.createConsumer(destination, messageSelector)) {
                JMSMessage message = (JMSMessage) messageConsumer.receiveNoWait();
                LOG.info("consumed messsage {}", message);
            }
        }
    }
}
