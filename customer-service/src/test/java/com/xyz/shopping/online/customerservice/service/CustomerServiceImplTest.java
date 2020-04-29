package com.xyz.shopping.online.customerservice.service;

import com.xyz.shopping.online.customerservice.entity.Customer;
import com.xyz.shopping.online.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService = new CustomerServiceImpl();

    private Customer customer;

    @BeforeEach
    public void setUp(){
        customer = new Customer();
        customer.setFirstName("abc");
        customer.setMiddleName("def");
        customer.setLastName("ghk");
        customer.setCity("Singapore");
        customer.setCountry("Singapore");
    }

    @Test
    public void getCustomer(){
        Optional<Customer> optCustomer = Optional.of(customer);
        when(customerRepository.findById(anyLong())).thenReturn(optCustomer);
        Optional<Customer> optCustomer1 = customerService.getCustomer(1l);
        assertEquals(customer, optCustomer1.get());
    }

    @Test
    public void saveCustomer(){
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer customer1 = customerService.saveCustomer(customer);
        assertEquals(customer, customer1);
    }
}
