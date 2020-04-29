package com.xyz.shopping.online.productservice.service;

import com.xyz.shopping.online.productservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {


    Page<Product> searchProducts(String searchCriteria, Pageable pageRequest);

    Product createProduct(Product product);

    Optional<Product> getProductDetails(Long id);
}
