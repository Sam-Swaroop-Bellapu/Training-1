package com.example.CustomerDB.controller;


import com.example.CustomerDB.client.CoordinatorRestTemplate;
import com.example.CustomerDB.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {



    @Autowired
    private CoordinatorRestTemplate coordinatorRestTemplate;



    @RequestMapping(value = "/add-customer", method = RequestMethod.POST)
    public String addCustomer(@RequestBody CustomerDetails customer){
        return coordinatorRestTemplate.addCustomer(customer);
    }

    @RequestMapping(value = "/add-address", method = RequestMethod.POST)
    public String addAddress(@RequestHeader String email,@RequestBody AddressDetails address   ){
        return coordinatorRestTemplate.addAddress(email,address);
    }

//    @GetMapping("/get-customers")
//    public CustomerDetails[] getCustomerAddresses(@RequestHeader String email){
//        return coordinatorRestTemplate.getCustomers(email);
//    }





    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestHeader String email, @RequestHeader String password){
        return coordinatorRestTemplate.loginAPI(email,password);
    }


    @RequestMapping(value = "/add-product",method = RequestMethod.POST)
    public String addProducts(@RequestHeader String email,@RequestBody ProductDetails newProduct){
        return coordinatorRestTemplate.addProduct(email, newProduct);
    }

    @RequestMapping(value = "/add-SKU", method = RequestMethod.POST)
    public String addSKU(@RequestHeader String productCode,@RequestHeader String email , @RequestBody SKUDetails newSKU){
        return coordinatorRestTemplate.addSKU(productCode, email,newSKU);

    }

    @RequestMapping(value = "/add-to-cart",method = RequestMethod.POST)
    public String addToCart(@RequestHeader String email, @RequestBody CartDetails newCart){
        return coordinatorRestTemplate.addToCart(email, newCart);
    }

    @RequestMapping(value = "/add-SKUPrice/{productCode}/{email}", method = RequestMethod.POST)
    public String addSKUPrice(@PathVariable String productCode,@PathVariable String email, @RequestBody SKUPriceDetails newSKUPrice) {
        return coordinatorRestTemplate.addSKUPrice(productCode,email,newSKUPrice);
    }

    @RequestMapping(value = "/add-inventory/{skuCode}/{email}", method = RequestMethod.POST)
    public String addInventory(@PathVariable String skuCode,@PathVariable String email,@RequestBody InventoryDetails newInventory){
        return coordinatorRestTemplate.addInventory(skuCode, email, newInventory);
    }

    @GetMapping("/view-cart")
    public String subTotal(@RequestHeader String email){
        return coordinatorRestTemplate.viewCart(email);
    }


    @RequestMapping(value = "/add-shipping-address-place-order", method = RequestMethod.POST)
    public String addInventory(@RequestHeader String email,@RequestHeader String type){
        return coordinatorRestTemplate.addShippingAddress(email,type);
    }

    @GetMapping("/get-orders")
    public String getOrders(@RequestHeader String email){
        return coordinatorRestTemplate.getOrders(email);
    }

    @RequestMapping(value = "/change-order-status", method = RequestMethod.POST)
    public String orderStatus(@RequestHeader String email,@RequestHeader String message){
        return coordinatorRestTemplate.changeOrderStatus(email, message);
    }

    @GetMapping("/return-orders")
    public String returnOrder(@RequestHeader String email){
        return coordinatorRestTemplate.orderReturned(email);
    }












}
