package com.xyz.shopping.online.productservice.controller;

import com.xyz.shopping.online.productservice.dto.ProductDetailsLog;
import com.xyz.shopping.online.productservice.dto.ProductSearchLog;
import com.xyz.shopping.online.productservice.entity.Product;
import com.xyz.shopping.online.productservice.service.ProductAnalyticsService;
import com.xyz.shopping.online.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductAnalyticsService productAnalyticsService;

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public Page<Product> searchProducts(@RequestParam(value = "search") String searchCriteria,
                                        @RequestParam(value = "userId") String userId, Pageable pageRequest){
        logger.info("Searching Products for criteria {} and page request {}", searchCriteria, pageRequest.getPageNumber());
        ProductSearchLog productSearchLog = new ProductSearchLog();
        productSearchLog.setSearchText(searchCriteria);
        productSearchLog.setUserId(userId);
        productAnalyticsService.logProductSearch(productSearchLog);
        return productService.searchProducts(searchCriteria, pageRequest);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductDetails(@PathVariable(name = "id") Long id,
                                                     @RequestParam(value = "userId") String userId){
        Optional<Product> optProduct = productService.getProductDetails(id);
        if(optProduct.isPresent()){
            Product product = optProduct.get();
            ProductDetailsLog productDetailsLog = new ProductDetailsLog();
            productDetailsLog.setProductId(product.getId());
            productDetailsLog.setProductName(product.getName());
            productDetailsLog.setUserId(userId);
            productAnalyticsService.logProductDetails(productDetailsLog);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = "application/json")
    public Product createProduct(@RequestBody Product product){
        logger.info("Creating Product {}", product);
        return productService.createProduct(product);
    }
}
