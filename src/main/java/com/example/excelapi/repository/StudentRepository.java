package com.example.excelapi.repository;

import com.example.excelapi.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface StudentRepository extends JpaRepository<Student,Integer>{
@Query(value = "SELECT name, TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) AS age," +
        " ((first_grade + second_grade + third_grade)/3)" +
        " as average FROM studentdb.student ORDER BY age ASC;"
        , nativeQuery = true)
ArrayList<?> orderStudents();
}
