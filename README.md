# Kaiburr_task_backend-api_code

Spring Boot backend rest api code for Kaiburr task


# Creating Spring Boot project 

You can either create new or clone from my repository.

There can be many ways to do this. The one which i used is using Intellij ide, if you don’t have you have to install it.

Click on file->new->project, then select Spring Intitializer and click on next. 

![Screenshot from 2020-02-20 00-34-45](https://user-images.githubusercontent.com/31029148/74873979-3057b980-5386-11ea-81a8-fea4d11fa815.png)

After this you need to name your project or package which you want to create. Then you have to also add the dependencies you need. For this project it was web and mongodb.


Once you are done  with it it will look something like this in Intellij. Don’t worry about the opened files as this image is after i wrote the whole code. On you root directory you can see pom.xml which has track of all the dependencies installed which in this case is spring starter web and mongo.

![Screenshot from 2020-02-20 00-42-52](https://user-images.githubusercontent.com/31029148/74874283-b96ef080-5386-11ea-9e6f-7e82311ed1d9.png)


# Creating MVC model for our API

Under src/main/java create java classes as mentioned in the image below. If you have cloned my repository then no need to do that.
I will be going through every java class in depth.
As we create a spring project we have a main java class which in this case is named SpringbootMongodbApplication.

![Screenshot from 2020-02-20 00-49-32](https://user-images.githubusercontent.com/31029148/74874420-f0450680-5386-11ea-867c-a05b654b3552.png)


# Creating Model Class(Server Class Here)

This is our Server Model which consist of name,id,language,framework.
```@Id``` annotation above id is because i wanted to be the auto generated key for my collection and the ```@Component(collection=”Server”)``` is my collection name.
Rest is the constructors,getters and setters which can be generated easily in Intellij. Below code will be in the server class.

```package rc;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Server")
public class Server {

    private String name;

    @Id
    private String id;

    private String language;

    private String framework;

    public Server(String name, String language, String framework) {
        this.name = name;
        this.language = language;
        this.framework = framework;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public String getFramework() {
        return framework;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }
}
```
![Screenshot from 2020-02-20 00-55-37](https://user-images.githubusercontent.com/31029148/74874732-2bdfd080-5387-11ea-81d7-08490426ee44.png)



# Connection to mongodb

For this we have to create an interface which extends MongoRepository and which will have the Server(model) and data type of your model as generics.
Dont forget to add @Repository annotation. Dont worry about the functions declared inside the interface, i will be explaining later. Add following lines of code to yout serverRepository class. 

```
package rc;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServerRepository extends MongoRepository<Server,String> {
    Optional<Server> findById(String id);

    List<Server> findByName(String name);
}

```
![Screenshot from 2020-02-20 01-02-25](https://user-images.githubusercontent.com/31029148/74874859-5f225f80-5387-11ea-9ab8-171328760c2b.png)



## DbSeeder class

This class is created for sending our objects to the database. Its just to check whether the created objects are inserted into the mongodb database. Just copy the code as it is. Her you can see we have the serverRepository object of the ServerRepository class which helps us perform operations.

![Screenshot from 2020-02-20 01-06-35](https://user-images.githubusercontent.com/31029148/74874920-7c572e00-5387-11ea-95df-96facbda39f5.png)



## Application Properties

Dont forget to change the application.properties. Here the host will be localhost which will run on the port 27017 and our database name is ServerDb.

```
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=ServerDb
```

![Screenshot from 2020-02-20 01-11-55](https://user-images.githubusercontent.com/31029148/74874974-93961b80-5387-11ea-86e5-456be7e1fb3c.png)

Now its time to run our application. Once we run it and if we have robo3t we can see that our objects are inserted in the database. If you dont have robo3t install it, its just a workbench. Once you run your spring boot application and open robo3t we will see the objects created. As you can see the serverdb is created with th two objects that we mentioned in dbSeeder class. Our connection to the mongodb database is successfully done.

![Screenshot from 2020-02-20 01-16-30](https://user-images.githubusercontent.com/31029148/74875033-adcff980-5387-11ea-85f5-066791f16297.png)



# RestController

We have four get request mapping and two post request mapping for inert and update.

## RestController Detail Functionality

`/api` is the root mapping

`/api/servers`- If we hit this we get lsit of all the serve objects which resides in the mongodb database. Here we are using serverRepository object to perform the crud oberations.

`/api/id` - Gives the object with the id passed. Carefull, this is not basic crud so in the ServerRepository class declare the function as findBy and then what you want to find, here id so the function naming will be findById(String id).

`/api/servers` - If we hit post request on this. This will take the request body and save the body passed in db. Same is in case of put request but here we will also pass the id in the requestbody and during post it will be automatically created.

`/api/name/{name passed}` - Gives the object with the name passed in url.

`/api/delete/{id passed}` - Deletes the object with the given id. 

The code will look like this.
 
![Screenshot from 2020-02-20 01-34-33](https://user-images.githubusercontent.com/31029148/74876567-effa3a80-5389-11ea-86af-24a252293401.png)

![Screenshot from 2020-02-20 01-34-36](https://user-images.githubusercontent.com/31029148/74876640-099b8200-538a-11ea-9540-bf539955ad9e.png)

# Resource not found ecxeption class

Declare this class like this if by chance the oject we want is null in db, then it will return 404 Request Not Found. This we are using above in Controller class.

```
package rc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{


}

```

![Screenshot from 2020-02-20 01-37-41](https://user-images.githubusercontent.com/31029148/74876686-1fa94280-538a-11ea-9761-63f9b6477ddc.png)

Now we are all set we can run our springboot application.

# Checking our Api using postman.

Now once we have our server running we can make api calls using postman. If you dont have postman installed, you can install it as  it is available on chrome web store.


## Get request to /api/servers

When this request is hit in postman, the getAll method runs in sprinboot and through the ServerRepository class we get all the objects in out database.

```
 @GetMapping("/servers")
    public List<Server> getAll(){
        List<Server> servers = this.serverRepository.findAll();
        return servers;
    }
```

![Screenshot from 2020-02-20 01-43-23](https://user-images.githubusercontent.com/31029148/74876733-38195d00-538a-11ea-8d37-db0191182f6b.png)


## Get request by id /api/{id passed}

This method in the ServerCOntroller class runs which accept an id in the url to get the object with the following id.

```
    @GetMapping("/{id}")
    public Optional<Server> getById(@PathVariable("id") String id){
        Optional<Server> servers = this.serverRepository.findById(id);
        if(servers.isEmpty())
            throw new ResourceNotFoundException();

        return servers;
    }
```

![Screenshot from 2020-02-20 01-46-43](https://user-images.githubusercontent.com/31029148/74876781-4cf5f080-538a-11ea-9166-b595c4898a76.png)

## Post Request to /api/servers

This method in the ServerCOntroller class accepts the request body to be updated in the database and creates a new object in database.

```
   @PostMapping("/servers")
    public void update(@RequestBody Server server){
        this.serverRepository.save(server);
    }
```

![Screenshot from 2020-02-20 01-48-23](https://user-images.githubusercontent.com/31029148/74876832-69922880-538a-11ea-9e3b-fa86fccdf975.png)

After this post we can again hit get to check if it is added or not.

![Screenshot from 2020-02-20 01-49-37](https://user-images.githubusercontent.com/31029148/74876842-7282fa00-538a-11ea-84e3-daf94ffe72e8.png)

Yes it was added amazing..!!


## Delete by id /api/delete/{id passed}

This methods int the ServerController class deletes the object with the given id passed in the url. 

```
   @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id){
        this.serverRepository.deleteById(id);
    }
```

![Screenshot from 2020-02-20 01-53-38](https://user-images.githubusercontent.com/31029148/74876942-a1996b80-538a-11ea-9464-04fb8bb2fc4f.png)

![Screenshot from 2020-02-20 01-54-14](https://user-images.githubusercontent.com/31029148/74876989-b544d200-538a-11ea-814f-449ec2f6689f.png)


And yes it was deleted.


## Get by name /api/name/{name passed}

This method in SeverController class return the id with the given name passed in the url.

```
   @GetMapping("/name/{name}")
    public List<Server> getByName(@PathVariable("name") String name){
        List<Server> servers = this.serverRepository.findByName(name);

        if(servers.isEmpty())
            throw new ResourceNotFoundException();

        return servers;
    }
```

![Screenshot from 2020-02-20 01-56-20](https://user-images.githubusercontent.com/31029148/74877057-cb529280-538a-11ea-9b41-48463691eade.png)

## We have successfully built a spring boot rest api


