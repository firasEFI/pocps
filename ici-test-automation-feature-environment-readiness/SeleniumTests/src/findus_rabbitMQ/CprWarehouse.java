package findus_rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;


/**
 * Created by nielsjes on 05-09-2017.
 */
public class CprWarehouse {

    public static Preferences prefs = Preferences.userRoot().node("currentCpr");

    public String getStandardCprnumber() throws IOException, TimeoutException {


        String currentCpr = prefs.get("currentCpr", "");


        if(currentCpr.equals("")){
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("192.168.195.59");
            factory.setPort(5672);
            factory.setUsername("newadmin");
            factory.setPassword("visma123");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            String queue = "standardCPR";

            // exchange
            GetResponse response = channel.basicGet(queue, false);
            if(response != null) {
                channel.basicAck(response.getEnvelope().getDeliveryTag(), false);
            }

            System.out.println("\nUsing new CPR number: " + new String(response.getBody()));
            prefs.put("currentCpr", new String(response.getBody()));
            channel.close();
            connection.close();
            return new String(response.getBody());
        }
        else{
            System.out.println("\nUsing same CPR number: " + currentCpr);
            return currentCpr;
        }



    }
}
