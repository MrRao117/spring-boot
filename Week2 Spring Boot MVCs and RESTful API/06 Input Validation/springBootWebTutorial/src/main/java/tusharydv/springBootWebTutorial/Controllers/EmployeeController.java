package tusharydv.springBootWebTutorial.Controllers;


import jakarta.validation.Valid;
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
        Optional<EmployeeDTO> employeeDTO = employeeService.getAllEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    public List<EmployeeDTO> getAllEmployee(@RequestParam(required = false, name = "InputAge") Integer age,
                                            @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployees();
    }
    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping(path ="/{employeeId}")
    public EmployeeDTO updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable long employeeId){
        return employeeService.updateEmployeeById(employeeId, employeeDTO);
    }
    @DeleteMapping(path ="{employeeId}")
    public boolean deleteEmployeeById(@PathVariable long employeeId){
        return employeeService.deleteEmployeeById(employeeId);
    }

    @PatchMapping(path = "/{employeeId}")
    public  EmployeeDTO updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                                  @PathVariable Long employeeId){
        return employeeService.updatePartialEmployeeById(employeeId, updates);
    }
}