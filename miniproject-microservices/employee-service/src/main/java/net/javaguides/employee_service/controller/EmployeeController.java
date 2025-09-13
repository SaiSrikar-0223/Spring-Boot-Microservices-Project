package net.javaguides.employee_service.controller;

import lombok.AllArgsConstructor;
import net.javaguides.employee_service.dto.APIResponseDto;
import net.javaguides.employee_service.dto.EmployeeDto;
import net.javaguides.employee_service.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    //Build Save Employee Rest API
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
      EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
      return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //Build get Employee Rest API
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployee(@PathVariable("id") Long employeeId){
        APIResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);
        return  new ResponseEntity<>(apiResponseDto,HttpStatus.OK);
    }
}
