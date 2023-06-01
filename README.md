# shout-review
Build a simple Restful CRUD API for a Movie Review and Ticket Booking system using Spring Boot, Mysql, and JPA.

#### 1.  Clone the application
`https://github.com/dollyraj/shout-review.git`

#### 2.  Create MYSQL Database
    create database shoutreview

#### 3.  Change mysql username and password as per your installation
* Open   `src/main/resources/application.properties` 
* change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

#### 4. Build and run the app using maven
```
mvn package 
java -jar target/shout-review-0.0.1-SNAPSHOT.jar
```

Alternatively, you can run the app without packaging it using -
If you use Maven, run the following command in a terminal window (in the complete) directory:
`mvn spring-boot:run`

The app will start running at http://localhost:8080.
## Explore API
The application defines following CRUD APIs.

### Movie API

|Method|Url|Description|Sample|
|----|---|-----------|------|
|POST|/movie/add|Add movie|[link](https://github.com/dollyraj/shout-review#add-movie---movieadd)|
|GET|/movie/title?title=movieName|Get Movie by Title|
|GET|/movie/genre?genre=genreType|Find movie by genre|


### Review API

|Method|Url|Description|
|----|---|-----------|
|POST|/review/add/movieId|Add review to a movie with given movieId|

### Theater API

|Method|Url|Description|
|----|---|-----------|
|POST|/theater/add|Create theater|

### Show API

|Method|Url|Description|
|----|---|-----------|
|POST|/show/add|Create  show|
|GET|/show/search?city=x|Search show in a city|
|GET|/show/search?city=x&theaterName=y
|GET|/show/search?city=a&movieName=b

### User API

|Method|Url|Description|
|----|---|-----------|
|POST|/user/signup|Create  user|
|GET|/user/userId|Get User|


### Ticket Api
|Method|Url|Description|
|----|---|-----------|
|POST|/ticket/book|Book movie ticket|


**Test them using postman or any other rest client.**

<!-- ### Sample Valid JSON Request Bodies -->

 #### Create User -> /user/signup

```http
{
    "name":"Shaun",
    "password":"movie",
    "email":"xyz12@gmail.com",
    "role":"USER",
    "mobile":"+91999278"
}
```
### Add Movie -> /movie/add
```http
{
    "title":"wingman",
    "genre":"ACTION"
}
```

### Add Review -> /review/add
```http
{
    "movieReview":"Good",
    "rating":3,
    "movieId":5
}
```

### Add Theater -> /theater/add

```http
{
    "name":"nexus pvr2",
    "city":"pune",
    "address":"xyz street"
}
```

### Add Show ->/show/add
```http
{
    "showTime":"2023-04-30T18:00:00",
    "movieId":5,
    "theaterId":2
}
```

### Book Movie Ticket ->/ticket/book

```http
{
    "seatsNumbers":["1A","1B"],
    "userId":2,
    "showId":2,
    "seatType":"REGULAR"
}
```
