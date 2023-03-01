package paf.ws21.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;


public class Customer {

    private Integer cId;
    private String company;
    private String lastName;
    private String firstName;
    private String jobTitle;
    private String businessPhone;
    private String address;
    private String city;
    private String stateProvince;
    
    //Getters
    public Integer getcId() {return cId;}
    public String getCompany() {return company;}
    public String getLastName() {return lastName;}
    public String getFirstName() {return firstName;}
    public String getJobTitle() {return jobTitle;}
    public String getBusinessPhone() {return businessPhone;}
    public String getAddress() {return address;}
    public String getCity() {return city;}
    public String getStateProvince() {return stateProvince;}
    
    //Setters
    public void setcId(Integer cId) {this.cId = cId;}
    public void setCompany(String company) {this.company = company;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setJobTitle(String jobTitle) {this.jobTitle = jobTitle;}
    public void setBusinessPhone(String businessPhone) {this.businessPhone = businessPhone;}
    public void setAddress(String address) {this.address = address;}
    public void setCity(String city) {this.city = city;}
    public void setStateProvince(String stateProvince) {this.stateProvince = stateProvince;}

    //Create Customer object from RowSet Object
    public static Customer createCustomer(SqlRowSet rs) {
        Customer c = new Customer();
        c.setcId(rs.getInt("id"));
        c.setCompany(rs.getString("company"));
        c.setLastName(rs.getString("last_name"));
        c.setFirstName(rs.getString("first_name"));
        c.setJobTitle(rs.getString("job_title"));
        c.setBusinessPhone(rs.getString("business_phone"));
        c.setAddress(rs.getString("address"));
        c.setCity(rs.getString("city"));
        c.setStateProvince(rs.getString("state_province"));
        return c;
    }

    //Create JsonObject from Customer object
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("id", getcId())
                .add("company", getCompany())
                .add("last_name", getLastName())
                .add("first_name", getFirstName())
                .add("job_title",getJobTitle())
                .add("business_phone",getBusinessPhone())
                .add("address", getAddress())
                .add("city",getCity())
                .add("state_province",getStateProvince())
                .build();
    }
    
}