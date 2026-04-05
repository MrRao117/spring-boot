package tusharydv.springBootWebTutorial.Controllers;


import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tusharydv.springBootWebTutorial.dto.EmployeeDTO;
//import tusharydv.springBootWebTutorial.SpringBootWebTutorialApplication.EmployeeEntity;
import tusharydv.springBootWebTutorial.repositories.EmployeeRepository;
import tusharydv.springBootWebTutorial.entities.EmployeeEntity;
import tusharydv.springBootWebTutorial.services.EmployeeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name ="employeeId") Long id){
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);

        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1) ).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(@RequestParam(required = false, name = "InputAge") Integer age,
                                               @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
        return new  ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping (path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, employeeDTO));
    }
    @DeleteMapping(path="/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotDeleted=employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true)
        return ResponseEntity.notFound().build();
    }
    @PatchMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                          @PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId, updates);
        if(employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO); 
    }
}

