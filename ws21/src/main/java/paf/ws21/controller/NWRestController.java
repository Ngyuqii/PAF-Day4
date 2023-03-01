package paf.ws21.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import paf.ws21.model.Customer;
import paf.ws21.model.Order;
import paf.ws21.repository.NWRepository;

import org.springframework.http.ResponseEntity;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class NWRestController {

    @Autowired
    NWRepository nwRepo;
    
    @GetMapping(path = "/customers")
    public ResponseEntity<String> getAllCustomers(@RequestParam(defaultValue = "5") String limit,
        @RequestParam(defaultValue = "0") String offset){
            
        List<Customer> cList = nwRepo.getAllCustomers(Integer.parseInt(limit), Integer.parseInt(offset));
            
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Customer c : cList) {
            arrBuilder.add(c.toJson());
        }

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Json.createObjectBuilder()
                    .add("customers", arrBuilder)
                    .build().toString());
    }
          
    @GetMapping(path = "/customer/{customerId}")
    public ResponseEntity<String> getCustomerById(@PathVariable Integer customerId){

        try {
            Customer c = nwRepo.getCustomerById(customerId);

            return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Json.createObjectBuilder()
                        .add("customer", c.toJson())
                        .build().toString());
        }
        catch (IndexOutOfBoundsException e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Json.createObjectBuilder()
                        .add("message","customerId: %s not found".formatted(customerId))
                        .build().toString()); 
        }
    }

    @GetMapping(path = "/customer/{customerId}/orders")
    public ResponseEntity<String> getOrderById(@PathVariable Integer customerId){

        try {
            nwRepo.getCustomerById(customerId);
        }
        catch (IndexOutOfBoundsException e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Json.createObjectBuilder()
                        .add("message","customerId: %s not found".formatted(customerId))
                        .build().toString()); 
        }

        List<Order> oList = nwRepo.getOrderByCId(customerId);

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Order o : oList) {
            arrBuilder.add(o.toJson());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Json.createObjectBuilder()
                        .add("orders",arrBuilder)
                        .build().toString());

    }
}