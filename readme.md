
# Application

This is a basic CRUD Application for Account Management.

## Swagger-UI 

You can access swagger-ui by landing on below web address after server startup.

```agsl
http://localhost:8080/swagger-ui/index.html
```

## Pre-requisites

Java (https://www.oracle.com/java/technologies/downloads)

Maven (https://maven.apache.org/download.cgi)

## Acknowledgements

This is a basic CRUD account management service for demo purpose.


## Tech Stack

**Client:** N/A

**Language:** Java

**DataBase:** H2 

**Plugins/Frameworks:** Spring Boot

### External Dependencies used

H2 DataBase

SpringDoc-OpenAPI-Starter-WebMVC-UI

Spring-Boot-Starter-Web

Spring-Boot-Starter-Data-JPA

Lombok

Spring-Boot-Starter-Validation

Spring-Boot-Starter-Test




## Authors

- [@gauravnewton](https://www.github.com/gauravnewton)



## Launching App

To deploy this just put the jar file inside tomcat deployment folder i.e. webapps

### To run and build separately

To build backend use below command

```bash
\account-management-service\target> mvn clean install
`````

Use below command to up service

```bash
\account-management-service\target> java -jar apartment-manager-0.0.1-SNAPSHOT.jar
```

### To build and run at once use below command

```bash
\account-management-service> mvn spring-boot:run
```


## 🚀 About Team ##
You can refer this web address to explore more about me
[here](https://github.com/gauravnewton).


## API Reference


### Create Account

Sample Request
```agsl
curl -X 'POST' \
  'http://localhost:8080/api/v1/account' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "Gaurav Kumar",
  "email": "gaurav.mute@gmail.com",
  "country": "US",
  "postalCode": "60661",
  "age": 22,
  "status": "REQUESTED"
}'
```

Sample Response

```agsl
{
  "accountId": "Bv3bAO",
  "status": "REQUESTED",
  "securityPIN": "2939"
}
```

## Get Account

Sample Request

```agsl
curl -X 'GET' \
  'http://localhost:8080/api/v1/account?accountId=Bv3bAO&email=gaurav.mute%40gmail.com' \
  -H 'accept: application/json'
```

Sample Response

```agsl
{
  "accountId": "Bv3bAO",
  "name": "Gaurav Kumar",
  "email": "gaurav.mute@gmail.com",
  "age": 22,
  "status": "REQUESTED",
  "securityPIN": "2939",
  "addresses": [
    {
      "id": 1,
      "country": "United States",
      "countryCode": "US",
      "postalCode": "60661",
      "state": "Illinois",
      "stateCode": "IL",
      "city": "Chicago",
      "latitude": "41.8814",
      "longitude": "-87.643",
      "createdAt": "2024-10-31T06:16:42.835+00:00",
      "updatedAt": null
    }
  ],
  "createdAt": "2024-10-31T06:16:42.835+00:00",
  "updatedAt": null
}
```

## Get Account Count

Sample Request

```agsl
curl -X 'GET' \
  'http://localhost:8080/api/v1/account/count' \
  -H 'accept: application/json'
```

Sample Response

```agsl
[
  {
    "country": "US",
    "count": 1,
    "states": [
      {
        "state": "IL",
        "count": 1,
        "places": [
          {
            "place": "Chicago",
            "count": 1
          }
        ]
      }
    ]
  }
]
```

## Update Account

Sample Request

```agsl
curl -X 'PUT' \
  'http://localhost:8080/api/v1/account/Bv3bAO?name=Gaurav%20Kumar&country=US&postalCode=60661&age=22&status=ACTIVE' \
  -H 'accept: application/json'
```

Sample Response

```agsl
{
  "status": "OK",
  "message": "Account updated successfully"
}
```

## Delete Account

Sample Request

```agsl
curl -X 'DELETE' \
  'http://localhost:8080/api/v1/account/delete/Bv3bAO/2939' \
  -H 'accept: application/json'
```

Sample Response

```agsl
{
  "status": "OK",
  "message": "Account deleted successfully"
}
```

