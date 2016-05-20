# Config Server
curl -X POST http://localhost:8888/bus/refresh
curl -X POST http://localhost:8888/bus/refresh?destination=web:**
curl -X POST http://localhost:9006/refresh

# Discovery
http://localhost:8761

# Hystrix Dashboard
http://localhost:7979
http://localhost:9001/hystrix.stream