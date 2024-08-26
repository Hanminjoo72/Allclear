package com.project.allclear_course.service;

import com.project.allclear_course.domain.entity.Department;
import com.project.allclear_course.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAllByOrderByIdAsc();  // ID 기준으로 오름차순 정렬
    }

}
