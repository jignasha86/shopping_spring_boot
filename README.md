# shopping_spring_boot

## Installation Steps

### Software Dependencies

-> Java 8

-> Maven 3.6+

-> Spring Boot Release 2.2.7

-> Spring Tool Suite 4

-> In-memory H2 Database (Not required separate install)

### Configure & Run application

-> git clone shopping_spring_boot

-> Import Maven modular project in STS IDE or deploy jar separately on Web server 

-> Start modular application from STS IDE

### Testing application

-> Open **http://localhost:8080/swagger-ui.html** from browser for centralized api documentation

-> Microservices found on eureka service discovery **http://localhost:8761** 

-> product-service, cart-service, checkout-service rest api services

-> api gateway used for common authentication and filtering requests accesible through api gateway on port 8080

-> okta authentication used for sending authorized requests

-> Appliction code thread safe as spring boot classes and inside code Syncronized and used transactional repositories

-> Through load testing application use case of race condition can be tested

### API endpoints

-> **http://localhost:8080/product-service/products**

-> **http://localhost:8080/cart-service/add_cart**

-> **http://localhost:8080/checkout-service/checkout**


### Scaling application

-> Spring Cloud based orchestarion can make application scale and multiple of service instances can exists on same
   or different machine through centralized service discovery
   
-> Spring Cloud Config can be used for common code configuration

-> Inter service communication through feign or ribbon 
