package tusharydv.springBootWebTutorial.Controllers;


import org.springframework.web.bind.annotation.*;
import tusharydv.springBootWebTutorial.dto.EmployeeDTO;
//import tusharydv.springBootWebTutorial.SpringBootWebTutorialApplication.EmployeeEntity;
import tusharydv.springBootWebTutorial.repositories.EmployeeRepository;
import tusharydv.springBootWebTutorial.entities.EmployeeEntity;
import tusharydv.springBootWebTutorial.services.EmployeeService;

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

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name ="employeeId") Long id){
        return employeeService.getEmployeeById(id);
    }
    @GetMapping
    public List<EmployeeDTO> getAllEmployee(@RequestParam(required = false, name = "InputAge") Integer age,
                                               @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployees();
    }
    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping String updateEmployeeById(){
        return "Hello from Put";
    }
}

