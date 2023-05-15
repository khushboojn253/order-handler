Controller
---------------------------------
1. Created a OrderController class in which the REST endpoints are defined.
   This class is used to receive the requests and return the response.

Service Layer
------------------------------------
2. From the Controller class, we are calling service class OrderServiceImpl
   which is used to perform any business logic.

DAO Layer
--------------------------------------
3. Created an Entity class Order which is the mapping for table order.Created
   two more mapping classes NumberCount and WeightCount which is used to return
   the result set of group by functions.

4. Created a Repository OrderRepository which extends PagingAndSortingRepository
   required for pagination.This is used to handle SQL queries logic.

5. From the service class(OrderServiceImpl), we are calling the methods of
   OrderRepository and passing the page size as parameter.

CSV Handling
----------------------------------------------
6. An interface CSVHandler is created to handle CSV related operations.The
   implementation class's method  is reading the CSV with the file path/name
   given and then returning the records.OrderServiceImpl is further using the
   method and parsing the records.

Advice/Exception Handling
---------------------------------------------------
7.  Created a Controller Advice which will handle the exceptions and return
    the ResponseEntity.

Unit Tests
------------------------------------------------------
8. Created a test package and added test for OrderServiceImpl class.