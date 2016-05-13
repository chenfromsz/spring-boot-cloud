package com.test.data.test;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = { "com.test.data.repositories" })
public class Neo4jConfig extends Neo4jConfiguration {
    @Override
    public Neo4jServer neo4jServer() {
        return new RemoteServer("http://192.168.1.221:7474","neo4j","12345678");
    }

    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory("com.test.data.domain");
    }
}
