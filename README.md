# Service-Diabetes

**REST API** used to generate a diabetes predisposition report. <br>
In such, the application provides an endpoint which will generate the report.

For more information about the available endpoint, feel free to checkout the swagger's documentation.


## Stack

- **Java 15** *(should run smoothly on older versions)*
- **Spring Boot 2.4.6**
- **Spring Boot AOP 2.4.6**
- **Spring Boot Test**
- **JaCoCo 0.8.6**
- **Swagger 3.0.0**
- **Gradle 6.8.3**


## Installation

The application is quite easy to install; it only needs to be imported in your preferred IDE as a gradle project. <br>

**Listen on port: 8083**


## Test

The application is entirely tested and reach a **90%** + code coverage. <br>
You can do end-to-end tests by doing HTTP request with the differents endpoints provided, f.e with Postman. <br>
If you wish to add or tweak some tests, you can find them under the traditional **src/test/java** package. <br>


## Logs

The application is logged by making use of the **Aspect Oriented Programming**. <br>
*LoggingAspect* is the class using **AOP** which define the logging. <br>


## Documentation

The application uses Swagger2 to build up the documentation. <br>
To access the documentation, run the application and reach those links: <br>
[UI](http://localhost:8083/swagger-ui/) | [JSON](http://localhost:8083/v2/api-docs)



