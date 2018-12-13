Tools used - Eureka Server, Feign, Ribbon

1) Check application properties of all services to get the default port numbers
2) Run eureka-naming-server
3) Run other apps
4) Use postman to verify results
5) Try running more than one instance of forex-service by creating new run configs with vm argument -> -Dserver.port=8001