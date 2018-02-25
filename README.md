# Product Catalogue Service

This is the repository for the microservices of Product Catalogue Service for GloboMart. 
The entire architecture is chosen is microservice based where each service will have the API corresponding to different functionalities.
All the mircoservices are restful and orchestrated at a separate layer which is based on node.js.
Each service has it's own db which will be no-sql db that stores data in json format and for fast rerieval of data redis in-memory key value pair db will be used for caching purposes.


## Setup

Make sure you have Maven. If you don't go to the [Maven website](https://maven.apache.org/index.html) and follow the direction to download and install Maven.
Make sure you have redis server running on your localhost [for in-momeory database] . If you don't have redis please download it from (https://github.com/ServiceStack/redis-windows) for windows follow the steps to run the redis server on localhost

## Build

To build the code run the following command in the root project directory:

```
mvn clean verify
```

Once the build completes you can deploy the war in any server

Or

Install tomcat server and run the following command to deploy on tomcat localhost

```
tomcat:run
```


#APIs -

###	Add a product
POST /api/product
sample input to this i.e product catalouge document structure is -
```
{
 "productName" : "scent", 
 "productId" : "12345", // primary key
 "productType" : "cosmetics",
 "productPrice" : "20"
}
```

###	Retrieve multiple products based on productType
GET /api/product/product-type/{product-type}

###	Retrieve a product based on productId
GET /api/product/product-id/{product-id}

### Delete a product
DELETE /api/product/product-id/{product-id}