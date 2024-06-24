package com.dw.demo.service;

import com.dw.demo.exception.ResourceNotFoundException;
import com.dw.demo.model.Purchase;
import com.dw.demo.model.User;
import com.dw.demo.repository.PurchaseRepository;
import com.dw.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    UserRepository userRepository;

    public Purchase savePurchase(Purchase purchase) {
        //구매확정 바로 직전, 현재시간을 저장함
        purchase.setPurchaseTime(LocalDateTime.now());
        return purchaseRepository.save(purchase);
    }

    public List<Purchase> savePurchaseList(List<Purchase> purchaseList) {
        List<Purchase> savedPurchaseList = purchaseList.stream()
                .map((purchase)->{
                    //구매확정 바로 직전, 현재시간을 저장함
                    purchase.setPurchaseTime(LocalDateTime.now());
                    return purchaseRepository.save(purchase);
                })
                .collect(Collectors.toList());
        return savedPurchaseList;
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public List<Purchase> getPurchaseListByUser(String userId) {
        // 유저아이디로 유저객체 찾기
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User", "ID", userId);
        }
        return purchaseRepository.findByUser(userOptional.get());
    }

    //유저 이름으로 구매한 게임 찾기
    public List<Purchase> getPurchaseListByUserName(String userName) {
        // 유저이름으로 유저객체 찾기
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User", "Name", userName);
        }
        return purchaseRepository.findByUser(userOptional.get());
    }

    // 현재 세션 유저 이름으로 구매한 게임 찾기
    public List<Purchase> getPurchaseListByCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }
        String userId = authentication.getName();
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User", "ID", userId);
        }
        return purchaseRepository.findByUser(userOptional.get());
    }
}







