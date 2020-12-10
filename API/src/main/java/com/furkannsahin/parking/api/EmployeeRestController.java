package com.furkannsahin.parking.api;

import com.furkannsahin.parking.dto.EmployeeDto;
import com.furkannsahin.parking.mapper.EmployeeMapper;
import com.furkannsahin.parking.model.Employee;
import com.furkannsahin.parking.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@Api(value = "Employee Controller")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "https://reactbootcamp66-bitirme-odevi-furk2sahin.netlify.app"})
public class EmployeeRestController {

    @Autowired
    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    @ApiOperation(value = "Get all employees", notes ="returns all employees")
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getEmployees(){
        List<Employee> employees = employeeService.getEmployees();
        return ResponseEntity.ok(employeeMapper.toEmployeeDtos(employees));
    }

    @ApiOperation(value = "Create an employee", notes ="Creates an employee and returns it")
    @PostMapping("/employees")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        try{
            employeeDto.setPassword(new String(Base64.getEncoder().encodeToString(employeeDto.getPassword().getBytes())));
            Employee createdEmployee = employeeService.createEmployee(employeeMapper.toEmployee(employeeDto));
            return ResponseEntity.ok(employeeMapper.toEmployeeDto(createdEmployee));
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Update an employee", notes = "Updates an employee")
    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDto employeeDto){
        try{
            employeeService.updateEmployee(id, employeeMapper.toEmployee(employeeDto));
            return new ResponseEntity<>("Employee updated with id: " + id, HttpStatus.OK);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Delete an employee", notes = "Deletes an employee by given id")
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
        try{
            employeeService.deleteEmployee(id);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>("Employee Successfully deleted by id: " + id, HttpStatus.OK);
    }

}
