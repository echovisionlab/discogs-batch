package io.dsub.dumpdbmgmt.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.persistence.ValidationMode;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Java-based configuration for batch process report.
 * Note that TaskExecutor is shared from here and the ones for job itself.
 */

@Configuration
@PropertySource(value = "classpath:application.properties")
public class AppConfig {

    Environment env;

    public AppConfig(Environment env) {
        this.env = env;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("batchDataSource") DataSource ds,
            @Qualifier("batchTaskExecutor") ThreadPoolTaskExecutor taskExecutor) {

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean emf;

        emf = builder
                .dataSource(ds)
                .packages("io.dsub.dumpdbmgmt")
                .persistenceUnit("batchPU")
                .build();

        emf.setBootstrapExecutor(taskExecutor);
        emf.setValidationMode(ValidationMode.AUTO);
        emf.setJpaVendorAdapter(vendorAdapter);
        Properties prop = new Properties();
        prop.put("hibernate.hbm2ddl.auto", "update");
        prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        prop.put("hibernate.show_sql", "false");
        prop.put("hibernate.event.merge.entity_copy_observer", "allow");
        prop.put("hibernate.order_inserts", "true");
        emf.setJpaProperties(prop);
        return emf;
    }

    @Primary
    @Bean(value = "jpaTransactionManager")
    public JpaTransactionManager transactionManager(
            @Qualifier(value = "entityManagerFactory") LocalContainerEntityManagerFactoryBean emf,
            @Qualifier(value = "batchDataSource") DataSource dataSource) {
        JpaTransactionManager manager = new JpaTransactionManager();
        emf.setValidationMode(ValidationMode.CALLBACK);
        manager.setPersistenceUnitName("jpaTransactionPU");
        manager.setDataSource(dataSource);
        manager.setTransactionSynchronization(0);
        manager.setEntityManagerFactory(emf.getObject());
        manager.setValidateExistingTransaction(true);
        manager.setNestedTransactionAllowed(true);
        return manager;
    }
}
