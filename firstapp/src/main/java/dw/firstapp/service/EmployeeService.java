package dw.firstapp.service;

import dw.firstapp.model.Employee;
import dw.firstapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    // 의존성주입
    @Autowired
    EmployeeRepository employeeRepository;
    public Employee saveEmployee(Employee employee) {
        // repository code - save
        employeeRepository.save(employee);
        return employee;
    }
}
