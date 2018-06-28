package com.byhovsky.agency;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * ContextConfiguration this class defines all application config
 *
 * @author Denis Byhovsky
 */
@Configuration
@ComponentScan(basePackages = "com.byhovsky.agency")
@EnableAspectJAutoProxy(proxyTargetClass = true)

public class ContextConfiguration {

    @Bean
    public JdbcTemplate initJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @EnableTransactionManagement
    @Profile("production")
    @Configuration
    @PropertySource("classpath:jdbc.properties")
    public static class DataBaseConfigProfile {

        //To define current profile, give access to properties
        @Autowired
        private Environment env;

        /**
         * Setter for property 'env'.
         *
         * @param env Value to set for property 'env'.
         */
        public void setEnv(Environment env) {
            this.env = env;
        }

        //DataSource bean
        @Bean
        public ComboPooledDataSource dataSource() throws PropertyVetoException {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(env.getRequiredProperty("jdbc.driverClassName"));
            dataSource.setUser(env.getRequiredProperty("jdbc.username"));
            dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
            dataSource.setMaxPoolSize(Integer.valueOf(env.getRequiredProperty("jdbc.maxPoolSize")));
            dataSource.setMinPoolSize(Integer.valueOf(env.getRequiredProperty("jdbc.minPoolSize")));
            dataSource.setJdbcUrl(env.getRequiredProperty("jdbc.url"));
            dataSource.setMaxStatements(Integer.valueOf(env.getRequiredProperty("jdbc.maxStatements")));
            return dataSource;
        }
    }

    @Configuration
    @Profile("dev")
    public static class DataBaseDevConfigProfile {

        //Test dataSource
        @Bean
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .addScript("classpath:/schema.sql")
                    .addScript("classpath:/init.sql")
                    .build();
        }


    }
}