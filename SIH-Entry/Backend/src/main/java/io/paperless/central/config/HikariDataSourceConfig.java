package io.paperless.central.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Configuration
public class HikariDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    @Primary
    public DataSource hikariDataSource() {
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }
}

