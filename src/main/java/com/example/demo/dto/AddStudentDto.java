package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AddStudentDto {

    @NotBlank(message = "Name is Required")
    private String name;

    @Email
    @NotBlank(message = "Email is Required")
    private String email;
}
