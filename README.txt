Controller
---------------------------------
- Created a OrderController class in which the REST endpoints are defined.
  This class is used to receive the requests and return the response.

Service Layer
------------------------------------
- From the Controller class, we are calling service class OrderServiceImpl
  which is used to perform any business logic.

DAO Layer
--------------------------------------
- Created an Entity class Order which is the mapping for table order.Created
  two more mapping classes NumberCount and WeightCount which is used to return
  the result set of group by functions.

- Created a Repository OrderRepository which extends PagingAndSortingRepository
  required for pagination.This is used to handle SQL queries logic.

- From the service class(OrderServiceImpl), we are calling the methods of
  OrderRepository and passing the page size as parameter.

Batch Job
-------------------------------------------------------
- Written a Spring Batch processing Job which will read data from CSV
  and store the records in database after performing some business logic
  over the records. Added classes BatchConfiguration and OrderProcessor
  for this.
- Added a Listener JobCompletionNotificationListener which will run after
  the completion of job.

Advice/Exception Handling
---------------------------------------------------
- Created a Controller Advice which will handle the exceptions and return
  the ResponseEntity.

Unit Tests
------------------------------------------------------
- Created a test package and added tests for OrderServiceImpl, OrderProcessor
  and OrderController.

Design Patterns
-----------------------------------------------------
- Implemented Builder and Factory Design Patterns.Created on Factory class
  OrderProcessorFactory which will create the object of Item processor based
  on the parameter given at the time of execution of job.

Properties configuration
------------------------------------------------------
- Created one application.yml file to store the properties.

