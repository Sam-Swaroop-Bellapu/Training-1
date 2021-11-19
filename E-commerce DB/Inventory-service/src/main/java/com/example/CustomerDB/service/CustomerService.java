package com.example.CustomerDB.service;

import com.example.CustomerDB.entity.*;
import com.example.CustomerDB.repository.*;
import com.example.CustomerDB.testCaseData.TestCaseData;
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

    public String addCustomer(CustomerDetails newCustomer){
        List<CustomerDetails> customerList=customerRepository.findAll();
        Optional<CustomerDetails> result=customerList.stream().filter(x->x.getEmail().equals(newCustomer.getEmail())).findFirst();
        if (result.isPresent()){
            return "Customer Already present in the database. please add a new customer!";
        }
        CustomerDetails customers=new CustomerDetails();
        customers.setFirstName(newCustomer.getFirstName());
        customers.setLastName(newCustomer.getLastName());
        customers.setEmail(newCustomer.getEmail());
        customers.setMobileNumber(newCustomer.getMobileNumber());

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("cjss_encryption"); // encryptor's private key
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        String data=newCustomer.getMyPassword();
        String encryptedData = encryptor.encrypt(data);
        customers.setMyPassword(encryptedData);
        customerRepository.save(customers);
        return "Customer added successfully";
    }

    public String addAddress(String email,AddressDetails newAddress){
        List<CustomerDetails> customerDetails=customerRepository.findAll();
        Optional  res = customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();

        if(res.isPresent()){
            addressRepository.save(newAddress);
            return "Address Successfully added!!!";

        }
        return "user does not exist,Please create a customer entry!";

    }


    public List getCustomerAddresses(String email){
        List<AddressDetails> addressDetails=addressRepository.findAll();
        List<CustomerDetails> customerDetails=customerRepository.findAll();
        Optional  res = customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();
        if(res.isPresent()){
            return addressDetails.stream().filter(x->x.getEmail().equals(email)).collect(Collectors.toList());

        }
        List<ArrayList> h=new ArrayList<>();
        return h;
//        System.out.print(res.toString());


    }

    public ResponseEntity<String> loginApi(String email,String password){
        List<CustomerDetails> customerDetails=customerRepository.findAll();

        //Decryption of password and comparison with passed login information.
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("cjss_encryption"); // encryptor's private key
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        Optional<CustomerDetails> result=customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();
        String decryptedPassword=encryptor.decrypt(result.get().getMyPassword());
        int EXPIRATIONTIME = 1000*60*60*1;

        String secret = "CJSSTECHNOLOGIES";
        if(result.get().getEmail().equals(email) && decryptedPassword.equals(password)){
            String JWT = Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() +EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

            String username = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(JWT)
                    .getBody()
                    .getSubject();

            Date expirationDate=Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(JWT)
                    .getBody()
                    .getExpiration();
            Date date=new Date(System.currentTimeMillis()-EXPIRATIONTIME);

            Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
            Instant expiration = issuedAt.plus(-3, ChronoUnit.MINUTES);

            Instant expire = Instant.now().truncatedTo(ChronoUnit.SECONDS).plus(-3, ChronoUnit.MINUTES);

            return ResponseEntity.status(HttpStatus.CREATED).body("Valid User");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UnAuthorised User!!!");



    }

    public String addProducts(String email,ProductDetails newProduct){
        List<CustomerDetails> customerDetails=customerRepository.findAll();
        Optional  res = customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();
        List<ProductDetails> productList=productRepository.findAll();


        List<ProductDetails> r= new ArrayList<>();

//        System.out.println(res.isPresent());
//        System.out.println(productList.size());
//        System.out.println(product.get().getProductCode().equals(newProduct.getProductCode()));
//        System.out.println(product.get().getEmail().equals(email));
//        System.out.println(product.get().getProductCode());
//        System.out.println(newProduct.getProductCode());


//        if(res.isPresent() && productList.size()!=0 && product.get().getProductCode().equals(newProduct.getProductCode()) && product.get().getEmail().equals(email)){
//
//            return "Product already present in the inventory,please add a new one!";
//        }
//
//        productRepository.save(newProduct);
//        return "Added the Product Successfully!!!";

//            if(res.isPresent() && productList.size()!=0){
//                if((product.get().getProductCode().equals(newProduct.getProductCode())) && (product.get().getEmail().equals(newProduct.getEmail()))){
//                    return "Product already present in the inventory,please add a new one!";
//                }
//                productRepository.save(newProduct);
//                return "Added the Product Successfully!!!";
//            }
//            productRepository.save(newProduct);
//            return "First product added successfully!";
//        if(res.isPresent() && productList.size()!=0){
//
//
//
//
//            List<ProductDetails> productNewList=productRepository.findAll();
//            List<ProductDetails> product=productNewList.stream().filter(x->x.getEmail().equals(email)).collect(Collectors.toList());
//            product.forEach(x-> System.out.println(x.getProductCode()));
//            System.out.println(product);
//
//            productRepository.save(newProduct);
//            return "one new product added";
//        }
//        if(!res.isPresent()){
//            return "Add  Customer to add products";
//        }
//        productRepository.save(newProduct);
//        return "First product added!";

        if(res.isPresent()){
            if(productList.size()!=0){
                List<ProductDetails> productDetailsList=productRepository.findAll();
                List<ProductDetails> result=productDetailsList.stream().filter(x->x.getEmail().equals(email)).collect(Collectors.toList());
                Optional <ProductDetails> prod = result.stream().filter(x-> x.getProductCode().equals(newProduct.getProductCode())).findFirst();
                if(prod.isPresent()){
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





    public List<ProductDetails> getProducts(String email){
        List<CustomerDetails> customerDetails=customerRepository.findAll();
        List<ProductDetails> productList=productRepository.findAll();
        List<ProductDetails> result=productList.stream().filter(u->u.getEmail().equals(email)).collect(Collectors.toList());
        Optional  res = customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();
        if(res.isPresent()){
            return productList.stream().filter(d->d.getEmail().equals(email)).collect(Collectors.toList());
        }

        List<ProductDetails> empty=new ArrayList<>();

        return empty;

    }

    public String addSKU(String productCode,String email,SKUDetails SKU){
        List<CustomerDetails> customerDetails=customerRepository.findAll();
        List<ProductDetails> productsDetails=productRepository.findAll();
        Optional  res = customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();
        Optional<ProductDetails> result=productsDetails.stream().filter(r->r.getProductCode().equals(productCode)).findFirst();
        if(res.isPresent() && result.isPresent()){
            skuRepository.save(SKU);
            return "Successfully added SKU";
        }
        return "product for this skuCode was not added,please add the product first before adding its sku details";

    }

    public List<SKUDetails> getSKU(String email,String productCode){
        List<CustomerDetails> customerDetails=customerRepository.findAll();
        List<SKUDetails> SKUList=skuRepository.findAll();
//        Optional result=SKUList.stream().filter(u->u.getProductCode().equals(productCode)).findFirst();
        Optional  res = customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();


        if(res.isPresent()){
            return SKUList;
        }

        List<SKUDetails> empty=new ArrayList<>();
        return empty;




    }

    public String addSKUPrice(String productCode,String email, SKUPriceDetails newSkuPrice){
        SKUPriceDetails skuPrice=new SKUPriceDetails();
        List<CustomerDetails> customerDetails=customerRepository.findAll();
        List<SKUDetails> SKUDetails=skuRepository.findAll();
        Optional result=SKUDetails.stream().filter(e->e.getProductCode().equals(productCode)).findFirst();
        Optional  res = customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();
        if(res.isPresent() && result.isPresent()){

            skuPriceRepository.save(newSkuPrice);
            return "Successfully added SKU price";
        }
        return "Unsuccessful operation";


    }

    public List<SKUPriceDetails> getSKUPrice(String email,String skuCode){
        List<CustomerDetails> customerDetails=customerRepository.findAll();
        List<SKUPriceDetails> SKUPriceList=skuPriceRepository.findAll();
        Optional result=SKUPriceList.stream().filter(u->u.getSkuCode().equals(skuCode)).findFirst();
        Optional  res = customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();
        if(res.isPresent() && result.isPresent()){
            return SKUPriceList;
        }
        List<SKUPriceDetails> empty=new ArrayList<>();
        return empty;
    }

    public String addInventory(String skuCode,String email,InventoryDetails newInventory){
        //get info from customer table using REST Template
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8081/get-customers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("email", email);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<CustomerDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, request,CustomerDetails[].class);
        Optional<CustomerDetails> customer= Arrays.stream(response.getBody()).filter(x->x.getEmail().equals(email)).findFirst();

        //get info from SKU table using REST Template
        final String skuUrl="http://localhost:8082/add-SKU";
        HttpHeaders skuheaders = new HttpHeaders();
        skuheaders.setContentType(MediaType.APPLICATION_JSON);
        skuheaders.add("productCode",newProduct.getProductCode());
        skuheaders.add("email",email);
        HttpEntity<SKUDetails> skuRequest=new HttpEntity<SKUDetails>(newSKU,skuheaders);




        List<SKUDetails> skuDetails=skuRepository.findAll();

        Optional result=skuDetails.stream().filter(x->x.getSkuCode().equals(skuCode)).findFirst();

        if(customer.isPresent() && result.isPresent()){
            InventoryDetails inventory=new InventoryDetails();
            inventory.setSkuCode(newInventory.getSkuCode());
            inventory.setQuantityAvailable(newInventory.getQuantityAvailable());
            inventoryRepository.save(inventory);

            return "Inventory added successfully";
        }
        return "Inventory cannot be added as there is no product with that specific skuCode!!!";


    }

    public List<InventoryDetails> getInventory(String email){
        List<CustomerDetails> customerDetails=customerRepository.findAll();
        List<InventoryDetails> InventoryList=inventoryRepository.findAll();
//        Optional result=InventoryList.stream().filter(u->u.getSkuCode().equals(skuCode)).findFirst();
        Optional  res = customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();
        if(res.isPresent()){
            return InventoryList;
        }
        List<InventoryDetails> empty=new ArrayList<>();
        return empty;
    }

    public String  addToCart(String email,CartDetails newCart){
        CartDetails cart=new CartDetails();
        List<CartDetails> t=new ArrayList<>();
        List<CustomerDetails> customerDetails=customerRepository.findAll();
        List<SKUDetails> productAndSKU=skuRepository.findAll();
        List<InventoryDetails> inventory=inventoryRepository.findAll();
        List<CartDetails> cartList=cartRepository.findAll();

        Optional<CustomerDetails>  customerResult = customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();
        Optional<SKUDetails> ProductandSKUResult=productAndSKU.stream().filter(x->x.getProductCode().equals(newCart.getProductCode()) && x.getSkuCode().equals(newCart.getSkuCode())).findFirst();

        Optional<InventoryDetails> quantityResult=inventory.stream().filter(x->x.getSkuCode().equals(newCart.getSkuCode())).findFirst();
//        Optional<CartDetails> cartresult=cartList.stream().filter(x->x.getEmail().equals(newCart.getEmail()) && x.getProductCode().equals(newCart.getProductCode()) && x.getSkuCode().equals(newCart.getSkuCode())).findFirst()

        if(customerResult.isPresent() && ProductandSKUResult.isPresent() && cartList.size()!=0){
            Optional<CartDetails> cartresult=cartList.stream().filter(x->x.getEmail().equals(newCart.getEmail()) && x.getProductCode().equals(newCart.getProductCode()) && x.getSkuCode().equals(newCart.getSkuCode())).findFirst();
            if (cartresult.isPresent()){
                return "This entry already present in the cart,please add a new product into the cart";
            }

            if((quantityResult.get().getQuantityAvailable()>= newCart.getQuantity()) ){

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




    public List<CartDetails> productListings(String email){
        List<String> result=new ArrayList<>();
        List<CartDetails> cart=cartRepository.findAll();
        List<SKUPriceDetails> productsPriceList=skuPriceRepository.findAll();
        List<ProductDetails> productsList=productRepository.findAll();
        List<CartDetails> cartDetails=cart.stream().filter(x->x.getEmail().equals(email)).collect(Collectors.toList());
        List d=new ArrayList();
        return cartDetails;
   }

   public String subTotal(String email){
       List<CartDetails> cart=cartRepository.findAll();
       List<SKUPriceDetails> productsPriceList=skuPriceRepository.findAll();
       AtomicReference<String> viewCart = new AtomicReference<>("");
       AtomicReference<Long> total= new AtomicReference<>(0l);
//       List<ProductDetails> productsList=productRepository.findByProductCode();
       System.out.println("ProductName   "+"Quantity   "+"Price   "+"SubTotal");

       List<CartDetails> cartResultList=cart.stream().filter(x->x.getEmail().equals(email)).collect(Collectors.toList());
       cartResultList.stream().forEach(c ->{
           ProductDetails product = productRepository.findByProductCode(c.getProductCode());
           SKUDetails sku = skuRepository.findBySkuCode(c.getSkuCode());
           Optional<SKUPriceDetails> price = skuPriceRepository.findById(sku.getSkuCode());
           int sub = Integer.parseInt(price.get().getPrice())*c.getQuantity();
           System.out.println(product.getProductName()+"   "+c.getQuantity()+"   "+price.get().getPrice()+"   "+sub+"");
           viewCart.set(viewCart +"\n"+"product name : " + product.getProductName() +"\t"+ " price : " + price.get().getPrice() + "\t" + "quantity : " + c.getQuantity() +"\t" +
                   " sub total : " +sub+"");
           total.updateAndGet(v -> v + Integer.parseInt(price.get().getPrice()) * c.getQuantity());


       });
       viewCart.set(viewCart+"\nTotal: "+total +"\n");
       return viewCart.toString();
   }

   public String addShippingAddress(String email,String type){
       List<CustomerDetails> customerDetails=customerRepository.findAll();
       Optional  res = customerDetails.stream().filter(x->x.getEmail().equals(email)).findFirst();

       if(res.isPresent()){
//           List<AddressDetails> addressDetails=addressRepository.findByEmail(email);
           List<CartDetails> cartDetails=cartRepository.findByEmail(email);
           System.out.println(type);
           cartDetails.stream().forEach(x->{
               ShippingDetails shipping=new ShippingDetails();
                System.out.println(x.getProductCode());
               Optional<AddressDetails> addressList=addressRepository.findByType(type);
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
           List<CartDetails> inventoryList=cartRepository.findAll();
           inventoryList.stream().forEach(x->{
               InventoryDetails removeInventory=inventoryRepository.findBySkuCode(x.getSkuCode());
               InventoryDetails updatedInventory=new InventoryDetails();
               updatedInventory.setSkuCode(x.getSkuCode());
               int finalQuantity=removeInventory.getQuantityAvailable()-x.getQuantity();
               updatedInventory.setQuantityAvailable(finalQuantity);
               inventoryRepository.delete(removeInventory);
               inventoryRepository.save(updatedInventory);
           });



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
