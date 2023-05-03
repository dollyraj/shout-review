# shout-review
Build a simple Restful CRUD API for a Movie Review and Ticket Booking system using Spring Boot, Mysql, and JPA.


## Explore API
The application defines following CRUD APIs.

### Movie API

|Method|Url|Description|
|----|---|-----------|
|POST|/movie/add|Add movie|
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


###Ticket Api
|Method|Url|Description|
|----|---|-----------|
|POST|/ticket/book|Book movie ticket|


**Test them using postman or any other rest client.**

<!-- ### Sample Valid JSON Request Bodies -->

Create User -> /user/signup

```
{
    "name":"Shaun",
    "password":"movie",
    "email":"xyz12@gmail.com",
    "role":"USER",
    "mobile":"+91999278"
}
```
Add Movie -> /movie/add
```
{
    "title":"wingman",
    "genre":"ACTION"
}
```

Add Review -> /review/add
```
{
    "movieReview":"Good",
    "rating":3,
    "movieId":5
}
```

Add Theater -> /theater/add

```
{
    "name":"nexus pvr2",
    "city":"pune",
    "address":"xyz street"
}
```
Add Show

```
{
    "showTime":"2023-04-30T18:00:00",
    "movieId":5,
    "theaterId":2
}
```

Book Movie Ticket

```
{
    "seatsNumbers":["1A","1B"],
    "userId":2,
    "showId":2,
    "seatType":"REGULAR"
}
```
