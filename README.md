# S4 (Super Simple Scheduling System)

This is a REST API to manage Students and Classes. The project has been implemented using the following frameworks/libraries:

* Spring Boot.
* Spring Data REST.
* Spring Data JPA.
* H2 Database Engine. (In memory database)

## Prerequisites

Is needed to have installed:

* Java 1.8
* Apache Maven 3.2.1 or newer.

## Packaging

Run the following command to build and package the tool.

```
mvn clean package
```

## Running

Run the following command to start the server.
```
java -jar target/s4-0.1.0.jar
```

## Usage
All the endpoints provides HATEOAS navigation.

### Students
It provides CRUD functionality for its management. The main entry point is:
```
GET http://localhost:8080/v1/students
```

### Classes
It provides CRUD functionality for its management. The main entry point is:
```
GET http://localhost:8080/v1/classes
```

### Subscriptions
This feature enables a student to subscribe into several clasess. Here is an example of the endpoint usage:
Request:
```
POST http://localhost:8080/v1/students/1/classes
```
Payload:
```
{
	"code": 1
}
```
Response:
```
{
    "firstName": "Iberth",
    "lastName": "Fernandez",
    "_links": {
        "self": {
            "href": "http://localhost:8080/v1/students/1"
        },
        "student": {
            "href": "http://localhost:8080/v1/students/1"
        },
        "classes": {
            "href": "http://localhost:8080/v1/students/1/classes"
        }
    }
}
```

### Searches
It provides the ability to search data given the input type of search. The list of searches available for each resource can be found using the following requests:
```
GET http://localhost:8080/v1/students/search
GET http://localhost:8080/v1/classes/search
```

## TO DO

* Add API documentation.
* Improve the searching engine.


## Author

* **Iberth Fernandez** - *Initial work*
