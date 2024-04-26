package dw.wholesale_company.service;

import dw.wholesale_company.exception.ResourceNotFoundException;
import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Mileage;
import dw.wholesale_company.repository.CustomerRepository;
import dw.wholesale_company.repository.MileageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    MileageRepository mileageRepository;

    public List<Customer> getCustomerAll() { return customerRepository.findAll(); }

    //고객 전체의 평균마일리지보다 평균마일리지가 큰 고객 정보
    public List<Customer> getCustomerWithHighMileThanAvg() {
        List<Customer> customers = customerRepository.findAll();
        int sum = 0;
        for (int i=0; i<customers.size(); i++) {
            sum = sum + customers.get(i).getMileage();
        }
        Double avg = (double)sum / (double)customers.size();
        return customers.stream().filter(c->c.getMileage() > avg)
                .collect(Collectors.toList());
    }

    //마일리지 등급명별로 고객의 정보를 보이시오.
    public List<Customer> getCustomerByMileageGrade(String grade) {
        Optional<Mileage> mileageOptional = mileageRepository.findById(grade);
        if (mileageOptional.isEmpty()) {
            throw new ResourceNotFoundException("Mileage", "Grade", grade);
        }
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().filter(customer ->
                    customer.getMileage() >= mileageOptional.get().getLowLimit()
                    && customer.getMileage() <= mileageOptional.get().getHighLimit())
                .collect(Collectors.toList());
    }
}
