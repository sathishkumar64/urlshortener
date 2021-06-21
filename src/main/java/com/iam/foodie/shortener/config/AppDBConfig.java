package com.iam.foodie.shortener.config;


import com.iam.foodie.shortener.util.DataBaseProperties;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableReactiveMongoRepositories(basePackages = {"com.iam.foodie.shortener.dao.repositories"})
@EnableTransactionManagement
//@RefreshScope
@Slf4j
public class AppDBConfig extends AbstractReactiveMongoConfiguration {

    @Autowired
    private DataBaseProperties dataBaseProperties;

    @Override
    protected String getDatabaseName() {
        return dataBaseProperties.getDatabaseName();
    }

    protected String getMappingBasePackage() {
        return "com.iam.foodie.shortener.dao.model";
    }

    public MongoClient reactiveMongoClient() {
        log.debug("Database config.......{},{}", dataBaseProperties.getDatabaseName(), dataBaseProperties.getDatabaseUrl());
        return MongoClients.create(dataBaseProperties.getDatabaseUrl());
    }

    @Bean
    ReactiveMongoTransactionManager transactionManager(ReactiveMongoDatabaseFactory factory) {
        return new ReactiveMongoTransactionManager(factory);
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}

