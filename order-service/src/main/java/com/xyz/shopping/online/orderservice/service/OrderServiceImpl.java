package com.xyz.shopping.online.orderservice.service;

import com.xyz.shopping.online.orderservice.entity.CallCenter;
import com.xyz.shopping.online.orderservice.repository.CallCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CallCenterRepository callCenterRepository;

    @Override
    public Optional<CallCenter> getCallCenterDetails(long id) {
        return callCenterRepository.findById(id);
    }

    @PostConstruct
    public void createCallCenter() {
        CallCenter callCenter = new CallCenter();
        callCenter.setPhoneNumber("+6581630557");
        callCenter.setCity("Singapore");
        callCenter.setAddressLine1("Block 175D");
        callCenter.setAddressLine2("Punggol Field");
        callCenterRepository.save(callCenter);
    }
}
