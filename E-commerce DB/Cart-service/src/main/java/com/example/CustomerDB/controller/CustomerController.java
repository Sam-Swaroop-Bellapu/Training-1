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


    @RequestMapping(value = "/add-to-cart", method = RequestMethod.POST)
    public String addToCart(@RequestHeader String email,@RequestBody CartDetails newCart){
        return customerService.addToCart(email,newCart);
    }

    @GetMapping("/get-listings")
    public List<CartDetails> getProductListings(@RequestHeader String email){
        return customerService.cartListings(email);

    }
    @GetMapping("/sub-total")
    public String subTotal(@RequestHeader String email){
        return customerService.subTotal(email);
    }





}
