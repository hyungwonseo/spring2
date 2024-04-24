package dw.wholesale_company.controller;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrderAll() {
        return new ResponseEntity<>(orderService.getOrderAll(),
                HttpStatus.OK);
    }

    @GetMapping("/orders/date/after/{date}")
    public ResponseEntity<List<Order>> getOrderByDateAfter(@PathVariable LocalDate date) {
        return new ResponseEntity<>(orderService.getOrderByDateAfter(date),
                HttpStatus.OK);
    }

    @GetMapping("/orders/orderdate/{orderDate}")
    public ResponseEntity<List<Customer>> getCustomerByOrderDate(@PathVariable LocalDate orderDate) {
        return new ResponseEntity<>(orderService.getCustomerByOrderDate(orderDate),
                HttpStatus.OK);
    }
}
