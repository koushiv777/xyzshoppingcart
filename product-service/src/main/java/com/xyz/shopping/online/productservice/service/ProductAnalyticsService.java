package com.xyz.shopping.online.productservice.service;

import com.xyz.shopping.online.productservice.dto.ProductDetailsLog;
import com.xyz.shopping.online.productservice.dto.ProductSearchLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("analytics-service")
public interface ProductAnalyticsService {

    @RequestMapping(path = "/analytics/log/productDetails", method = RequestMethod.POST)
    ProductDetailsLog logProductDetails(ProductDetailsLog productDetailsLog);

    @RequestMapping(path = "/analytics/log/productSearch", method = RequestMethod.POST)
    ProductSearchLog logProductSearch(ProductSearchLog productSearchLog);

}
