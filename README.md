# Online Shopping Cart Project for XYZ Startup

#### The application architecture influenced with microservice architecture with Api Gateway and Auto Discovery of Services.
#### The application has below micro-services
1. product-service
2. analytics-service
3. customer-service
4. order-service
5. shopping-api-gateway
6. shopping-autodiscovery-server

#### 1. product-service: This micro-service contains services related to the product doamin like searching products, getting product details etc.

#### 2. customer-service: This micro-service contains services related to customer domian. 

#### 3. order-server: This micro-service contains services related to order domain.

#### 4. analytics-service: This micro-service contains services to log the customer activity like what product details viewed by customer, customer are searching for which products etc.

#### 5. shopping-autodiscovery-server: This micro-service spin up a Eureka Auto Discovery server where each microservices registered.

#### 6. shopping-api-gateway: This microservice works a api gateway for all the microservices.

## Folder Structure of each micro-service
#### Each micro-service has below folders with their significance
1. controller - this has the rest controller classes.
2. service - this has the service classes.
3. repository - this has JPA Repository classes to interact with the DB.
4. entity - this has JPA Entities.

## How to run locally the Project

#### 1. Spin up the Auto Discovery Server
    - Navigate to shopping-autodiscovery-server
    - run mvn clean install
    - run mvn spring-boot:run 

#### 2. Spin up the analytics-service
    - Navigate to analytics-service
    - run mvn clean install
    - run mvn spring-boot:run 

#### 3. Spin up the product-service
    - Navigate to product-service
    - run mvn clean install
    - run mvn spring-boot:run 
    
#### 4. Spin up the customer-service
    - Navigate to customer-service
    - run mvn clean install
    - run mvn spring-boot:run 

#### 5. Spin up the order-service
    - Navigate to order-service
    - run mvn clean install
    - run mvn spring-boot:run 

#### 6. Spin up the order-service
    - Navigate to order-service
    - run mvn clean install
    - run mvn spring-boot:run 
    
## How to test the backend services locally
##### Each micro-service has embedded swagger-ui for REST services to test, the below are URLs for each micro-service
1. product-service - http://localhost:9080/swagger-ui.html
2. analytics-service - http://localhost:9081/swagger-ui.html
3. order-service - http://localhost:9082/swagger-ui.html
4. customer-service - http://localhost:9084/swagger-ui.html