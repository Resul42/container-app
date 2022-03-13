- I have chosen to use Spring boot and Spring cloud to make this application in. I am using java 11 with Spring boot 2.6.3 and Spring cloud 2021.0.1. The reason for not using the latest Spring boot version is the compatibility with spring cloud. Currently, the latest Spring cloud is compatible until 2.6.3.
- For dependency management i am using Maven.
- For the database I am using a NoSQL database (MongoDB).

- The priority for security is high for me since I have a background in supplying medical application where security is a very high priority. This is the reason I have chosen for implementing Spring security with a custom AuthenticationProvider which uses a user with Authorities to define the scope of the user’s access. This user is saved in the database which we also use to manage the users department.
- I have used Swagger for the api documentation. After running the application, you can access swagger by using this url: http://localhost:8080/swagger-ui/index.html#/
The url can change based on the port you have decided to run the app on but default is 8080.

#Run
- To be able to run this application you need to have a MongoDB environment running. The way i do it is to install Docker on your desktop/laptop. 
- After this we pull the MongoDB container ````- sudo docker pull mongo````. After the image is pulled, we run the Mongo docker container by executing this command ```docker run -p 27017:27017 --name containername mongo```.
- After the MongoDB is running, we can run the application and the songs and artists will be saved in the MongoDB.
- The next step after the mongoDB is running, we need to inject a user in the database for usage with the endpoints.
- To do this we need to access the Mongo docker container by executing this command ``docker exec -it containername bash
  ``, followed by ``mongo`` to use the mongo shell.
- When we are in the mongo shell, we need to use the``test`` database ``> use test``.
- Last part is adding the user to the MongoDB by executing this query. 
```json
db.users.insert(
{
  _id:"6d3443d4-f152-4688-995c-bbe1f579fd45",
  "email": "email@test.com",
  "password": "{bcrypt}$2a$10$hjv4naVe0NE7GxluPjBvq.GAwSzpROJA2R3fPnIygaNoRsykRbLA.",
  "roles": ["ADMIN"],
  "department": "HEAVY",
  "_class": "com.example.containerapp.model.User"
})
```
#Usage
- Now we can call the application with E.G Insomnia or Postman. When doing this, you need to use the Basic Auth authentication with:
  - Username: email@test.com
  - password: password
