package dw.wholesale_company.controller;

import dw.wholesale_company.model.Department;
import dw.wholesale_company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getDepartmentAll() {
        return new ResponseEntity<>(departmentService.getDepartmentAll(),
                HttpStatus.OK);
    }
}
