package com.codesuttlle.EmployeeDetails.customAnnotation;

import com.codesuttlle.EmployeeDetails.repository.EmployeeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {

@Autowired
    private EmployeeRepository employeeRepository;

@Override
public void initialize(EmailValidation emailValidation){

}
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if(email==null|| email.isEmpty()){
            return true;
        }
        return !employeeRepository.existsByEmail(email);
    }
}
