package dw.wholesale_company.service;

import dw.wholesale_company.model.OrderDetail;
import dw.wholesale_company.repository.OrderDetailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getOrderDetailAll() { return orderDetailRepository.findAll(); }
}
