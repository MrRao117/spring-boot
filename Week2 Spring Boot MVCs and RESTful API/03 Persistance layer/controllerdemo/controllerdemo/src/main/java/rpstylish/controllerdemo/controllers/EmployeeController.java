package rpstylish.controllerdemo.controllers;

import org.springframework.web.bind.annotation.*;
import rpstylish.controllerdemo.dto.EmployeeDTO;
import rpstylish.controllerdemo.entities.EmployeeEntity;
import rpstylish.controllerdemo.repositories.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping(path = "employees")
public class EmployeeController {


//    @GetMapping(path="getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "Secret Message: asdfg@sdcvb54";
//    }

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name ="employeeId") Long id){
        return employeeRepository.findById(id).orElse(null);
    }
    @GetMapping
    public List<EmployeeEntity> getAllEmployee(@RequestParam(required = false, name = "InputAge") Integer age,
                                 @RequestParam(required = false) String sortBy){
        return employeeRepository.findAll();
    }
    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
        return employeeRepository.save(inputEmployee);
    }

    @PutMapping String updateEmployeeById(){
        return "Hello from Put";
    }
}
