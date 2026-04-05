package tusharydv.springBootWebTutorial.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import tusharydv.springBootWebTutorial.dto.EmployeeDTO;
import tusharydv.springBootWebTutorial.entities.EmployeeEntity;
import tusharydv.springBootWebTutorial.repositories.EmployeeRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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


    public Optional<EmployeeDTO> getEmployeeById(Long id){
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
//        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeeDTO.class));


        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class));
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
    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO){
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity=employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }

    public boolean isExistByEmployeeId(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean exists=employeeRepository.existsById(employeeId);
        if(!exists) return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }
    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates){
        boolean exists = isExistByEmployeeId(employeeId);
        if(!exists) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) -> {
            try {
                Field fieldToBeUpdated =
                        EmployeeEntity.class.getDeclaredField(field);

                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);

            } catch (NoSuchFieldException e) {
                throw new RuntimeException("Invalid field: " + field);
            }
        });

        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}
