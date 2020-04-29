package com.xyz.shopping.online.analyticsservice.repository;

import com.xyz.shopping.online.analyticsservice.entity.ProductSearchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSearchLogRepository extends JpaRepository<ProductSearchLog, Long> {
}
