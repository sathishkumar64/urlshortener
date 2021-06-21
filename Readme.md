### Shorten URL - API

#### DataBase : MongoDB 

#### Build Application
    -   mvn clean install
#### Run Application
    - mvn spring-boot:run
#### Swagger URL
    -   http://localhost:8082/webjars/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

##### POST API

localhost:8082/api/v1/shorten
{
"userId":"60b0fbe0fba0fc065aef3864",
"url" :"https://google.com/api/v1/vendors/address?vendorId=60a291527ffead7557478c5d"

}

######  GET ALL

localhost:8082/api/v1/shorten?userId=60b0fbe0fba0fc065aef3864

###### DELETE

localhost:8082/api/v1/shorten/60ce0ed296ea2924c006ccd5

######  GET and Redirect

localhost:8082/api/v1/shorten/-430300183