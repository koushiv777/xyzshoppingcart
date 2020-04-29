package com.xyz.shopping.online.customerservice.controller;

import com.xyz.shopping.online.customerservice.entity.Customer;
import com.xyz.shopping.online.customerservice.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

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
    public void getCustomer() throws Exception {
        Optional<Customer> optCustomer = Optional.of(customer);
        when(customerService.getCustomer(1l)).thenReturn(optCustomer);

        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(customer.getFirstName())));
    }

    @Test
    public void saveCustomer() throws Exception {

        String customerJson = "{\n" +
                "  \"firstName\": \"abc\",\n" +
                "  \"middleName\": \"def\",\n" +
                "  \"lastName\": \"ghk\",\n" +
                "  \"city\": \"Singapore\",\n" +
                "  \"country\": \"Singapore\"\n" +
                "}";

        when(customerService.saveCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(customer.getFirstName())));
    }
}
