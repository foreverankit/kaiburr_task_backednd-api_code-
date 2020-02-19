# Kaiburr_task_backend-api_code

Spring Boot backend rest api code for Kaiburr task


# Creating Spring Boot project 

You can either create new or clone from my repository.

There can be many ways to do this. The one which i used is using Intellij ide, if you don’t have you have to install it.

Click on file->new->project, then select Spring Intitializer and click on next. 

After this you need to name your project or package which you want to create. Then you have to also add the dependencies you need. For this project it was web and mongodb.


Once you are done  with it it will look something like this in Intellij. Don’t worry about the opened files as this image is after i wrote the whole code. On you root directory you can see pom.xml which has track of all the dependencies installed which in this case is spring starter web and mongo.


# Creating MVC model for our API

Under src/main/java create java classes as mentioned in the image below. If you have cloned my repository then no need to do that.
I will be going through every java class in depth.
As we create a spring project we have a main java class which in this case is named SpringbootMongodbApplication.




# Creating Model Class(Server Class Here)

This is our Server Model which consist of name,id,language,framework.
@Id annotation above id is because i wanted to be the auto generated key for my collection and the @Component(collection=”Server”) is my collection name.
Rest is the constructors,getters and setters which can be generated easily in Intellij.



# Connection to mongodb

For this we have to create an interface which extends MongoRepository and which will have the Server(model) and data type of your model as generics.
Dont forget to add @Repository annotation. Dont worry about the functions declared inside the interface, i will be explaining later.



## DbSeeder class

This class is created for sending our objects to the database. Its just to check whether the created objects are inserted into the mongodb database. Just copy the code as it is. Her you can see we have the serverRepository object of the ServerRepository class which helps us perform operations.

## Application Properties

Dont forget to change the application.properties. Here the host will be localhost which will run on the port 27017 and our database name is ServerDb.


 

Now its time to run our application. Once we run it and if we have robo3t we can see that our objects are inserted in the database. If you dont have robo3t install it, its just a workbench. Once you run your spring boot application and open robo3t we will see the objects created. As you can see the serverdb is created with th two objects that we mentioned in dbSeeder class. Our connection to the mongodb database is successfully done.


# RestController

We have four get request mapping and two post request mapping for inert and update.

## RestController Detail Functionality

‘/api’ is the root mapping

‘/api/servers’- If we hit this we get lsit of all the serve objects which resides in the mongodb database. Here we are using serverRepository object to perform the crud oberations.

‘/api/id’ - Gives the object with the id passed. Carefull, this is not basic crud so in the ServerRepository class declare the function as findBy and then what you want to find, here id so the function naming will be findById(String id).

‘/api/servers’ - If we hit post request on this. This will take the request body and save the body passed in db. Same is in case of put request but here we will also pass the id in the requestbody and during post it will be automatically created.

‘/api/name/{name passed}’ - Gives the object with the name passed in url.

‘/api/delete/{id passed}’ - Deletes the object with the given id. 

The code will look like this.
 







# Resource not found class

Declare this class like this if by chance the oject we want is null in db, then it will return 404 Request Not Found. This we are using above in Controller class.



Now we are all set we can run our springboot application.
# Checking our Api using postman.

Now once we have our server running we can make api calls using postman. If you dont have postman installed, you can install it as  it is available on chrome web store.


## Get request to /api/servers




## Get request by id /api/{id passed}


## Post Request to /api/servers



After this post we can again hit get to check if it is added or not.


Yes it was added amazing..!!


## Delete by id /api/delete/{id passed}






And yes it was deleted.


## Get by name /api/name/{name passed}





## We have successfully built a spring boot rest api

## Thank you.
