package com.xyz.shopping.online.orderservice.repository;

import com.xyz.shopping.online.orderservice.entity.CallCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallCenterRepository extends JpaRepository<CallCenter, Long> {
}
