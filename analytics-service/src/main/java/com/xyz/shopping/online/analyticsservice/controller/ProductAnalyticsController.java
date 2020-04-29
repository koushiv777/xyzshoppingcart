package com.xyz.shopping.online.analyticsservice.controller;

import com.xyz.shopping.online.analyticsservice.entity.ProductDetailsLog;
import com.xyz.shopping.online.analyticsservice.entity.ProductSearchLog;
import com.xyz.shopping.online.analyticsservice.service.ProductAnalyticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/analytics")
public class ProductAnalyticsController {
    Logger logger = LoggerFactory.getLogger(ProductAnalyticsController.class);

    @Autowired
    private ProductAnalyticsService productAnalyticsService;

    @RequestMapping(path = "/log/productDetails", method = RequestMethod.POST, consumes = "application/json")
    public ProductDetailsLog logProductDetails(@RequestBody ProductDetailsLog productDetailsLog){
        logger.info("Logging Product Details {}", productDetailsLog);
        return productAnalyticsService.logProductDetails(productDetailsLog);
    }

    @RequestMapping(path = "/log/productSearch", method = RequestMethod.POST, consumes = "application/json")
    public ProductSearchLog logProductSearch(@RequestBody ProductSearchLog productSearchLog){
        logger.info("Logging Product Search {}", productSearchLog);
        return productAnalyticsService.logProductSearch(productSearchLog);
    }
}
