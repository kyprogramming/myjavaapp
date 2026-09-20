package myapps.src.main.java.com.example.myapps;

import myapps.src.main.java.com.example.myapps.contact.ContactForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MyController {

    // ═══════════════════════════════════════
    // HOME
    // ═══════════════════════════════════════
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "Kaushal");
        model.addAttribute("age", 25);
        return "index";
    }

    // ═══════════════════════════════════════
    // ABOUT
    // ═══════════════════════════════════════
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("name", "Kaushal");
        return "about";
    }

    // ═══════════════════════════════════════
    // CONTACT — Show Form
    // ═══════════════════════════════════════
    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contact";
    }

    // ═══════════════════════════════════════
    // CONTACT — Handle Submit (with validation)
    // ═══════════════════════════════════════
    @PostMapping("/contact")
    public String handleContact(
            @Valid @ModelAttribute("contactForm") ContactForm contactForm,
            BindingResult result,
            Model model) {

        // अगर validation errors हैं तो form पर वापस
        if (result.hasErrors()) {
            return "contact";
        }

        // Log to console (for debugging / demo)
        System.out.println("═══════════════════════════════════");
        System.out.println("New Contact Message");
        System.out.println("═══════════════════════════════════");
        System.out.println("Name:    " + contactForm.getName());
        System.out.println("Email:   " + contactForm.getEmail());
        System.out.println("Message: " + contactForm.getMessage());
        System.out.println("═══════════════════════════════════");

        // Success message
        model.addAttribute("success", true);

        // Form clear करने के लिए fresh object
        model.addAttribute("contactForm", new ContactForm());

        return "contact";
    }
}