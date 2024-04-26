package dw.wholesale_company.repository;

import dw.wholesale_company.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findByCity(String city);
}
