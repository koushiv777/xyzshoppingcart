package com.xyz.shopping.online.customerservice.controller;

import com.xyz.shopping.online.customerservice.entity.Customer;
import com.xyz.shopping.online.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

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
        customerRepository.save(customer);

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

        mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(customer.getFirstName())));
    }
}
