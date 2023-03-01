package paf.ws21.repository;

public class SqlQueries {

    public static final String SQL_SELECTALLCUSTOMERS = "SELECT * FROM customers LIMIT ? OFFSET ?";

    public static final String SQL_SELECTCUSTOMERBYID = "SELECT id, company, last_name, first_name, job_title, business_phone, address, city, state_province FROM customers WHERE id = ?";

    public static final String SQL_SELECTCUSTOMERORDER = "SELECT c.id AS customer_id, c.company, c.last_name, c.first_name, c.job_title, c.business_phone, o.id AS order_id, DATE_FORMAT(o.order_date, \"%d/%m/%Y\") as order_date, DATE_FORMAT(o.shipped_date, \"%d/%m/%Y\") as shipped_date, o.ship_name, o.shipping_fee FROM customers c INNER JOIN orders o ON c.id = o.customer_id WHERE o.customer_id = ?";

}
