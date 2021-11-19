package com.example.CustomerDB.controller;



import com.example.CustomerDB.entity.*;
import com.example.CustomerDB.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;





    @RequestMapping(value = "/add-customer", method = RequestMethod.POST)
    public String addCustomer(@RequestBody CustomerDetails customer){
        return customerService.addCustomer(customer);
    }

    @GetMapping("/get-customers")
        public List<CustomerDetails> getCustomers(@RequestHeader String email){
        return  customerService.getCustomers(email);

        }




    @RequestMapping(value = "/add-address", method = RequestMethod.POST)
    public String addAddress(@RequestBody AddressDetails address ,@RequestHeader String email  ){
        return customerService.addAddress(email,address);
    }

    @GetMapping("/get-customerAddresses")
    public List getCustomerAddresses(@RequestHeader String email){
        return customerService.getCustomerAddresses(email);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestHeader String email, @RequestHeader String password){
        return customerService.loginApi(email, password);
    }

}
