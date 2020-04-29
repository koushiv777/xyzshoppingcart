package com.xyz.shopping.online.analyticsservice.repository;

import com.xyz.shopping.online.analyticsservice.entity.ProductDetailsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsLogRepository extends JpaRepository<ProductDetailsLog, Long> {
}
