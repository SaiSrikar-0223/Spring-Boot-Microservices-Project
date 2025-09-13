package net.javaguides.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.departmentservice.dto.DepartmentDTO;
import net.javaguides.departmentservice.entity.Department;
import net.javaguides.departmentservice.repository.DepartmentRepository;
import net.javaguides.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {

        //convert department dto to department jpa entity

        Department department =new Department(
                departmentDTO.getId(),
                departmentDTO.getDepartmentName(),
                departmentDTO.getDepartmentDescription(),
                departmentDTO.getDepartmentCode()
        );
         Department savedDepartment = departmentRepository.save(department);

         DepartmentDTO savedDepartmentDto = new DepartmentDTO(
                       savedDepartment.getId(),
                       savedDepartment.getDepartmentName(),
                       savedDepartment.getDepartmentDescription(),
                       savedDepartment.getDepartmentCode()
         );
          return savedDepartmentDto;
    }

    @Override
    public DepartmentDTO getDepartmentByCode(String departmentCode) {

        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        DepartmentDTO departmentDTO = new DepartmentDTO(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDTO;
    }
}
