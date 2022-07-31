package com.wellsfargo.pad.iccbatch.config;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

    @Configuration
    @EnableTransactionManagement
    @EnableJpaRepositories(
            entityManagerFactoryRef = "odsEntityManagerFactory",
            transactionManagerRef = "odsTransactionManager",
            basePackages = { "com.wellsfargo.pad.iccbatch.repo" }
    )
    public class ODSDataSource{

        @Bean(name="odsDataSource")
        @Primary
        @ConfigurationProperties(prefix="spring.datasource")
        public DataSource odsDataSource() {
            return DataSourceBuilder.create().build();
        }

        @Primary
        @Bean(name = "odsEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean odsEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                                  @Qualifier("odsDataSource") DataSource odsDataSource) {
            return builder
                    .dataSource(odsDataSource)
                    .packages("com.wellsfargo.pad.iccbatch.domain")
                    .build();
        }

        @Bean(name = "odsTransactionManager")
        public PlatformTransactionManager odsTransactionManager(
                @Qualifier("odsEntityManagerFactory") EntityManagerFactory odsEntityManagerFactory) {
            return new JpaTransactionManager(odsEntityManagerFactory);
        }
    }
