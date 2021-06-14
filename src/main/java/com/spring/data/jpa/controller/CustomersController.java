package com.spring.data.jpa.controller;

import com.spring.data.jpa.model.Customer;
import com.spring.data.jpa.repository.CustomerJpaRepository;
import com.spring.data.jpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    /** The JPA repository */
    @Autowired
    private CustomerJpaRepository customerJpaRepository;
    @Autowired
    private CustomerService service;

    /**
     * Used to fetch all the Customers from DB
     *
     ** SELECT * FROM CUSTOMER
     *
     * @return list of {@link Customer}
     */
    @GetMapping("/all")
    public List<Customer> findAll() {
        return customerJpaRepository.findAll();
    }

    /**
     * Used to find and return a Customer by name
     *
     **  SELECT * FROM CUSTOMER WHERE NAME =?
     *
     * @param name refers to the name of the Customer
     * @return {@link Customer} object
     */
 /*   @GetMapping("/{name}")
    public Customer findByName(@PathVariable final String name){
        return customerJpaRepository.findByName(name);

    }*/

    /**
     * Used to find and return customer by name and status
     *
     ** SELECT * FROM CUSTOMER WHERE NAME =? AND STATUS =?
     *
     * @param name
     * @param status
     * @return {@link Customer} object
     */
    @GetMapping("/{name}/{status}")
    public List<Customer> findByNameAndStatus(@PathVariable final String name,@PathVariable String status){
        return customerJpaRepository.findByNameAndStatus(name,status);

    }

    /**
     * Used to find  multiple customers with same name
     *
     *  SELECT * FROM CUSTOMER WHERE NAME IN (?)
     *
     * @param storeId
     * @return
     * @return {@link Customer} object
     */
    @GetMapping("/{storeId}")
   public List<Customer> findByNameIn(@PathVariable final Long storeId){
       return customerJpaRepository.findByStoreIdIn(storeId);
  }


    /** Used to find a customer by name case Ignore
     *
     ** SELECT * FROM CUSTOMER WHERE UPPER(STATUS) = UPPER(?)
     * @param status
     * @return
     */
  /* @GetMapping("/{status}")
    public List<Customer> findByNameIgnoreCase(@PathVariable final String status){
        return customerJpaRepository.findByNameIgnoreCase(status);
    }*/



    /**
     * Used to create a Customer in the DB
     *
     * @param customers refers to the Customer needs to be saved
     * @return the {@link Customer} created
     */
    @PostMapping("/load")
    public Customer load(@RequestBody final Customer customers) {
        customerJpaRepository.save(customers);
        return customerJpaRepository.findByName(customers.getName());
    }

    /**
     * Used to get the customers based on the pageNo,page size and sorting by id
     * @param pageNo
     * @param pageSize
     *
     *
     *
     * @param sortBy
     * @return
     */
    @GetMapping("/page")
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Customer> list = service.getAllCustomers(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
    }

/*
    http://localhost:8080/customers?pageSize=5
    http://localhost:8080/customers?pageSize=5&pageNo=1
    http://localhost:8080/customers?pageSize=5&pageNo=2
    http://localhost:8080/customers?pageSize=5&pageNo=1&sortBy=firstName*/
    }