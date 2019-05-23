# egoi-java-test
Project test Java

After the git clone, run the mvn clean install command.

You can see the tests being run.

Create the database in MySql called egoi

Execute script called scriptBD.sql in the project's doc folder

Change the database password in the application.properties file

If you want to change the port other than 8080 is in the file application.properties property server.port

run the mvn clean install command again

Then run "java -jar target/egoi-0.0.1-SNAPSHOT.jar"  command

After executing the commands, import the Egoi.postman_collection.json file from the doc folder in Postman to test the services.

In the service called "findAll", change the word "all" at the end of the url to search by category name

# Change Category
PUT: http://localhost:8080/api/category

# Save Category
POST: http://localhost:8080/api/category

# FindById
GET : http://localhost:8080/api/category/5

# FindAll
GET: http://localhost:8080/api/category/findByName/all

# DELETE
DELETE: http://localhost:8080/api/category/11
