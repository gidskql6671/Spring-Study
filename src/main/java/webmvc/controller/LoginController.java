package webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webmvc.dto.MemberLoginRequest;
import webmvc.dto.MemberLoginResponse;
import webmvc.service.MemberLoginService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    private final MemberLoginService memberLoginService;
    
    public LoginController(MemberLoginService memberLoginService) {
        this.memberLoginService = memberLoginService;
    }
    
    @GetMapping
    public String login() {
        return "member/login";
    }

    @PostMapping
    public String login(@Valid MemberLoginRequest memberLoginRequest, BindingResult result, HttpSession session) {
        try {
            MemberLoginResponse res = memberLoginService.login(memberLoginRequest);
            session.setAttribute("member", res);
            return "redirect:/";
        } catch (RuntimeException err) {
            return "redirect:/login";
        }
    }
}
