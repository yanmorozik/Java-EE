package eu.senla.library.config;

import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource("classpath:liquibase/database.properties")
@EnableJpaRepositories({"eu.senla.library.repository"})
public class DatabaseH2Configuration {

    @Value(value = "${jdbc.url}")
    private String url;

    @Value(value = "${jdbc.user}")
    private String username;

    @Value(value = "${jdbc.pass}")
    private String password;

    @Value(value = "${jdbc.driverClassName}")
    private String driver;

    @Value(value = "${liquibase.changeLog}")
    private String changeLog;

    @Value("${hibernate.dialect}")
    private String dialect;

//    @Value("#{${database.hibernate.test}}")
//    private Map<String, String> hibernateAdditionalProperties;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource(url, username, password);
        driverManagerDataSource.setDriverClassName(driver);
        return driverManagerDataSource;
    }

    @Bean
    public TransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public FactoryBean<EntityManagerFactory> entityManagerFactory(DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setPackagesToScan("eu.senla.library.model");
        localContainerEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setJpaProperties(getJpaProperties());
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setChangeLog(changeLog);
        springLiquibase.setDataSource(dataSource);
        return springLiquibase;
    }

    private Properties getJpaProperties() {
        Properties jpaProperties = new Properties();

        jpaProperties.put("hibernate.dialect", dialect);

        return jpaProperties;
    }
}
