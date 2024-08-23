package com.ahmad.spring_master_slave.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.ahmad.spring_master_slave.readRepository",
        entityManagerFactoryRef = "replicaEntityManagerFactoryBean",
        transactionManagerRef = "replicaTransactionManager"
)
public class ReplicaJPAConfiguration {
    @Bean
    public LocalContainerEntityManagerFactoryBean replicaEntityManagerFactoryBean(EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("replicaDataSource") DataSource masterDataSource) {
        return entityManagerFactoryBuilder
                .dataSource(masterDataSource)
                .packages("com.ahmad.spring_master_slave.entity")
                .build();
    }

    @Bean
    public PlatformTransactionManager replicaTransactionManager(@Qualifier("replicaEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
    }
}
