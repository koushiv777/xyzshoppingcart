package com.xyz.shopping.online.customerservice.service;

import com.xyz.shopping.online.customerservice.entity.Customer;

import java.util.Optional;

public interface CustomerService {
    Optional<Customer> getCustomer(Long id);
    Customer saveCustomer(Customer customer);
}
