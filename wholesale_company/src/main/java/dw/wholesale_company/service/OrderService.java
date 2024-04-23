package dw.wholesale_company.service;

import dw.wholesale_company.exception.ResourceNotFoundException;
import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
//    @Autowired
//    EntityManager entityManager;

    public List<Order> getOrderAll() { return orderRepository.findAll(); }

    //주문일이 2021년 5월 1일 이후인 주문 정보 얻기
    public List<Order> getOrderByDateAfter(LocalDate date) {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().filter(a -> a.getOrderDate().compareTo(date) > 0)
                .collect(Collectors.toList());
    }
}
