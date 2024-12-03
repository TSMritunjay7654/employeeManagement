package com.codesuttlle.EmployeeDetails.service;
import com.codesuttlle.EmployeeDetails.dto.EmployeeDto;
import com.codesuttlle.EmployeeDetails.entities.EmployeeEntity;
import com.codesuttlle.EmployeeDetails.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDto> getEmployeeById(Long getById) {
        return employeeRepository.findById(getById).map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDto.class));
    }

    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeEntity> employeeEntity=employeeRepository.findAll();
        return employeeEntity.stream()
                .map(employeeEntities -> modelMapper.map(employeeEntities,EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto setNewEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity=modelMapper.map(employeeDto,EmployeeEntity.class);
        EmployeeEntity saveEmployeeEntity=employeeRepository.save(employeeEntity);
        return modelMapper.map(saveEmployeeEntity,EmployeeDto.class);

    }

    public EmployeeDto updateEmployee(Long Id,EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity=modelMapper.map(employeeDto, EmployeeEntity.class);
        employeeEntity.setId(Id);
        EmployeeEntity saveEmployeeEntity=employeeRepository.save(employeeEntity);
        return modelMapper.map(saveEmployeeEntity,EmployeeDto.class);
    }

    public boolean isExists(Long Id){
        return employeeRepository.existsById(Id);
    }
    public boolean deleteEmployeeById(Long Id) {
        boolean exists=isExists(Id);
        if(!exists)return false;
        employeeRepository.deleteById(Id);
        return true;
    }

    public EmployeeDto updateParticularEmployeeId(Long id, Map<String, Object> updates) {
        boolean exists=isExists(id);
        if(!exists) return null;
        EmployeeEntity employeeEntity=employeeRepository.findById(id).get();
        updates.forEach((filed,value)->{
            Field updatedField= ReflectionUtils.findRequiredField(EmployeeEntity.class,filed);
                updatedField.setAccessible(true);
                ReflectionUtils.setField(updatedField,employeeEntity,value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDto.class);
    }
}
