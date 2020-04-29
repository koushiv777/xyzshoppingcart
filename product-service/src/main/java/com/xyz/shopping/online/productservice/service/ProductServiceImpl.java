package com.xyz.shopping.online.productservice.service;

import com.xyz.shopping.online.productservice.entity.Product;
import com.xyz.shopping.online.productservice.repository.ProductRepository;
import com.xyz.shopping.online.productservice.specifications.ProductSpecificationsBuilder;
import com.xyz.shopping.online.productservice.util.SearchOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductServiceImpl implements ProductService {
    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> searchProducts(String searchCriteria, Pageable pageRequest) {
        ProductSpecificationsBuilder specificationsBuilder = new ProductSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(searchCriteria+",");
        while (matcher.find()){
            specificationsBuilder.with(matcher.group(1), SearchOperation.getSimpleOperation(matcher.group(2).charAt(0)), matcher.group(3));
        }
        Specification<Product> specification = specificationsBuilder.build();
        return productRepository.findAll(specification, pageRequest);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductDetails(Long id) {
        return productRepository.findById(id);
    }
}
