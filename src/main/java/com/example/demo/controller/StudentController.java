package com.example.demo.controller;

import com.example.demo.dto.AddStudentDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){

        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long studentId){
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @PostMapping
    public ResponseEntity<StudentDto> createNewStudent(@RequestBody @Valid AddStudentDto addStudentDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAStudent(@PathVariable Long id)
    {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id,
                                                    @RequestBody AddStudentDto addStudentDto)
    {
        return ResponseEntity.ok(studentService.updateStudent(id,addStudentDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> updatePartialStudent(@PathVariable Long id, @RequestBody Map<String, Object> updates)
    {
        return ResponseEntity.ok(studentService.updatePartialStudent(id,updates));

    }

}