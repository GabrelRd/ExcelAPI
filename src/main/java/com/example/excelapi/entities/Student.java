package com.example.excelapi.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student{
    @Id @Setter
    private Integer id;
    @Column(length = 200, nullable = false)
    private String name;
    @Column(length = 2, nullable = false)
    private String gender;
    @Column(nullable = false)
    private Date birthDate;
    @Column(nullable = false)
    private Short firstGrade;
    @Column(nullable = false)
    private Short secondGrade;
    @Column(nullable = false)
    private Short thirdGrade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Short getFirstGrade() {
        return firstGrade;
    }

    public void setFirstGrade(Short firstGrade) {
        this.firstGrade = firstGrade;
    }

    public Short getSecondGrade() {
        return secondGrade;
    }

    public void setSecondGrade(Short secondGrade) {
        this.secondGrade = secondGrade;
    }

    public Short getThirdGrade() {
        return thirdGrade;
    }

    public void setThirdGrade(Short thirdGrade) {
        this.thirdGrade = thirdGrade;
    }
}
