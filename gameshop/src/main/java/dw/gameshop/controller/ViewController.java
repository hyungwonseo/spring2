package dw.gameshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/login")
    public String login_form() {
        return "login_form";
    }

    @GetMapping("/articles")
    public String article() {
        return "article";
    }

    @GetMapping("/gameshop/index.html")
    public String index() {
        return "index";
    }

    @GetMapping("/gameshop/singleProduct.html")
    public String singleProduct() {
        return "singleProduct";
    }

    @GetMapping("/gameshop/login.html")
    public String login() {
        return "login";
    }

    @GetMapping("/gameshop/cart.html")
    public String cart() {
        return "cart";
    }

    @GetMapping("/gameshop/mypage.html")
    public String mypage() {
        return "mypage";
    }

    @GetMapping("/gameshop/video.html")
    public String video() {
        return "video";
    }
}
