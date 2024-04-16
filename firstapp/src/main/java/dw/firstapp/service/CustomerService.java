package dw.firstapp.service;

import dw.firstapp.model.고객;
import dw.firstapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<고객> getCustomerAll() {
        return customerRepository.findAll();
    }
}
