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



    public String  addToCart(String email,CartDetails newCart){
        CartDetails cart=new CartDetails();
        //To bring Customer details from Customer table
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8081/get-customers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email", email);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<CustomerDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, request,CustomerDetails[].class);
        Optional<CustomerDetails> CustomerTest= Arrays.stream(response.getBody()).filter(x->x.getEmail().equals(email)).findFirst();



        final String skuUrl = "http://localhost:8082/getSKU";
        HttpHeaders skuHeaders = new HttpHeaders();
        skuHeaders.setContentType(MediaType.APPLICATION_JSON);
        skuHeaders.add("email",email);
        ResponseEntity<SKUDetails[]> skuResponse = restTemplate.exchange(skuUrl, HttpMethod.GET, request,SKUDetails[].class);
        Optional<SKUDetails> skuTest= Arrays.stream(skuResponse.getBody()).filter(x->x.getProductCode().equals(newCart.getProductCode())).findFirst();



        final String inventoryUrl = "http://localhost:8082/get-inventory";

        ResponseEntity<InventoryDetails[]> inventoryResponse = restTemplate.exchange(inventoryUrl, HttpMethod.GET, request,InventoryDetails[].class);
        Optional<InventoryDetails> inventoryTest= Arrays.stream(inventoryResponse.getBody()).filter(x->x.getSkuCode().equals(newCart.getSkuCode())).findFirst();


        List<CartDetails> cartList=cartRepository.findAll();


//        Optional<CartDetails> cartresult=cartList.stream().filter(x->x.getEmail().equals(newCart.getEmail()) && x.getProductCode().equals(newCart.getProductCode()) && x.getSkuCode().equals(newCart.getSkuCode())).findFirst()

        if(CustomerTest.isPresent() && skuTest.isPresent() && cartList.size()!=0){
            Optional<CartDetails> cartresult=cartList.stream().filter(x->x.getEmail().equals(newCart.getEmail()) && x.getProductCode().equals(newCart.getProductCode()) && x.getSkuCode().equals(newCart.getSkuCode())).findFirst();
            if (cartresult.isPresent()){
                return "This entry already present in the cart,please add a new product into the cart";
            }

            System.out.println("hello");
            System.out.println(inventoryTest.get().getQuantityAvailable());


            if((inventoryTest.get().getQuantityAvailable()>= newCart.getQuantity()) ){

                cart.setEmail(email);
                cart.setProductCode(newCart.getProductCode());
                cart.setQuantity(newCart.getQuantity());
                cart.setSkuCode(newCart.getSkuCode());
                cartRepository.save(cart);




                return "Added to cart successfully!!!";
            }
            return "Quantity:"+newCart.getQuantity()+" for this product is not available right now! Out of Stock! ";
        }
        cartRepository.save(newCart);
        return "first product added successfully";
    }




    public List<CartDetails> cartListings(String email){
        List<CartDetails> cart=cartRepository.findAll();
        List<CartDetails> cartDetails=cart.stream().filter(x->x.getEmail().equals(email)).collect(Collectors.toList());
        return cartDetails;
   }

   public String subTotal(String email){
       List<CartDetails> cart=cartRepository.findAll();
//       List<SKUPriceDetails> productsPriceList=skuPriceRepository.findAll();
       AtomicReference<String> viewCart = new AtomicReference<>("");
       AtomicReference<Long> total= new AtomicReference<>(0l);
//       List<ProductDetails> productsList=productRepository.findByProductCode();
       System.out.println("ProductName   "+"Quantity   "+"Price   "+"SubTotal");

       List<CartDetails> cartResultList=cart.stream().filter(x->x.getEmail().equals(email)).collect(Collectors.toList());
       cartResultList.stream().forEach(c ->{
//           ProductDetails product = productRepository.findByProductCode(c.getProductCode());
           RestTemplate restTemplate = new RestTemplate();

           final String productUrl = "http://localhost:8082/getProducts";
           HttpHeaders productHeaders = new HttpHeaders();
           productHeaders.setContentType(MediaType.APPLICATION_JSON);
           productHeaders.add("email",email);
           HttpEntity request=new HttpEntity(productHeaders);
           ResponseEntity<ProductDetails[]> productResponse = restTemplate.exchange(productUrl, HttpMethod.GET, request,ProductDetails[].class);
           Optional<ProductDetails> product= Arrays.stream(productResponse.getBody()).filter(x->x.getProductCode().equals(c.getProductCode())).findFirst();


//           SKUDetails sku = skuRepository.findBySkuCode(c.getSkuCode());
           final String skuUrl = "http://localhost:8082/getSKU";
           HttpHeaders skuHeaders = new HttpHeaders();
           skuHeaders.setContentType(MediaType.APPLICATION_JSON);
           skuHeaders.add("email",email);
           HttpEntity skurequest = new HttpEntity(skuHeaders);
           System.out.println("This is the first request: "+request);
           ResponseEntity<SKUDetails[]> skuResponse = restTemplate.exchange(skuUrl, HttpMethod.GET, skurequest,SKUDetails[].class);
           Optional<SKUDetails> skuTest= Arrays.stream(skuResponse.getBody()).filter(x->x.getSkuCode().equals(c.getSkuCode())).findFirst();

            System.out.println(skuTest.get().getSkuCode());
//           Optional<SKUPriceDetails> price = skuPriceRepository.findById(sku.getSkuCode());

           final String skuPriceUrl="http://localhost:8082/getSKUPrice";

           HttpHeaders skuPriceHeaders = new HttpHeaders();
           skuPriceHeaders.setContentType(MediaType.APPLICATION_JSON);
           skuPriceHeaders.add("email",email);
           skuPriceHeaders.add("skuCode",skuTest.get().getSkuCode());
           System.out.println(skuPriceHeaders);
           HttpEntity<SKUPriceDetails> skuPriceRequest = new HttpEntity<>(skuPriceHeaders);

//           HttpEntity skuPriceRequest = new HttpEntity(skuPriceHeaders);
           System.out.println(skuPriceRequest.getHeaders());
           ResponseEntity<SKUPriceDetails[]> skuPriceResponse = restTemplate.exchange(skuPriceUrl, HttpMethod.GET, skuPriceRequest,SKUPriceDetails[].class);

           Optional<SKUPriceDetails> price= Arrays.stream(skuPriceResponse.getBody()).filter(x->x.getSkuCode().equals(skuTest.get().getSkuCode())).findFirst();




           int sub = Integer.parseInt(price.get().getPrice())*c.getQuantity();
           System.out.println(product.get().getProductName()+"   "+c.getQuantity()+"   "+price.get().getPrice()+"   "+sub+"");
           viewCart.set(viewCart +"\n"+"product name : " + product.get().getProductName() +"\t"+ " price : " + price.get().getPrice() + "\t" + "quantity : " + c.getQuantity() +"\t" +
                   " sub total : " +sub+"");
           total.updateAndGet(v -> v + Integer.parseInt(price.get().getPrice()) * c.getQuantity());


       });
       viewCart.set(viewCart+"\nTotal: "+total +"\n");
       return viewCart.toString();
   }

   public String addShippingAddress(String email,String type){
//       List<CustomerDetails> customerDetails=customerRepository.findAll();
       RestTemplate restTemplate = new RestTemplate();
       final String url = "http://localhost:8081/get-customers";
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       headers.add("email", email);
       HttpEntity request = new HttpEntity(headers);
       ResponseEntity<CustomerDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, request,CustomerDetails[].class);
       Optional<CustomerDetails> CustomerTest= Arrays.stream(response.getBody()).filter(x->x.getEmail().equals(email)).findFirst();

