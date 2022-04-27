package com.example.departmentservice.controller;

import com.example.departmentservice.dto.ApiResponse;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DepartmentRepository departmentRepository;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Department department) {
        ApiResponse apiResponse = departmentService.save(department);
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(departmentRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        departmentRepository.deleteById(id);
        return ResponseEntity.ok().body(ApiResponse.builder().message("Deleted").success(true).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
//        return ResponseEntity.ok(new Department(1, "Moliya"));
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isEmpty()) return ResponseEntity.ok().body(ApiResponse.builder().message("Not found department").success(false).build());
        return ResponseEntity.ok().body(departmentOptional.get());
    }
}
