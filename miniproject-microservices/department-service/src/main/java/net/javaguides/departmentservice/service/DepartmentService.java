package net.javaguides.departmentservice.service;

import net.javaguides.departmentservice.dto.DepartmentDTO;

public interface DepartmentService {

    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);
    DepartmentDTO getDepartmentByCode(String code);
}
