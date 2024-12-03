package com.codesuttlle.EmployeeDetails.repository;

import com.codesuttlle.EmployeeDetails.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    boolean existsByEmail(String email);
}
