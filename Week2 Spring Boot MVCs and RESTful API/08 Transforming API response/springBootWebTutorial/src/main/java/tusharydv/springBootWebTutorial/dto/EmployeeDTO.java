package tusharydv.springBootWebTutorial.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tusharydv.springBootWebTutorial.annotations.EmployeeRoleValidation;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message="Name can not be blank")
    @Size(min=3, max=20,message = "The name should e in the range:[3,10]")
    private String name;

    @NotBlank(message = "Email of the employee can not be blank")
    @Email(message = "Enter a valid email")
    private String email;

    @NotNull(message = "Age of the employee cannot be blank")
    @Max(value=80,message="Age should be less than 80")
    @Min(value=18, message = "Age should be greater than 18")
    private Integer age;

    @NotBlank(message = "Role of the employee can not be blank")
//    @Pattern(regexp = "^(ADMIN|USER)$",message = "Role of the Employee can either be ADMIN or USER")
    @EmployeeRoleValidation
    private String role;//ADMIN,USER

    @NotNull(message = "salary of the employee can not be null")
    @Positive(message = "salary of the employee should be positive")
    @Digits(integer = 6 , fraction = 2, message = "Salary can be in the form XXXXXX.YY")
    @DecimalMax(value="100000.99")
    @DecimalMin(value="100.50")
    private double salary;

    @PastOrPresent(message = "Date of joining field in Employee cannot be in the future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;


}

