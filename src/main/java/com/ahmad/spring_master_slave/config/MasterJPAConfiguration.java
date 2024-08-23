package com.ahmad.spring_master_slave.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.ahmad.spring_master_slave.writeRepository",
        entityManagerFactoryRef = "masterEntityManagerFactoryBean",
        transactionManagerRef = "masterTransactionManager"
)
public class MasterJPAConfiguration {
    @Bean
    public LocalContainerEntityManagerFactoryBean masterEntityManagerFactoryBean(EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("masterDataSource") DataSource masterDataSource) {
        return entityManagerFactoryBuilder
                .dataSource(masterDataSource)
                .packages("com.ahmad.spring_master_slave.entity")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager masterTransactionManager(@Qualifier("masterEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
    }
}
