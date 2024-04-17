package dw.firstapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloPageController {
    @GetMapping("/hello")
    public String hello() {
        return "redirect:/hello.html";
    }
}
