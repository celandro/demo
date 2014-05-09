Service can be started by running
mvn compile glassfish:run
or by copying the war file from the target directory to the webserver of your choice

Requires a local mongo running on default port, database is testDB

insert users in the local database by:
> mongo testDB
> db.users.insert({userName: "ryan", password:"hello", gender:"male"})

urls:
http://localhost:8080/service/api/1.0/status
http://localhost:8080/service/api/1.0/users/list?gender=female
http://localhost:8080/service/api/1.0/users/authenticate?userName=ryan&password=hello
http://localhost:8080/service/api/1.0/files/list?filePath=src/main/java/demo

Jax-rs was chosen as the server implementation for its support of json serialization(using the jackson library) and easy to read syntax. Mongodb with Morphia was chosen as an easy to demo database that does not require configuration while still providing object mapping for easy code use. Spring was chosen as a way to wire in mongo and the UserDao and theoretically allow configuration if desired.

Versioning is supported through the creation of additional service classes. Pagination is not supported however could be added through the limit and offset methods in the morphia query api see https://github.com/mongodb/morphia/wiki/Query