package io.dsub.dumpdbmgmt.config;

import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * Batch DataSource for MySQL.
 * IF you want, you may declare designated solution of DB here.
 */

@Configuration
@PropertySource(value = "classpath:application.properties")
public class DataSourceConfig {

    Environment env;

    public DataSourceConfig(Environment env) {
        this.env = env;
    }

    @BatchDataSource
    @Bean(name = "batchDataSource")
    public DataSource batchDataSource() {
        return DataSourceBuilder
                .create()
                .url(env.getProperty("batch.jdbc.url"))
                .driverClassName(env.getProperty("batch.jdbc.driver"))
                .username(env.getProperty("batch.jdbc.user"))
                .password(env.getProperty("batch.jdbc.pass"))
                .build();
    }
}
