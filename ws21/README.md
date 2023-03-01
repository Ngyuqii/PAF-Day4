## Workshop 21
Use JDBCTemplate to query a MySQL database

### Task 1
- Create the given database (northwind) and load the data from the SQL files. 
- Create a new user (non root) and give the user access to the northwind database.

### Task 2
- Configure the SpringBoot to use this database. Add the configurations to application.properties file.

### Task 3
Write REST controller(s) to process the following HTTP requests
- Get a list of all customers (GET /api/customers Accept: application/json). This HTTP endpoint supports the following parameters.
    1. offset - return the first result from n records from the first; n is the number given by offset parameter
    2. limit - return the number of records specified by limit
The default value for offset is 0 and limit is 5.

- Get the details of a customer with the customer’s id (GET /api/customer/<customer_id> Accept: application/json). Return a 404 and an appropriate error object if the customer does not exist.

- Get all orders for a customer (GET /api/customer/<customer_id>/orders Accept: application/json). This endpoint returns an array of orders in JSON. If the customer does not have any orders, the endpoint should return an empty array. Return a 404 and an appropriate error object if the customer does not exist.