//       Optional  res = customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();

       if(CustomerTest.isPresent()){
//           List<AddressDetails> addressDetails=addressRepository.findByEmail(email);
           List<CartDetails> cartDetails=cartRepository.findByEmail(email);
           System.out.println(type);
           cartDetails.stream().forEach(x->{
               ShippingDetails shipping=new ShippingDetails();
                System.out.println(x.getProductCode());
//               Optional<AddressDetails> addressList=addressRepository.findByType(type);
               final String Addressurl = "http://localhost:8081/get-customerAddresses";
               ResponseEntity<AddressDetails[]> Addressresponse = restTemplate.exchange(Addressurl, HttpMethod.GET, request,AddressDetails[].class);
               Optional<AddressDetails> addressList= Arrays.stream(Addressresponse.getBody()).filter(y->y.getType().equals(type)).findFirst();

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
//           List<CartDetails> inventoryList=cartRepository.findAll();
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
       List<CustomerDetails> customerDetails=customerRepository.findAll();
       Optional  res = customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();

       if(res.isPresent()){
           List<OrderDetails> orders=orderRepository.findByEmail(email);
           return orders;
       }
       return null;
   }

    public String orderStatus(String email,String message){
        List<OrderDetails> orders=orderRepository.findByEmail(email);
        List<CustomerDetails> customerDetails=customerRepository.findAll();
        Optional  res = customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();

        if(res.isPresent()){
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
        List<CartDetails> inventoryList=cartRepository.findAll();
        orders.stream().forEach(x->{
            OrderDetails updatedOrder=new OrderDetails();
            updatedOrder.setProductCode(x.getProductCode());
            updatedOrder.setEmail(x.getEmail());
            updatedOrder.setStatus("product returned");
            orderRepository.delete(x);
            orderRepository.save(updatedOrder);
        });
        inventoryList.stream().forEach(x->{
            InventoryDetails removeInventory=inventoryRepository.findBySkuCode(x.getSkuCode());
            InventoryDetails updatedInventory=new InventoryDetails();
            updatedInventory.setSkuCode(x.getSkuCode());
            int finalQuantity=removeInventory.getQuantityAvailable()+x.getQuantity();
            updatedInventory.setQuantityAvailable(finalQuantity);
            inventoryRepository.delete(removeInventory);
            inventoryRepository.save(updatedInventory);
        });
        return "product returned!";
    }










}
