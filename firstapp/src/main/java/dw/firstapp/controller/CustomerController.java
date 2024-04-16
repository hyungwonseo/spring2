package dw.firstapp.controller;

import dw.firstapp.model.고객;
import dw.firstapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customerAll")
    public List<고객> getCustomerAll() {
        return customerService.getCustomerAll();
    }
}
