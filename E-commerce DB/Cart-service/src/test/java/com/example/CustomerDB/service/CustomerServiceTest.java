package com.example.CustomerDB.service;

import com.example.CustomerDB.testCaseData.TestCaseData;
import com.example.CustomerDB.entity.CustomerDetails;
import com.example.CustomerDB.repository.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    public CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    AddressRepository addressRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    SKURepository skuRepository;

    @Mock
    SKUPriceRepository skuPriceRepository;



    @Test
    public void CustomerRegistrationTest() {
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


        assertEquals("Customer added successfully",customerService.addCustomer(newCustomer));
    }

    @Test
    public void LoginTest(){
        when(customerRepository.findAll()).thenReturn(TestCaseData.addCustomerListTest());
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body("Valid User"),customerService.loginApi("samswaroop97@gmail.com","swaroop1997"));
    }

    @Test
    public void AddressTest(){
        when(customerRepository.findAll()).thenReturn(TestCaseData.addCustomerListTest());
        assertEquals("Address Successfully added!!!",customerService.addAddress("samswaroop97@gmail.com",TestCaseData.addAddressTest()));
    }

    @Test
    public void ProductTest(){
        when(customerRepository.findAll()).thenReturn(TestCaseData.addCustomerListTest());
        assertEquals("First product added successfully",customerService.addProducts("samswaroop97@gmail.com",TestCaseData.addProductTest()));
    }

    @Test
    public void SKUTest(){
        when(customerRepository.findAll()).thenReturn(TestCaseData.addCustomerListTest());
        when(productRepository.findAll()).thenReturn(TestCaseData.addProductListTest());
        assertEquals("Successfully added SKU",customerService.addSKU("p1","samswaroop97@gmail.com",TestCaseData.addSKUTest()));

    }

    @Test
    public void SKUPriceTest(){
        when(customerRepository.findAll()).thenReturn(TestCaseData.addCustomerListTest());
        when(skuRepository.findAll()).thenReturn(TestCaseData.addSKUListTest());
        assertEquals("Successfully added SKU price",customerService.addSKUPrice("p1","samswaroop97@gmail.com",TestCaseData.addSKUPrice()));
    }


}