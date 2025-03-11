package com.example.demo7.Services;

import com.example.demo7.Entity.EmployeeEntity;
import com.example.demo7.Repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServices {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeEntity> getALlDetails() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();

        return employeeEntityList;
    }
    public Optional<EmployeeEntity> getById(Integer id)
    {
//        List<EmployeeEntity> employeeIdList = employeeRepository.findById();

        return employeeRepository.findById(String.valueOf(id));
    }

    public EmployeeEntity addDetails(EmployeeEntity emp) {
        return employeeRepository.save(emp);
    }




    public EmployeeEntity updateDetails(Integer id, EmployeeEntity updatedEmployee) {
        return employeeRepository.findById(String.valueOf(id))
                .map(existingEmployee -> {
                    existingEmployee.setName(updatedEmployee.getName());
                    existingEmployee.setAge(updatedEmployee.getAge());
                    existingEmployee.setDepartment(updatedEmployee.getDepartment());
                    existingEmployee.setSalary(updatedEmployee.getSalary());
                    return employeeRepository.save(existingEmployee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }
    @Transactional
    public String deleteEmployee(Integer id) {
        Optional<Object> existingEmployee = employeeRepository.findById(id);

        if (existingEmployee.isPresent()) {
            employeeRepository.deleteById(id);
            return "Employee with ID " + id + " deleted successfully.";
        } else {
            throw new RuntimeException("Employee not found with ID: " + id);
        }
    }
    public EmployeeEntity patchDetails(Integer id, EmployeeEntity updatedFields) {
        Optional<Object> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            EmployeeEntity existingEmployee = (EmployeeEntity) optionalEmployee.get(); // Extracting the EmployeeEntity

            if (updatedFields.getName() != null) {
                existingEmployee.setName(updatedFields.getName());
            }
            if (updatedFields.getAge() > 0) { // Ensure age is valid
                existingEmployee.setAge(updatedFields.getAge());
            }
            if (updatedFields.getDepartment() != null) {
                existingEmployee.setDepartment(updatedFields.getDepartment());
            }
            if (updatedFields.getSalary() > 0) { // Ensure salary is valid
                existingEmployee.setSalary(updatedFields.getSalary());
            }

            return employeeRepository.save(existingEmployee); // Save and return updated employee
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

}

