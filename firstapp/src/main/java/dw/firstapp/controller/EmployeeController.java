package dw.firstapp.controller;

import dw.firstapp.model.Employee;
import dw.firstapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    EmployeeService employeeService;

    // 매개변수를 사용한 생성자
    @Autowired // 생성자 주입 (권장하는 방법!!! @Autowired를 생략해도 의존성주입 됨)
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/api/employee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("api/employee")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("api/employee/{id}")
    public Employee getEmployeeById(@PathVariable long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("api/employee/{id}")
    public Employee updateEmployeeById(@PathVariable long id,
                                       @RequestBody Employee employee) {
        return employeeService.updateEmployeeById(id, employee);
    }

    @DeleteMapping("api/employee/{id}")
    public Employee deleteEmployeeById(@PathVariable long id) {
        return employeeService.deleteEmployeeById(id);
    }











}
