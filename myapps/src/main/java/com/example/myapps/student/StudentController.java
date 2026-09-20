package myapps.src.main.java.com.example.myapps.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // ═══════════════════════════════════════
    // LIST ALL STUDENTS
    // ═══════════════════════════════════════
    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        model.addAttribute("total", students.size());
        return "students/list";
    }

    // ═══════════════════════════════════════
    // SHOW ADD FORM
    // ═══════════════════════════════════════
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("isEdit", false);
        return "students/form";
    }

    // ═══════════════════════════════════════
    // SHOW EDIT FORM
    // ═══════════════════════════════════════
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student id: " + id));
        model.addAttribute("student", student);
        model.addAttribute("isEdit", true);
        return "students/form";
    }

    // ═══════════════════════════════════════
    // SAVE (ADD or UPDATE)
    // ═══════════════════════════════════════
    @PostMapping("/save")
    public String saveStudent(
            @Valid @ModelAttribute("student") Student student,
            BindingResult result,
            Model model) {

        // Validation errors → form पर वापस
        if (result.hasErrors()) {
            model.addAttribute("isEdit", student.getId() != null);
            return "students/form";
        }

        // Duplicate email check (नया student के लिए)
        if (student.getId() == null) {
            Student existing = studentRepository.findByEmail(student.getEmail());
            if (existing != null) {
                result.rejectValue("email", "duplicate", "This email is already registered");
                model.addAttribute("isEdit", false);
                return "students/form";
            }
        }

        // Save
        try {
            studentRepository.save(student);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to save: " + e.getMessage());
            model.addAttribute("isEdit", student.getId() != null);
            return "students/form";
        }

        return "redirect:/students";
    }

    // ═══════════════════════════════════════
    // DELETE
    // ═══════════════════════════════════════
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }
}