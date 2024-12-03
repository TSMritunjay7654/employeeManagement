package com.codesuttlle.EmployeeDetails.dto;
import com.codesuttlle.EmployeeDetails.customAnnotation.EmailValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    @NotBlank(message = "Name should not be blank")
    @Size(min = 3,max = 20, message = "Give the name as per the specified size")
    private String name;
    @Email(message = "Enter the valid mail")
    @EmailValidation(message = "Email already in use")
    private String email;
    @NotNull(message = "age should not be null")
    private Integer age;
    @NotBlank(message = "Role cannot be blank")
    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role must be either USER or ADMIN")
    private String role;
    @Positive(message = "salary must be a positive number")
    @NotNull(message = "salary should not be null")
    @Digits(integer = 30,fraction = 2)
    private Double salary;
    @JsonProperty("isActive")
    private boolean isActive;
}
