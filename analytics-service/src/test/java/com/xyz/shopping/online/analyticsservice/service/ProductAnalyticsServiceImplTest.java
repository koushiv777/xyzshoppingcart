package com.xyz.shopping.online.analyticsservice.service;

import com.xyz.shopping.online.analyticsservice.entity.ProductDetailsLog;
import com.xyz.shopping.online.analyticsservice.entity.ProductSearchLog;
import com.xyz.shopping.online.analyticsservice.repository.ProductDetailsLogRepository;
import com.xyz.shopping.online.analyticsservice.repository.ProductSearchLogRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductAnalyticsServiceImplTest {

    @Mock
    private ProductDetailsLogRepository productDetailsLogRepository;

    @Mock
    private ProductSearchLogRepository productSearchLogRepository;

    @InjectMocks
    private ProductAnalyticsService productAnalyticsService = new ProductAnalyticsServiceImpl();

    private ProductDetailsLog productDetailsLog;

    private ProductSearchLog productSearchLog;

    @BeforeEach
    public void setUp(){
        productDetailsLog = new ProductDetailsLog();
        productDetailsLog.setProductId(1l);
        productDetailsLog.setProductName("abc");
        productDetailsLog.setUserId("def");

        productSearchLog = new ProductSearchLog();
        productSearchLog.setUserId("def");
        productSearchLog.setSearchText("name:abc");
    }

    @Test
    public void logProductDetails(){
        when(productDetailsLogRepository.save(productDetailsLog)).thenReturn(productDetailsLog);
        ProductDetailsLog productDetailsLog1 = productAnalyticsService.logProductDetails(productDetailsLog);
        Assertions.assertEquals(productDetailsLog, productDetailsLog1);
    }

    @Test
    public void logProductSearch(){
        when(productSearchLogRepository.save(productSearchLog)).thenReturn(productSearchLog);
        ProductSearchLog productSearchLog1 = productAnalyticsService.logProductSearch(productSearchLog);
        Assertions.assertEquals(productSearchLog, productSearchLog1);
    }

}
