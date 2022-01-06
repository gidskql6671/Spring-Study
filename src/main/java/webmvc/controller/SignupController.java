package webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webmvc.dto.MemberSignupRequest;
import webmvc.service.MemberSignupService;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final MemberSignupService memberSignupService;

    public SignupController(MemberSignupService memberSignupService) {
        this.memberSignupService = memberSignupService;
    }

    @GetMapping
    public String signup() {
        return "member/signup";
    }

    @PostMapping
    public String signup(@Valid MemberSignupRequest memberSignupRequest, BindingResult result) {
        try {
            memberSignupService.signup(memberSignupRequest);
            return "redirect:/";
        } catch (RuntimeException err) {
            return "redirect:/signup";
        }
    }
}
 