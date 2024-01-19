package ru.chirkov.nosql.rest;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chirkov.nosql.entity.Employee;
import ru.chirkov.nosql.service.EmployeeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    @Operation(summary = "get by id")
    public Employee getById(@PathVariable Integer id){
        return employeeService.findById(id);
    }

    @GetMapping()
    @Operation(summary = "find all")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @PostMapping()
    @Operation(summary = "create")
    public Employee create(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @PutMapping()
    @Operation(summary = "update")
    public Employee update(@RequestBody Employee employee){
        return employeeService.update(employee);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete")
    public void delete(@PathVariable Integer id){
        employeeService.delete(id);
    }

}
