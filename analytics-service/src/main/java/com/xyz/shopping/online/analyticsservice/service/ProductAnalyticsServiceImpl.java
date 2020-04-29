package com.xyz.shopping.online.analyticsservice.service;

import com.xyz.shopping.online.analyticsservice.entity.ProductDetailsLog;
import com.xyz.shopping.online.analyticsservice.entity.ProductSearchLog;
import com.xyz.shopping.online.analyticsservice.repository.ProductDetailsLogRepository;
import com.xyz.shopping.online.analyticsservice.repository.ProductSearchLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductAnalyticsServiceImpl implements ProductAnalyticsService {

    @Autowired
    private ProductDetailsLogRepository productDetailsLogRepository;

    @Autowired
    private ProductSearchLogRepository productSearchLogRepository;

    @Override
    public ProductDetailsLog logProductDetails(ProductDetailsLog productDetailsLog) {
        productDetailsLog.setCreatedDate(LocalDateTime.now());
        return productDetailsLogRepository.save(productDetailsLog);
    }

    @Override
    public ProductSearchLog logProductSearch(ProductSearchLog productSerachLog) {
        productSerachLog.setCreatedDate(LocalDateTime.now());
        return productSearchLogRepository.save(productSerachLog);
    }
}
