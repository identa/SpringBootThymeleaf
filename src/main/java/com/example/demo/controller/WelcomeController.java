package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.utils.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {
    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    @Autowired
    UserService userService;

    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("tasks", tasks);

        return "blank";
    }

    // /hello?name=kotlin
    @GetMapping("/hello")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "")
                    String name, Model model) {

        model.addAttribute("message", name);

        return "blank";
    }

    @GetMapping("/signup")
    public String signUp(Model model){
        model.addAttribute("message","Anh Son");
        return "login";
    }

    @PostMapping("/login")
    public String signUp2(@ModelAttribute("email") String email, @ModelAttribute("password") String password, Model model){
        boolean loginStatus = userService.login(email, password);
        if (loginStatus) return "blank";
        else {
            model.addAttribute("error", "Cannot login. Please try again!");
            return "login";
        }
    }

}
