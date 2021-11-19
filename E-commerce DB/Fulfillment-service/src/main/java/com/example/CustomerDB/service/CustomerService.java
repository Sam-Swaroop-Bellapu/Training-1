package com.example.CustomerDB.service;

import com.example.CustomerDB.entity.*;
import com.example.CustomerDB.repository.*;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import io.jsonwebtoken.Jwts;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
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



   public String addShippingAddress(String email,String type){
//       List<CustomerDetails> customerDetails=customerRepository.findAll();
       RestTemplate restTemplate = new RestTemplate();
       final String url = "http://localhost:8081/get-customers";
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       headers.add("email", email);
       HttpEntity request = new HttpEntity(headers);
       ResponseEntity<CustomerDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, request,CustomerDetails[].class);
       Optional<CustomerDetails>  test= Arrays.stream(response.getBody()).filter(x->x.getEmail().equals(email)).findFirst();

//       Optional  res = customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();

       if(test.isPresent()){
//           List<AddressDetails> addressDetails=addressRepository.findByEmail(email);
//           List<CartDetails> cartDetails=cartRepository.findByEmail(email);
           final String cartURL="http://localhost:8083/get-listings";
           HttpHeaders cartHeaders=new HttpHeaders();
           cartHeaders.setContentType(MediaType.APPLICATION_JSON);
           cartHeaders.set("email",email);
           HttpEntity cartRequest=new HttpEntity(cartHeaders);
           ResponseEntity<CartDetails[]> cartResponse=restTemplate.exchange(cartURL,HttpMethod.GET,cartRequest,CartDetails[].class);
           List<CartDetails>  cartDetails= Arrays.stream(cartResponse.getBody()).filter(x->x.getEmail().equals(email)).collect(Collectors.toList());


           final String Addressurl = "http://localhost:8081/get-customerAddresses";
           ResponseEntity<AddressDetails[]> Addressresponse = restTemplate.exchange(Addressurl, HttpMethod.GET, request,AddressDetails[].class);
           Optional<AddressDetails> addressList= Arrays.stream(Addressresponse.getBody()).filter(y->y.getType().equals(type)).findFirst();




           System.out.println(type);
           cartDetails.stream().forEach(x->{
               ShippingDetails shipping=new ShippingDetails();
                System.out.println(x.getProductCode());
//               Optional<AddressDetails> addressList=addressRepository.findByType(type);
               System.out.println(addressList);
               shipping.setEmail(addressList.get().getEmail());
               shipping.setShippingAddress(addressList.get().getShippingAddress());
               shipping.setLine1(addressList.get().getLine1());
               shipping.setLine2(addressList.get().getLine2());
               shipping.setPostalCode(addressList.get().getPostalCode());
               shipping.setCity(addressList.get().getCity());
               shipping.setState(addressList.get().getState());
               shipping.setType(addressList.get().getType());
               shipping.setProductCode(x.getProductCode());
               shippingRepository.save(shipping);
           });
           //placing the order

           cartDetails.stream().forEach(x->{
               OrderDetails newOrder=new OrderDetails();
               newOrder.setEmail(email);
               newOrder.setProductCode(x.getProductCode());
               newOrder.setStatus("Order Placed");
               orderRepository.save(newOrder);
           });

           // delete the quantity from the inventory


           final String inventoryURL="http://localhost:8082/add-and-delete-inventory";
           HttpHeaders inventoryHeaders=new HttpHeaders();
           inventoryHeaders.setContentType(MediaType.APPLICATION_JSON);
           inventoryHeaders.set("email",email);
           inventoryHeaders.set("operation","remove");
           HttpEntity inventoryRequest=new HttpEntity(inventoryHeaders);
           ResponseEntity<InventoryDetails[]> inventoryResponse=restTemplate.exchange(cartURL,HttpMethod.GET,inventoryRequest,InventoryDetails[].class);
           System.out.println(inventoryResponse);



//           List<CartDetails> inventoryList=cartDetails;
//           inventoryList.stream().forEach(x->{
//               InventoryDetails removeInventory=inventoryRepository.findBySkuCode(x.getSkuCode());
//               InventoryDetails updatedInventory=new InventoryDetails();
//               updatedInventory.setSkuCode(x.getSkuCode());
//               int finalQuantity=removeInventory.getQuantityAvailable()-x.getQuantity();
//               updatedInventory.setQuantityAvailable(finalQuantity);
//               inventoryRepository.delete(removeInventory);
//               inventoryRepository.save(updatedInventory);
//           });


           return "Shipping Address added and order placed successfully!";
       }
       return "Customer not registered";

   }

   public List<OrderDetails> getOrders(String email){
//       List<CustomerDetails> customerDetails=customerRepository.findAll();
       RestTemplate restTemplate = new RestTemplate();
       final String url = "http://localhost:8081/get-customers";
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       headers.add("email", email);
       HttpEntity request = new HttpEntity(headers);
       ResponseEntity<CustomerDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, request,CustomerDetails[].class);
       Optional<CustomerDetails>  test= Arrays.stream(response.getBody()).filter(x->x.getEmail().equals(email)).findFirst();

