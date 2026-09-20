package myapps.src.main.java.com.example.myapps;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "Kaushal");
        model.addAttribute("age", 25);
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("name", "Kaushal");
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @PostMapping("/contact")
    public String handleContact(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String message,
            Model model) {

        // Console में print करें (debugging के लिए)
        System.out.println("=== New Contact Message ===");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Message: " + message);

        // Success message के लिए
        model.addAttribute("success", true);
        return "contact";
    }
}