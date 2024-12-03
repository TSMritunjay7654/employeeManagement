package com.codesuttlle.EmployeeDetails.controller;

import com.codesuttlle.EmployeeDetails.dto.EmployeeDto;

import com.codesuttlle.EmployeeDetails.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
  import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(path = "/{getById}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long getById){
        Optional<EmployeeDto> employeeDto=employeeService.getEmployeeById(getById);
        return employeeDto.map(employeeDto1 -> ResponseEntity.ok(employeeDto1))
                .orElseThrow(()->new NoSuchElementException("Employee Not Found"));
    }



    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy ){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> setNewEmployee(@RequestBody @Valid EmployeeDto employeeDto)
    {
        EmployeeDto employeeDto1= employeeService.setNewEmployee(employeeDto);
        return new ResponseEntity<>(employeeDto1, HttpStatus.CREATED);
    }

    @PutMapping(path ="/{Id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long Id){
        return ResponseEntity.ok(employeeService.updateEmployee(Id,employeeDto));
    }

    @DeleteMapping(path = "/{Id}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long Id){
         return ResponseEntity.ok(employeeService.deleteEmployeeById(Id));
    }

    @PatchMapping(path = "/{Id}")
    public ResponseEntity<EmployeeDto> updateParticularEmployeeId(@RequestBody Map<String, Object> updates, @PathVariable Long Id){
        EmployeeDto employeeDto=employeeService.updateParticularEmployeeId(Id, updates);
        if(employeeDto==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDto);
    }
}
