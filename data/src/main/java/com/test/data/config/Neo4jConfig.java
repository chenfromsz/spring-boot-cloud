package com.test.data.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = { "com.test.data.repositories" })
@EnableConfigurationProperties(Neo4jSetting.class)
public class Neo4jConfig extends Neo4jConfiguration {
//SDN 升级到4.1.2，连接服务器的配置改在ogm.properties中设定，这样可以访问Neo4j 2.x 到 3.x 版本
//    @Autowired
//    private Neo4jSetting neo4jSetting;
//    @Override
//    public Neo4jServer neo4jServer() {
//        return new RemoteServer(neo4jSetting.getUrl(), neo4jSetting.getUsername(), neo4jSetting.getPassword());
//    }

    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory("com.test.data.domain");
    }
}
