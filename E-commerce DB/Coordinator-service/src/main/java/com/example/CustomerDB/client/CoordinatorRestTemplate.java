package com.example.CustomerDB.client;

import com.example.CustomerDB.entity.*;
import com.example.CustomerDB.testCaseData.TestCaseData;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CoordinatorRestTemplate {

    public String addCustomer(CustomerDetails newCustomer){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8081/add-customer";
        HttpEntity<CustomerDetails> request = new HttpEntity<>(newCustomer);
        ResponseEntity<String> response = restTemplate
                .exchange(fooResourceUrl, HttpMethod.POST, request, String.class);
        return response.getBody();
    }

    public String addAddress(String email,AddressDetails newAddress){
        RestTemplate restTemplate = new RestTemplate();
        String newUrl = "http://localhost:8081/add-address";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email", email);
        HttpEntity<AddressDetails> request = new HttpEntity<AddressDetails>(newAddress,headers);
        ResponseEntity<String> response = restTemplate
                .exchange(newUrl, HttpMethod.POST, request, String.class);

        return response.getBody();

    }

//    public CustomerDetails[] getCustomers(String email){
//        RestTemplate restTemplate = new RestTemplate();
//        final String url = "http://localhost:8081/get-customers";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("email", email);
//        HttpEntity request = new HttpEntity(headers);
//        ResponseEntity<CustomerDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, request,CustomerDetails[].class);
//        return response.getBody();
//
//    }

    public String loginAPI(String email,String password){
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8081/login";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email", email);
        headers.add("password",password);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request,String.class);
        return response.getBody();

    }

    public String addProduct(String email, ProductDetails newProduct){
        RestTemplate restTemplate=new RestTemplate();
        final String productUrl="http://localhost:8082/add-product";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email", email);
        HttpEntity<ProductDetails> productRequest=new HttpEntity<ProductDetails>(newProduct,headers);
        ResponseEntity<String> productResponse= restTemplate.exchange(productUrl, HttpMethod.POST, productRequest,String.class);

        return productResponse.getBody();




//        if(!productResponse.getBody().isEmpty() ){
//            return "added product information successfully";
//        }
//        return "unable to add product";
    }

    public String addToCart(String email,CartDetails newCart){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8083/add-to-cart";
        HttpHeaders cartHeaders = new HttpHeaders();
        cartHeaders.setContentType(MediaType.APPLICATION_JSON);
        cartHeaders.add("email",email);
        HttpEntity<CartDetails> request = new HttpEntity<>(newCart,cartHeaders);

        ResponseEntity<String> response = restTemplate
                .exchange(fooResourceUrl, HttpMethod.POST, request, String.class);
        return response.getBody();

    }

    public String addSKU(String productCode,String email,SKUDetails newSKU){
        //add SKU information from the hardcoded data to the product

        RestTemplate restTemplate=new RestTemplate();
        final String skuUrl="http://localhost:8082/add-SKU";
        HttpHeaders skuheaders = new HttpHeaders();
        skuheaders.setContentType(MediaType.APPLICATION_JSON);
        skuheaders.add("productCode",productCode);
        skuheaders.add("email",email);
        HttpEntity<SKUDetails> skuRequest=new HttpEntity<SKUDetails>(newSKU,skuheaders);
        ResponseEntity<String> skuResponse=restTemplate.exchange(skuUrl,HttpMethod.POST,skuRequest,String.class);
        return skuResponse.getBody();


    }

    public String addSKUPrice(String productCode,String email,SKUPriceDetails newSkuPrice){
        //add SKU Price information from the hardcoded data to the product
        RestTemplate restTemplate = new RestTemplate();

        final String skuPriceURL="http://localhost:8082/add-SKUPrice";

        HttpHeaders skuPriceheaders = new HttpHeaders();
        skuPriceheaders.setContentType(MediaType.APPLICATION_JSON);
        skuPriceheaders.add("productCode",productCode);
        skuPriceheaders.add("email",email);
        HttpEntity<SKUPriceDetails> skuPriceRequest=new HttpEntity<SKUPriceDetails>(newSkuPrice,skuPriceheaders);
        ResponseEntity<String> skuPriceResponse=restTemplate.exchange(skuPriceURL,HttpMethod.POST,skuPriceRequest,String.class);
        return skuPriceResponse.getBody();

    }

    public String addInventory(String skuCode,String email,InventoryDetails inventory){
        //add Inventory information from the hardCoded data to the product
        RestTemplate restTemplate = new RestTemplate();

        final String inventoryURL="http://localhost:8082/add-inventory";
        HttpHeaders inventoryHeaders = new HttpHeaders();
        inventoryHeaders.setContentType(MediaType.APPLICATION_JSON);
        inventoryHeaders.add("skuCode",skuCode);
        inventoryHeaders.add("email",email);
        HttpEntity<InventoryDetails> inventoryRequest=new HttpEntity<InventoryDetails>(inventory,inventoryHeaders);
        ResponseEntity<String> inventoryResponse=restTemplate.exchange(inventoryURL,HttpMethod.POST,inventoryRequest,String.class);
        return inventoryResponse.getBody();


    }

    public String viewCart(String email){
        RestTemplate restTemplate=new RestTemplate();
        final String Url="http://localhost:8083/sub-total";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email",email);
        HttpEntity<CartDetails> skuRequest=new HttpEntity<CartDetails>(headers);
        ResponseEntity<String> skuResponse=restTemplate.exchange(Url,HttpMethod.GET,skuRequest,String.class);
        return skuResponse.getBody();


    }


    public String addShippingAddress(String email,String type){
        RestTemplate restTemplate=new RestTemplate();
        final String Url="http://localhost:8084/add-shipping-address-place-order";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email",email);
        headers.add("type",type);
        HttpEntity<CartDetails> shippingRequest=new HttpEntity<CartDetails>(headers);
        ResponseEntity<String> shippingResponse=restTemplate.exchange(Url,HttpMethod.POST,shippingRequest,String.class);
        return shippingResponse.getBody();

    }


    public String getOrders(String email){
        RestTemplate restTemplate=new RestTemplate();
        final String Url="http://localhost:8084/get-orders";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email",email);
        HttpEntity<CartDetails> ordersRequest=new HttpEntity<CartDetails>(headers);
        ResponseEntity<String> ordersResponse=restTemplate.exchange(Url,HttpMethod.GET,ordersRequest,String.class);
        return ordersResponse.getBody();

    }

    public String changeOrderStatus(String email,String message){
        RestTemplate restTemplate=new RestTemplate();
        final String Url="http://localhost:8084/change-order-status";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email",email);
        headers.add("message",message);
        HttpEntity<CartDetails> ordersRequest=new HttpEntity<CartDetails>(headers);
        ResponseEntity<String> ordersResponse=restTemplate.exchange(Url,HttpMethod.POST,ordersRequest,String.class);
        return ordersResponse.getBody();



    }

    public String orderReturned(String email){
        RestTemplate restTemplate=new RestTemplate();
        final String Url="http://localhost:8084/return-orders";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email",email);
        HttpEntity<CartDetails> ordersRequest=new HttpEntity<CartDetails>(headers);
        ResponseEntity<String> ordersResponse=restTemplate.exchange(Url,HttpMethod.GET,ordersRequest,String.class);
        return ordersResponse.getBody();


    }












}
