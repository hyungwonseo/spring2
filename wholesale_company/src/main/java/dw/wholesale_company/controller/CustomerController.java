package dw.wholesale_company.controller;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomerAll() {
        return new ResponseEntity<>(customerService.getCustomerAll(),
                HttpStatus.OK);
    }

    @GetMapping("/customers/highmilethanavg")
    public ResponseEntity<List<Customer>> getCustomerWithHighMileThanAvg() {
        return new ResponseEntity<>(customerService.getCustomerWithHighMileThanAvg(),
                HttpStatus.OK);
    }

    @GetMapping("/customers/grade/{grade}")
    public ResponseEntity<List<Customer>> getCustomerByMileageGrade(@PathVariable String grade) {
        return new ResponseEntity<>(customerService.getCustomerByMileageGrade(grade),
                HttpStatus.OK);
    }
}
