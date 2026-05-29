package com.example.demo.service.impl;

import com.example.demo.dto.AddStudentDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> new StudentDto(student.getId(),student.getName(),student.getEmail()))
                .toList();
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student not found with ID: "+studentId));
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentDto addStudentDto) {
        Student newStudent = modelMapper.map(addStudentDto, Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        if(!studentRepository.existsById(id))
        {
            throw new IllegalArgumentException("Student doesn't exist by id: "+id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentDto addStudentDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: "+id));
        modelMapper.map(addStudentDto,student);

        student = studentRepository.save(student);
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(Long id, Map<String, Object> updates) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: "+id));

        updates.forEach((field, value)->{
            switch (field)
            {
                case "name":
                    student.setName((String) value);
                    break;
                case "email":
                    student.setEmail((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field Not Supported");
            }
        });
        Student saveStudent = studentRepository.save(student);
        return modelMapper.map(saveStudent,StudentDto.class);
    }
}
