package com.example.study.web.controller;

import com.example.study.web.dto.MemberRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberViewController {

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("memberJoinDto", new MemberRequestDto.JoinDto());
        return "signup";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}
