package webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webmvc.dto.AuthInfo;
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
        if (result.hasErrors()) {
            return "redirect:/login";
        }
        
        try {
            MemberLoginResponse res = memberLoginService.login(memberLoginRequest);
            AuthInfo authInfo = new AuthInfo(res.getId(), res.getEmail());
            session.setAttribute("member", authInfo);
            return "redirect:/";
        } catch (RuntimeException err) {
            return "redirect:/login";
        }
    }
}
