# Gift Card Challenge
This service solves the gift card challenge 


## Getting Started
* Build project with:
  * mvn clean package
* Run project with:  
  * mvn spring-boot:run
* Find Pair
  * curl -F file=@"src/test/resources/products.csv" 'http://ec2-54-189-131-2.us-west-2.compute.amazonaws.com:8080/demo/findPair/2400'
* Swagger is available at http://ec2-54-189-131-2.us-west-2.compute.amazonaws.com:8080/swagger-ui.html 


## Built With
* [SpringBoot](https://spring.io/projects/spring-boot) 
* [Maven](https://maven.apache.org/)
