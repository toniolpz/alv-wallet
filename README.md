# ALV Wallet Server Project

## GRPC Server

### Instructions

#### Create MySql database
Execute sql script `resources/database.sql`

#### Run Spring Boot Server
Run `java -jar` command for the file `build/libs/wallet-server.jar`

`java -jar ~/build/libs/wallet-server.jar`



## CLI Client

### Instructions

#### Run Client from command line
Run `java -jar` command for the file `build/libs/wallet-client.jar`

`java -jar ~/build/libs/wallet-client.jar`

Set the parameters for users, threads and rounds while asked


## Important Choices
##### Simple database table
I decided to leave with single Primary Key and without constraints in table, to better handle it with Spring JPA/Hibernate.

##### Use JPARepository instead of DAOs
Gets better performance with the built in handlers and methods for persistance than creating my own DAOs.

##### Using Spring boot than just gRPC for the server
Spring boot brings faster configuration and build options for deploy and executin / Running gRPC server and client.

##### Proto Services definitions and results
This are all Single response methods defined in the proto, since some methods like deposit and withdraw does not need an output, and the getBalance method will just return the results obtained.

Define enum type for currency for handle the correct values for the currency.

Use repeated type for get records when request the Balance of an user to can handle all the possible records in the same request/response event.

##### Integration tests
Set names and order for prioritize execution of the integration tests with jUnit.

Handle request that are expected to throw a Exception like said in the document.

Integration test can be found under `src/test/java/alv/wallet/WalletApplicationTest.java` and are executed when the project is build:
`.\gradlew build`

The test results will be show in the console and the test report can be found under `build\reports\tests\test\index.html`.

##### Client
The client will just handle a max of 10000 rounds per execution to prevent outOfMemoryException.

The Rounds are listed in an Executer class to can be selected and runnable randomly.

The builder for the client and server are managed by gradle in the same Project.


## Estimated transactions
Been tested with different values in the Client I estimate the server can handle up to 200 requests per second running in my dev computer and with the client sending requests in multiple threads from the same machine.