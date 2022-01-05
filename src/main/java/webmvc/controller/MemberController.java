package webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import webmvc.dto.MemberSignupRequest;
import webmvc.service.MemberSignupService;

import javax.validation.Valid;

@Controller
public class MemberController {

    private final MemberSignupService memberSignupService;

    public MemberController(MemberSignupService memberSignupService) {
        this.memberSignupService = memberSignupService;
    }

    @GetMapping("/signup")
    public String signup() {
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberSignupRequest memberSignupRequest, BindingResult result) {
        try {
            memberSignupService.signup(memberSignupRequest);
            return "redirect:/";
        } catch (RuntimeException err) {
            return "redirect:/signup";
        }
    }
}
 