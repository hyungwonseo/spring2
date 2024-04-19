package dw.firstapp.service;

import dw.firstapp.exception.ResourceNotFoundException;
import dw.firstapp.model.Employee;
import dw.firstapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    // 의존성주입
    @Autowired
    EmployeeRepository employeeRepository;
    public Employee saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            // 예외처리
            throw new ResourceNotFoundException("Employee", "ID", id);
        } else {
            return employee.get();
        }
    }

    public Employee updateEmployeeById(long id, Employee employee) {
        // ID로 해당 데이터 찾기
        Optional<Employee> employee1 = employeeRepository.findById(id);
        if (employee1.isPresent()) {
            // 데이터 업데이트
            employee1.get().setEmail(employee.getEmail());
            employee1.get().setFirstName(employee.getFirstName());
            employee1.get().setLastName(employee.getLastName());
            // 실제로 DB에 저장하기
            employeeRepository.save(employee1.get());

            return employee1.get();
        }else {
            // 예외처리
            throw new ResourceNotFoundException("Employee", "ID", id);
        }
    }

    public Employee deleteEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
            return employee.get();
        } else {
            // 예외처리
            throw new ResourceNotFoundException("Employee", "ID", id);
        }
    }
}








