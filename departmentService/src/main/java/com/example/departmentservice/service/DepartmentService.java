package com.example.departmentservice.service;

import com.example.departmentservice.dto.ApiResponse;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public ApiResponse save(Department department) {
        departmentRepository.save(department);
        return ApiResponse.builder().message("Saved").success(true).build();
    }
}

