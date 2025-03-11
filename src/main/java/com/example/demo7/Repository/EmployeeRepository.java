package com.example.demo7.Repository;

import com.example.demo7.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,String>
{
    Optional<Object> findById(Integer id);

    void deleteById(Integer id);

    //List<EmployeeEntity> findById(Integer id);
}
