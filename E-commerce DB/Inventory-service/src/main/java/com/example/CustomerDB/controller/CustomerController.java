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

    @RequestMapping(value = "/add-inventory", method = RequestMethod.POST)
    public String getInventory(@RequestHeader String skuCode,@RequestHeader String email,@RequestBody InventoryDetails newInventory){
        return customerService.addInventory(skuCode,email,newInventory);
    }

    @GetMapping("/get-Inventory/{email}/{skuCode}")
    public List<InventoryDetails> getInventory(@PathVariable String email){
        return customerService.getInventory(email);
    }
}
