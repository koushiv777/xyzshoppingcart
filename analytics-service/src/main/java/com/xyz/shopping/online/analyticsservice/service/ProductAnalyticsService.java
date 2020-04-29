package com.xyz.shopping.online.analyticsservice.service;

import com.xyz.shopping.online.analyticsservice.entity.ProductDetailsLog;
import com.xyz.shopping.online.analyticsservice.entity.ProductSearchLog;

public interface ProductAnalyticsService {
    ProductDetailsLog logProductDetails(ProductDetailsLog productDetailsLog);
    ProductSearchLog logProductSearch(ProductSearchLog productSearchLog);
}
