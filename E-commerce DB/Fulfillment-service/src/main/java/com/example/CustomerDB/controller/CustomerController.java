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


    @RequestMapping(value = "/add-shipping-address-place-order", method = RequestMethod.POST)
    public String addShippingAddress(@RequestHeader String email,@RequestHeader String type){
        return customerService.addShippingAddress(email, type);
    }

    @GetMapping("/get-orders")
    public List<OrderDetails> getOrders(@RequestHeader String email){
        return customerService.getOrders(email);
    }

    @RequestMapping(value = "/change-order-status", method = RequestMethod.POST)
    public String orderStatus(@RequestHeader String email,@RequestHeader String message){
        return customerService.orderStatus(email, message);
    }

    @GetMapping("/return-orders")
    public String returnOrder(@RequestHeader String email){
        return customerService.returnService(email);
    }



























}
