package com.example.demo.service;

import com.example.demo.dto.AddStudentDto;
import com.example.demo.dto.StudentDto;

import java.util.List;
import java.util.Map;

public interface StudentService {

    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long studentId);

    StudentDto createNewStudent(AddStudentDto addStudentDto);

    void deleteStudentById(Long id);

    StudentDto updateStudent(Long id, AddStudentDto addStudentDto);

    StudentDto updatePartialStudent(Long id, Map<String, Object> updates);
}
