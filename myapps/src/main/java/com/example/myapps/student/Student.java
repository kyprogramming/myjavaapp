package myapps.src.main.java.com.example.myapps.student;

import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age must be at least 1")
    @Max(value = 120, message = "Age must not exceed 120")
    private Integer age;                   // ⬅️ int से Integer किया

    @NotBlank(message = "Course is required")
    @Size(min = 2, max = 50, message = "Course must be between 2 and 50 characters")
    private String course;

    public Student() {
    }

    public Student(String name, String email, Integer age, String course) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.course = course;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAge() { return age; }        // ⬅️ Integer
    public void setAge(Integer age) { this.age = age; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
}