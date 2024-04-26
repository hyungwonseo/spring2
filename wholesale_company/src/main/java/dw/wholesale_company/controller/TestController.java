package dw.wholesale_company.controller;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Employee;
import dw.wholesale_company.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    TestService testService;
    @GetMapping("/test/1/{city}")
    public ResponseEntity<List<Employee>> getCustomerByCity(@PathVariable String city) {
        return ResponseEntity.ok(testService.getEmployeeByCity(city));
    }
    @GetMapping("/test/2/{id}")
    public ResponseEntity<Customer> getCustomerByOrderId(@PathVariable String id) {
        return ResponseEntity.ok(testService.getCustomerByOrderId(id));
    }

    @GetMapping("/test/3/{orderYear}")
    public ResponseEntity<Integer> getOrderNumByOrderYear(@PathVariable int orderYear) {
        return ResponseEntity.ok(testService.getOrderNumByOrderYear(orderYear));
    }
    @GetMapping("/test/4/{position}/{year}")
    public ResponseEntity<List<Employee>> getEmployeeByPositionAndYear(@PathVariable String position, @PathVariable int year) {
        return ResponseEntity.ok(testService.getEmployeeByPositionAndYear(position, year));
    }
}
