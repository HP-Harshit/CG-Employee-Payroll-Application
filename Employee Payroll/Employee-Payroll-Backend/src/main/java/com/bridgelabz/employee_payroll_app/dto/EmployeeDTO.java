package com.bridgelabz.employee_payroll_app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class EmployeeDTO {
    private Long id;

    @NotEmpty(message = "Name is a required field and cannot be empty.")
    @Pattern(regexp = "^[A-Z][a-zA-Z ]*$", message = "Name must start with a capital letter and contain only alphabets and spaces.")
    private String name;

    private Double salary;

    @NotEmpty(message = "Gender is a required field and cannot be empty.")
    private String gender;

    @PastOrPresent(message = "Start date must be a past or present date.")
    private LocalDate startDate;

    @NotNull(message = "Day is required.")
    private Integer day;

    @NotEmpty(message = "Month is required.")
    private String month;

    @NotNull(message = "Year is required.")
    private Integer year;

    @NotBlank(message = "Note cannot be blank.")
    private String note;

    @NotBlank(message = "Profile picture cannot be blank.")
    private String profilePic;

    @NotEmpty(message = "Department is a required field and cannot be empty.")
    private List<String> department;
}
