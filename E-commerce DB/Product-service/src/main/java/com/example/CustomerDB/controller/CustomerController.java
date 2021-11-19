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



    @RequestMapping(value = "/add-product", method = RequestMethod.POST)
    public String addProduct(@RequestHeader String email, @RequestBody ProductDetails newproducts){
        return customerService.addProducts(email,newproducts);
    }

    @GetMapping("/getProducts")
    public List<ProductDetails> getProducts(@RequestHeader String email){
        return customerService.getProducts(email);

    }

    @RequestMapping(value = "/add-SKU", method = RequestMethod.POST)
    public String addSKU(@RequestHeader String productCode,@RequestHeader String email , @RequestBody SKUDetails newSKU){
        return customerService.addSKU(productCode,email,newSKU);

    }

    @GetMapping("/getSKU")
    public List<SKUDetails> getSKU(@RequestHeader String email){
        return customerService.getSKU(email);
    }

    @RequestMapping(value = "/add-SKUPrice", method = RequestMethod.POST)
    public String addSKUPrice(@RequestHeader String productCode,@RequestHeader String email, @RequestBody SKUPriceDetails newSKUPrice) {
        return customerService.addSKUPrice(productCode,email, newSKUPrice);
    }

    @GetMapping("/getSKUPrice")
    public List<SKUPriceDetails> getSKUPrice(@RequestHeader String email,@RequestHeader String skuCode){
        return customerService.getSKUPrice(email,skuCode);
    }

    @RequestMapping(value = "/add-inventory", method = RequestMethod.POST)
    public String getInventory(@RequestHeader String skuCode,@RequestHeader String email,@RequestBody InventoryDetails newInventory){
        return customerService.addInventory(skuCode,email,newInventory);
    }

    @GetMapping("/get-inventory")
    public List<InventoryDetails> getinventory(@RequestHeader String email){
        return customerService.getInventory(email);
    }

    @RequestMapping(value = "/add-and-delete-inventory", method = RequestMethod.POST)
    public String addAndRemoveInventory(@RequestHeader String email,@RequestHeader String operation){
        return customerService.updateInventory(email,operation);
    }

}
