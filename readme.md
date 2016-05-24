# Init Config And Package
1. data: set neo4j url username password in application.yml and com.test.data.test.Neo4jConfig
2. mvn clean package

# Run
in module directory:
mvn spring-boot:run
or java -jar  *.jar

# Web
http://localhost:9001

# ConfigServer Refresh
Refresh All:
curl -X POST http://localhost:8888/bus/refresh

Refresh Dedtination:
curl -X POST http://localhost:8888/bus/refresh?destination=web:**

Refresh One:
curl -X POST http://localhost:9001/refresh

# Discovery
http://localhost:8761

# Hystrix Dashboard
http://localhost:7979

moniter:
http://localhost:9001/hystrix.stream
