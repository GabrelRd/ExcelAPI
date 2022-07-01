package com.example.excelapi.service;

import com.example.excelapi.entities.Student;
import com.example.excelapi.helper.ExcelHelper;
import com.example.excelapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void save(MultipartFile file){
        try {
            List<Student> students = ExcelHelper.convertExcelDB(file.getInputStream());
            this.studentRepository.saveAll(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Student> listAll(){
        return this.studentRepository.findAll();
    }

    public ArrayList<?> listFiltered(){
        return this.studentRepository.orderStudents();
    }
}
