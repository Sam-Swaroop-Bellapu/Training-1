package com.example.CustomerDB.testCaseData;

import com.example.CustomerDB.entity.*;
import com.example.CustomerDB.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class TestCaseData {

    private CustomerRepository customerRepository;


        public static List<CustomerDetails> addCustomerListTest(){
//        List<CustomerDetails> customersList=customerRepository.findAll();
            List<CustomerDetails> customerDetailsList = new ArrayList<>();
        CustomerDetails newCustomer=new CustomerDetails();
            newCustomer.setEmail("samswaroop97@gmail.com");
            newCustomer.setFirstName("Sam");
            newCustomer.setLastName("Swaroop");
            newCustomer.setMobileNumber(98076);
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
            String encryptedData = encryptor.encrypt("swaroop1997");
            newCustomer.setMyPassword(encryptedData);
            customerDetailsList.add(newCustomer);
            return customerDetailsList;
        }

        public static AddressDetails addAddressTest(){
            AddressDetails newAddress=new AddressDetails();

            newAddress.setEmail("samswaroop97@gmail.com");
            newAddress.setType("home");
            newAddress.setCity("Hyderabad");
            newAddress.setLine1("Pushpak Apartments");
            newAddress.setLine2("Nizampet,Hyderabad.");
            newAddress.setState("Telangana");
            newAddress.setPostalCode("500090");
            newAddress.setBillingAddress(true);
            newAddress.setShippingAddress(false);

            return newAddress;
        }

        public static ProductDetails addProductTest(){
            ProductDetails newProduct= new ProductDetails();

            newProduct.setEmail("samswaroop97@gmail.com");
            newProduct.setProductName("Nike Sneakers");
            newProduct.setProductCode("p1");
            newProduct.setDescription("Good low ankle sneakers by nike");

            return newProduct;
        }

    public static List<ProductDetails> addProductListTest(){
        ProductDetails newProduct= new ProductDetails();
        List<ProductDetails> r=new ArrayList<>();

        newProduct.setEmail("samswaroop97@gmail.com");
        newProduct.setProductName("Nike Sneakers");
        newProduct.setProductCode("p1");
        newProduct.setDescription("Good low ankle sneakers by nike");

        r.add(newProduct);
        return r;
    }

        public static SKUDetails addSKUTest(){
            SKUDetails newSKU=new SKUDetails();

            newSKU.setSize("M");
            newSKU.setSkuCode("sku1");
            newSKU.setProductCode("p1");

            return newSKU;
        }

        public static  List<SKUDetails> addSKUListTest(){
            List<SKUDetails> newSKUList=new ArrayList<>();
            SKUDetails newSKU=new SKUDetails();
            newSKU.setSize("M");
            newSKU.setSkuCode("sku1");
            newSKU.setProductCode("p1");
            newSKUList.add(newSKU);
            return newSKUList;
        }

        public static SKUPriceDetails addSKUPrice(){
            SKUPriceDetails newSKUPrice=new SKUPriceDetails();

            newSKUPrice.setSkuCode("sku1");
            newSKUPrice.setPrice("20000");
            newSKUPrice.setCurrency("dollars");

            return newSKUPrice;
        }


}