//       Optional  res = customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();

       if(test.isPresent()){
           List<OrderDetails> orders=orderRepository.findByEmail(email);
           return orders;
       }
       return null;
   }

    public String orderStatus(String email,String message){
        List<OrderDetails> orders=orderRepository.findByEmail(email);
//        List<CustomerDetails> customerDetails=customerRepository.findAll();
//        Optional  res = customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8081/get-customers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email", email);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<CustomerDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, request,CustomerDetails[].class);
        Optional<CustomerDetails>  test= Arrays.stream(response.getBody()).filter(x->x.getEmail().equals(email)).findFirst();


        if(test.isPresent()){
            orders.stream().forEach(x->{
                if(message.equals("hyderabad")){
                    OrderDetails updatedOrder=new OrderDetails();
                    updatedOrder.setStatus("product dispatched");
                    updatedOrder.setEmail(email);
                    updatedOrder.setProductCode(x.getProductCode());
                    orderRepository.delete(x);
                    System.out.println(updatedOrder.getStatus());
                    orderRepository.save(updatedOrder);
                    System.out.println(" this is implemented");


                }
                if(message.equals("vijayawada")){
                    OrderDetails updatedOrder=new OrderDetails();
                    updatedOrder.setStatus("shipment in travel");
                    updatedOrder.setEmail(email);
                    updatedOrder.setProductCode(x.getProductCode());
//                orderRepository.delete(x);
                    orderRepository.save(updatedOrder);

                }

                if(message.equals("rajahmundry")){
                    OrderDetails updatedOrder=new OrderDetails();
                    updatedOrder.setStatus("shipment delivered");
                    updatedOrder.setEmail(email);
                    updatedOrder.setProductCode(x.getProductCode());
                    orderRepository.delete(x);
                    orderRepository.save(updatedOrder);

                }


            });
        }

        return "Successfully updated status";


    }

    public String returnService(String email){
        List<OrderDetails> orders=orderRepository.findAll();
//        List<CartDetails> inventoryList=cartRepository.findAll();
        orders.stream().forEach(x->{
            OrderDetails updatedOrder=new OrderDetails();
            updatedOrder.setProductCode(x.getProductCode());
            updatedOrder.setEmail(x.getEmail());
            updatedOrder.setStatus("product returned");
            orderRepository.delete(x);
            orderRepository.save(updatedOrder);
        });
//        inventoryList.stream().forEach(x->{
//            InventoryDetails removeInventory=inventoryRepository.findBySkuCode(x.getSkuCode());
//            InventoryDetails updatedInventory=new InventoryDetails();
//            updatedInventory.setSkuCode(x.getSkuCode());
//            int finalQuantity=removeInventory.getQuantityAvailable()+x.getQuantity();
//            updatedInventory.setQuantityAvailable(finalQuantity);
//            inventoryRepository.delete(removeInventory);
//            inventoryRepository.save(updatedInventory);
//        });
        return "product returned!";
    }










}
