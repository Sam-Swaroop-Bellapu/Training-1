package com.example.CustomerDB.service;

import com.example.CustomerDB.entity.*;
import com.example.CustomerDB.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SKURepository skuRepository;

    @Autowired
    private SKUPriceRepository skuPriceRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private OrderRepository orderRepository;


    public String addProducts(String email, ProductDetails newProduct) {
        //get info from customer table using REST Template
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8081/get-customers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email", email);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<CustomerDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CustomerDetails[].class);
        Optional<CustomerDetails> test = Arrays.stream(response.getBody()).filter(x -> x.getEmail().equals(email)).findFirst();


        List<ProductDetails> productList = productRepository.findAll();
        if (test.isPresent()) {
            if (productList.size() != 0) {
                List<ProductDetails> productDetailsList = productRepository.findAll();
                List<ProductDetails> result = productDetailsList.stream().filter(x -> x.getEmail().equals(email)).collect(Collectors.toList());
                Optional<ProductDetails> prod = result.stream().filter(x -> x.getProductCode().equals(newProduct.getProductCode())).findFirst();
                if (prod.isPresent()) {
                    return "The product is already present! Add a new product";
                }
                productRepository.save(newProduct);
                return "New product added successfully";
            }
            productRepository.save(newProduct);
            return "First product added successfully";
        }
        return "Add a customer before adding a product";
    }


    public List<ProductDetails> getProducts(String email) {
        List<ProductDetails> productList = productRepository.findAll();
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8081/get-customers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email", email);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<CustomerDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CustomerDetails[].class);
        Optional<CustomerDetails> test = Arrays.stream(response.getBody()).filter(x -> x.getEmail().equals(email)).findFirst();

        if (test.isPresent()) {
            return productList.stream().filter(d -> d.getEmail().equals(email)).collect(Collectors.toList());
        }

        List<ProductDetails> empty = new ArrayList<>();

        return empty;

    }


    public String addSKU(String productCode, String email, SKUDetails SKU) {
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8081/get-customers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email", email);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<CustomerDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CustomerDetails[].class);
        Optional<CustomerDetails> CustomerTest = Arrays.stream(response.getBody()).filter(x -> x.getEmail().equals(email)).findFirst();

        final String productUrl = "http://localhost:8082/getProducts";
        HttpHeaders productHeaders = new HttpHeaders();
        productHeaders.setContentType(MediaType.APPLICATION_JSON);
        productHeaders.add("email", email);
        ResponseEntity<ProductDetails[]> productResponse = restTemplate.exchange(productUrl, HttpMethod.GET, request, ProductDetails[].class);
        Optional<ProductDetails> ProductTest = Arrays.stream(productResponse.getBody()).filter(x -> x.getProductCode().equals(productCode)).findFirst();


        if (CustomerTest.isPresent() && ProductTest.isPresent()) {
            skuRepository.save(SKU);
            return "Successfully added SKU";
        }
        return "product for this skuCode was not added,please add the product first before adding its sku details";

    }

    public List<SKUDetails> getSKU(String email) {
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8081/get-customers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email", email);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<CustomerDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CustomerDetails[].class);
        Optional<CustomerDetails> CustomerTest = Arrays.stream(response.getBody()).filter(x -> x.getEmail().equals(email)).findFirst();

        List<SKUDetails> SKUList = skuRepository.findAll();
//        Optional result=SKUList.stream().filter(u->u.getProductCode().equals(productCode)).findFirst();


        if (CustomerTest.isPresent()) {
            return SKUList;
        }

        List<SKUDetails> empty = new ArrayList<>();
        return empty;


    }

    public String addSKUPrice(String productCode, String email, SKUPriceDetails newSkuPrice) {
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8081/get-customers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email", email);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<CustomerDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CustomerDetails[].class);
        Optional<CustomerDetails> CustomerTest = Arrays.stream(response.getBody()).filter(x -> x.getEmail().equals(email)).findFirst();

        List<SKUDetails> SKUDetails = skuRepository.findAll();
        Optional result = SKUDetails.stream().filter(e -> e.getProductCode().equals(productCode)).findFirst();
        if (CustomerTest.isPresent() && result.isPresent()) {

            skuPriceRepository.save(newSkuPrice);
            return "Successfully added SKU price";
        }
        return "Unsuccessful operation";


    }

    public List<SKUPriceDetails> getSKUPrice(String email, String skuCode) {
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8081/get-customers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email", email);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<CustomerDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CustomerDetails[].class);
        Optional<CustomerDetails> CustomerTest = Arrays.stream(response.getBody()).filter(x -> x.getEmail().equals(email)).findFirst();

        List<SKUPriceDetails> SKUPriceList = skuPriceRepository.findAll();
        Optional result = SKUPriceList.stream().filter(u -> u.getSkuCode().equals(skuCode)).findFirst();
        if (CustomerTest.isPresent() && result.isPresent()) {
            return SKUPriceList;
        }
        List<SKUPriceDetails> empty = new ArrayList<>();
        return empty;
    }

    public String addInventory(String skuCode, String email, InventoryDetails newInventory) {
        //get info from customer table using REST Template
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8081/get-customers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email", email);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<CustomerDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CustomerDetails[].class);
        Optional<CustomerDetails> test = Arrays.stream(response.getBody()).filter(x -> x.getEmail().equals(email)).findFirst();
        List<SKUDetails> skuDetails = skuRepository.findAll();
        Optional result = skuDetails.stream().filter(x -> x.getSkuCode().equals(skuCode)).findFirst();

        if (test.isPresent() && result.isPresent()) {
            InventoryDetails inventory = new InventoryDetails();
            inventory.setSkuCode(newInventory.getSkuCode());
            inventory.setQuantityAvailable(newInventory.getQuantityAvailable());
            inventoryRepository.save(inventory);

            return "Inventory added successfully";
        }
        return "Inventory cannot be added as there is no product with that specific skuCode!!!";


    }


    public List<InventoryDetails> getInventory(String email) {
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8081/get-customers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email", email);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<CustomerDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CustomerDetails[].class);
        Optional<CustomerDetails> test = Arrays.stream(response.getBody()).filter(x -> x.getEmail().equals(email)).findFirst();

        List<InventoryDetails> InventoryList = inventoryRepository.findAll();
//        Optional result=InventoryList.stream().filter(u->u.getSkuCode().equals(skuCode)).findFirst();
        if (test.isPresent()) {
            return InventoryList;
        }
        List<InventoryDetails> empty = new ArrayList<>();
        return empty;
    }

    public String updateInventory(String email, String operation) {
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8081/get-customers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email", email);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<CustomerDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CustomerDetails[].class);
        Optional<CustomerDetails> test = Arrays.stream(response.getBody()).filter(x -> x.getEmail().equals(email)).findFirst();


        final String cartURL = "http://localhost:8083/get-listings";
        HttpHeaders cartHeaders = new HttpHeaders();
        cartHeaders.setContentType(MediaType.APPLICATION_JSON);
        cartHeaders.set("email", email);
        HttpEntity cartRequest = new HttpEntity(cartHeaders);
        ResponseEntity<CartDetails[]> cartResponse = restTemplate.exchange(cartURL, HttpMethod.GET, cartRequest, CartDetails[].class);
        List<CartDetails> cartDetails = Arrays.stream(cartResponse.getBody()).filter(x -> x.getEmail().equals(email)).collect(Collectors.toList());


        if (test.isPresent()) {
            if (operation.equals("remove")) {
                cartDetails.forEach(x -> {
                    InventoryDetails removeInventory = inventoryRepository.findBySkuCode(x.getSkuCode());
                    InventoryDetails updatedInventory = new InventoryDetails();
                    updatedInventory.setSkuCode(x.getSkuCode());
                    int finalQuantity = removeInventory.getQuantityAvailable() - x.getQuantity();
                    updatedInventory.setQuantityAvailable(finalQuantity);
                    inventoryRepository.delete(removeInventory);
                    inventoryRepository.save(updatedInventory);
                });
                return "successfully updated inventory!";
            }
            if (operation == "add") {
                cartDetails.stream().forEach(x -> {
                    InventoryDetails removeInventory = inventoryRepository.findBySkuCode(x.getSkuCode());
                    InventoryDetails updatedInventory = new InventoryDetails();
                    updatedInventory.setSkuCode(x.getSkuCode());
                    int finalQuantity = removeInventory.getQuantityAvailable() + x.getQuantity();
                    updatedInventory.setQuantityAvailable(finalQuantity);
                    inventoryRepository.delete(removeInventory);
                    inventoryRepository.save(updatedInventory);
                });
                return "successfully updated inventory!";

            }
            return "customer not present in the database";


        }
        return "could not update";


    }
}
