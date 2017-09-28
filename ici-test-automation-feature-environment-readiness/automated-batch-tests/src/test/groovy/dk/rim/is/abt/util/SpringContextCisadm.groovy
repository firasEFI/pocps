package dk.rim.is.abt.util

import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

/**
 * @author Radoslaw Domanski (rdo)
 * @since 26.01.2017
 */
public class SpringContextCisadm {
    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/application-contextCISADM.xml");

    public static ApplicationContext context() {
        return context;
    }

    @SuppressWarnings("unchecked")
    public static <T> GenericDao<T> buildDao(Class<T> clazz) {
        GenericDao bean = context.getBean(GenericDao.class);
        bean.setType(clazz);
        return bean;
    }

    public static <T> T resolve(Class<T> clazz) {
        return context.getBean(clazz);
    }
}
