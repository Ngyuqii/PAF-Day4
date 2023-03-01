package paf.ws21.repository;

import static paf.ws21.repository.SqlQueries.*;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import paf.ws21.model.Customer;
import paf.ws21.model.Order;

@Repository
public class NWRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //Method to select all customers
    //SQL_SELECTALLCUSTOMERS = "SELECT * FROM customers LIMIT ? OFFSET ?"
    public List<Customer> getAllCustomers(Integer limit,Integer offset){
        List<Customer> customers = new LinkedList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECTALLCUSTOMERS, limit , offset);
        while(rs.next()){
            customers.add(Customer.createCustomer(rs));
        }
        return customers;
    }

    //Method to get customer by id
    /*
    SQL_SELECTCUSTOMERBYID = "SELECT id, company, last_name, first_name, 
    job_title, business_phone, address, city, state_province FROM customers WHERE id = ?"
    */
    public Customer getCustomerById(Integer id){
        List<Customer> customers = new LinkedList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECTCUSTOMERBYID, id);
        while(rs.next()){
            customers.add(Customer.createCustomer(rs));
        }
        return customers.get(0);
    }

    //Method to get orders by customer id
    /*
    SQL_SELECTCUSTOMERORDER = "SELECT c.id AS customer_id, c.company, c.last_name, c.first_name, 
    c.job_title, c.business_phone, o.id AS order_id, o.order_date, o.shipped_date, o.ship_name, 
    o.shipping_fee FROM customers c INNER JOIN orders o ON c.id = o.customer_id WHERE o.customer_id = ?";
    */
    public List<Order> getOrderByCId(Integer id){
        List<Order> orderList = new LinkedList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECTCUSTOMERORDER, id);
        while(rs.next()){
            orderList.add(Order.createOrder(rs));
        }
        return orderList;
    }
    
}