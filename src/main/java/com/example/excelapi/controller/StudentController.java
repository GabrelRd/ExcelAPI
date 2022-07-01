package com.example.excelapi.controller;

import com.example.excelapi.entities.Student;
import com.example.excelapi.helper.ExcelHelper;
import com.example.excelapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/students/upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file){
        if(ExcelHelper.checkExcelFormat(file)){
            this.studentService.save(file);
            return ResponseEntity.ok(Map.of("message","File is uploaded and data is saved to db"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only excel format files can be uploaded");
        }
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return this.studentService.listAll();
    }

    @GetMapping("/students/filter")
    public List<?> getStudentsFiltered(){
        return this.studentService.listFiltered();
    }
}
