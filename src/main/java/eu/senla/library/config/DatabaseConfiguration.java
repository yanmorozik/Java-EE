package eu.senla.library.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:hibernate.properties")
public class DatabaseConfiguration {

    @Value(value = "${hibernate.connection.url}")
    private String url;

    @Value(value = "${hibernate.connection.username}")
    private String username;

    @Value(value = "${hibernate.connection.password}")
    private String password;

    @Value("#{${database.hibernate}}")
    private Map<String, String> hibernateAdditionalProperties;


//    @Value("${hibernate.dialect}")
//    private String dialect;
//
//    @Value("${hibernate.ejb.naming_strategy}")
//    private String namingStrategy;
//
//    @Value("${hibernate.show_sql}")
//    private String showSql;
//
//    @Value("${hibernate.format_sql}")
//    private String formatSql;
//
//    @Value("${hibernate.hbm2ddl.auto}")
//    private String validation;
//
    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource(url, username, password);
    }

    @Bean
    public TransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public FactoryBean<EntityManagerFactory> entityManagerFactory(DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setPackagesToScan("eu.senla.library.model");
        localContainerEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(hibernateAdditionalProperties);
        return localContainerEntityManagerFactoryBean;
    }

//@Bean
//public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//    LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//    entityManagerFactory.setDataSource(dataSource);
//    entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//    entityManagerFactory.setPackagesToScan("eu.senla.library.model");
//    entityManagerFactory.setJpaProperties(getJpaProperties());
//
//    return entityManagerFactory;
//}
//
//    @Bean
//    public TransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//        return transactionManager;
//    }
//
//    private Properties getJpaProperties() {
//        Properties jpaProperties = new Properties();
//
//        // Configures the used database dialect. This allows Hibernate to create SQL
//        // that is optimized for the used database.
//        jpaProperties.put("hibernate.dialect", dialect);
//
//        // Configures the naming strategy that is used when Hibernate creates
//        // new database objects and schema elements
//        jpaProperties.put("hibernate.ejb.naming_strategy", namingStrategy);
//
//        // If the value of this property is true, Hibernate writes all SQL
//        // statements to the console.
//        jpaProperties.put("hibernate.show_sql", showSql);
//
//        // If the value of this property is true, Hibernate will format the SQL
//        // that is written to the console.
//        jpaProperties.put("hibernate.format_sql", formatSql);
//
//        jpaProperties.put("hibernate.hbm2ddl.auto", validation);
//
//        return jpaProperties;
//    }
}
