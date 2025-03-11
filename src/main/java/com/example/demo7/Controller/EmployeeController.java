package com.example.demo7.Controller;

import com.example.demo7.Entity.EmployeeEntity;
import com.example.demo7.Services.EmployeeServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/employees")
public class EmployeeController
{
//      List<Integer> l1 = new ArrayList<>();
      private final EmployeeServices employeeServices;

      @GetMapping("/getAllDetails")
      public List<EmployeeEntity> getAllDetails()
      {
            System.out.println("GetAllDetails called");
            return employeeServices.getALlDetails();

      }
      @GetMapping("/getById/{id}")
      public Optional<EmployeeEntity> getById(@PathVariable Integer id)
      {
           System.out.println("getById called");
           return employeeServices.getById(id);
     }
    @PostMapping("/post")
    public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeEntity emp) {
        EmployeeEntity savedEmployee = employeeServices.addDetails(emp);
        System.out.println("savedEmployee" +"  "+ savedEmployee);
        return ResponseEntity.ok(savedEmployee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeEntity updatedEmployee) {
        EmployeeEntity updatedEmp = employeeServices.updateDetails(id, updatedEmployee);
        return ResponseEntity.ok(updatedEmp);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
        System.out.println("delete called");
        try {
            String message = employeeServices.deleteEmployee(id);
            return ResponseEntity.ok(message);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }


    @PatchMapping("/patch/{id}")
    public ResponseEntity<EmployeeEntity> patchEmployee(@PathVariable Integer id, @RequestBody EmployeeEntity updatedFields) {
        EmployeeEntity patchedEmployee = employeeServices.patchDetails(id, updatedFields);
        return ResponseEntity.ok(patchedEmployee);
    }





}