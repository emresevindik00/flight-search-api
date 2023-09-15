# Flight Search API To Get Information for Flights and Airports
___
### Spring Boot Application with Rest Api

---
This project provides to create account for existing users.

### Summary
The assessment consists of an API to be used for opening a new airport and flight.

___
The application has 3 apis
* FlightAPI
* AirportAPI
* AuthenticationAPI
  
```html
POST /api/airports - creates a new airport
GET /api/airports/{airportId} - retrieves an airport
PUT /api/airports - update airport
DELETE /api/airports/{airportId} - delete an airport
```

```html
POST /api/flights - creates a new flight
GET /api/flights/{flightId} - retrieves an flight
PUT /api/flights - update flight
DELETE /api/flights/{flightId} - delete an flight
GET /api/search - search an flight
```

```html
POST /api/auth/register - register an user
POST /api/auth/authenticate - authenticate an user or admin
POST /api/auth/refresh-token - refresh token
```

### Tech Stack

---
- Java 13
- Spring Boot
- Spring Data JPA
- Restful API
- OpenAPI documentation
- PostgreSql database
- Spring Security
- Scheduling
- Hibernate

### Prerequisites

---
- Maven

### Run & Build

---
Run & build the application.


#### Maven

For maven usage, you need to change `proxy` value in the `account-fe/package.json`
file by `"http://localhost:8080"` due to the default value has been settled for docker image network proxy.
___
*$PORT: 8080*
```ssh
$ cd airport/airport-api
$ mvn clean install
$ mvn spring-boot:run

$ cd airport/airport-fe
$ npm install
$ npm start
```

### Swagger UI will be run on this url
`http://localhost:${PORT}/swagger-ui.html`
