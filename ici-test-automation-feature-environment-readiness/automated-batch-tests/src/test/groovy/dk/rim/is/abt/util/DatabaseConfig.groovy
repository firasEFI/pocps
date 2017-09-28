package dk.rim.is.abt.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Radoslaw Domanski (rdo)
 * @since 26.01.2017
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean bean = new LocalEntityManagerFactoryBean();
        bean.setPersistenceUnitName("test-jpa");
        return bean;
    }
}
