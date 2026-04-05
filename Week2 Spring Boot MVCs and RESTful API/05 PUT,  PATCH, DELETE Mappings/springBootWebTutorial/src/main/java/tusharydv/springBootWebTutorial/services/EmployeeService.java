package tusharydv.springBootWebTutorial.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tusharydv.springBootWebTutorial.dto.EmployeeDTO;
import tusharydv.springBootWebTutorial.entities.EmployeeEntity;
import tusharydv.springBootWebTutorial.repositories.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

//import static java.util.stream.Nodes.collect;
import static org.modelmapper.Converters.Collection.map;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public EmployeeDTO getEmployeeById(Long id){
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);

        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }
    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEntity> employeeEntities=employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }
    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee){
        // to check if user is admin
        // log something
        EmployeeEntity toSaveEntity=modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity=employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }
}
