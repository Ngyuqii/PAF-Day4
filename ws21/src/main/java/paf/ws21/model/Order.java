package paf.ws21.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Order {
    private Integer oId;
    private DateTime orderDate;
    private DateTime shippedDate;
    private String shipName;
    private Double shippingFee;
    private Customer customer; //Relationship

    //Getters
    public Integer getoId() {return oId;}
    public DateTime getOrderDate() {return orderDate;}
    public DateTime getShippedDate() {return shippedDate;}
    public String getShipName() {return shipName;}
    public Double getShippingFee() {return shippingFee;}
    public Customer getCustomer() {return customer;}

    //Setters
    public void setoId(Integer oId) {this.oId = oId;}
    public void setOrderDate(DateTime orderDate) {this.orderDate = orderDate;}
    public void setShippedDate(DateTime shippedDate) {this.shippedDate = shippedDate;}
    public void setShipName(String shipName) {this.shipName = shipName;}
    public void setShippingFee(Double shippingFee) {this.shippingFee = shippingFee;}
    public void setCustomer(Customer customer) {this.customer = customer;}

    //Create Order object from RowSet Object
    public static Order createOrder (SqlRowSet rs){

        Customer customer = new Customer();
        customer.setcId(rs.getInt("customer_id"));
        customer.setCompany(rs.getString("company"));
        customer.setLastName(rs.getString("last_name"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setJobTitle(rs.getString("job_title"));
        customer.setBusinessPhone(rs.getString("business_phone"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setoId(rs.getInt("order_id"));
        
        order.setOrderDate(DateTimeFormat.forPattern("DD/MM/YYYY").parseDateTime(rs.getString("order_date")));
        if (rs.getString("shipped_date") != null) {
            order.setShippedDate(DateTimeFormat.forPattern("DD/MM/YYYY").parseDateTime(rs.getString("shipped_date")));
        }

        order.setShipName(rs.getString("ship_name"));
        order.setShippingFee(rs.getDouble("shipping_fee"));

        return order;
    }

    //Create Json Object from order object
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("customer_id", getCustomer().getcId())
                .add("company", getCustomer().getCompany())
                .add("last_name", getCustomer().getLastName())
                .add("first_name", getCustomer().getFirstName())
                .add("job_title", getCustomer().getJobTitle())
                .add("business_phone", getCustomer().getBusinessPhone())
                .add("order_id", getoId())
                .add("order_date", getOrderDate().toString())
                .add("shipped_date", getShippedDate() != null ? getShippedDate().toString() : "")
                .add("ship_name", getShipName())
                .add("shipping_fee", getShippingFee())
                .add("customer_id", getCustomer().getcId())
                .build();
    }

}