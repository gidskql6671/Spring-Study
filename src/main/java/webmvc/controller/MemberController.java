package webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import webmvc.dto.MemberSignupRequest;
import webmvc.dto.MemberSignupRequestValidator;
import webmvc.service.MemberSignupService;

import javax.validation.Valid;

@Controller
public class MemberController {

    private final MemberSignupRequestValidator signupReqValidator;
    private final MemberSignupService memberSignupService;
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(signupReqValidator);
    }

    public MemberController(MemberSignupRequestValidator signupReqValidator, MemberSignupService memberSignupService) {
        this.signupReqValidator = signupReqValidator;
        this.memberSignupService = memberSignupService;
    }

    @GetMapping("/signup")
    public String signup() {
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberSignupRequest memberSignupRequest) {
        try {
            memberSignupService.signup(memberSignupRequest);
            return "redirect:/";
        } catch (RuntimeException err) {
            return "redirect:/signup";
        }
    }
}
 