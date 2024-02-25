User Service
Technology Stack: Java 8, Spring Boot 3.2, Kafka, MySQL, etc.
APIS:
  1)POST localhost:8080/ecom/api/auth/get-token ( for getting jwt token)
  2)GET localhost:8080/ecom/api/user (get user details)
  3)POST localhost:8080/ecom/api/auth/create-account (create user account)
  3)POST localhost:8080/ecom/api/auth/update-account
  4)DELETE localhost:8080/ecom/api/auth/delete-account/email=?
  
Product Service
Technology Stack: Java 17, Spring Boot 2.7, Kafka, MongoDB, etc.

APIS:
  1) GET http://localhost:8080/api/products
  2) GET http://localhost:8080/api/products/{id}
  3) POST http://localhost:8080/api/products (with JSON payload)
  4) PUT http://localhost:8080/api/products/{id} (with JSON payload)
  5) DELETE http://localhost:8080/api/products/{id}
  6) POST-=http://localhostL8080/order/create-order ( create order api)
