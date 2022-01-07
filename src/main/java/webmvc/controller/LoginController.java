package webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webmvc.dto.AuthInfo;
import webmvc.dto.MemberLoginRequest;
import webmvc.dto.MemberLoginResponse;
import webmvc.service.MemberLoginService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
	public String login(@CookieValue(value = "remember_email", required = false) Cookie rCookie, Model model) {
		MemberLoginRequest memberLoginRequest = new MemberLoginRequest();

		if (rCookie != null) {
			memberLoginRequest.setEmail(rCookie.getValue());
			memberLoginRequest.setRememberEmail(true);
		}
		model.addAttribute("member", memberLoginRequest);
		return "member/login";
	}

	@PostMapping
	public String login(@Valid MemberLoginRequest memberLoginRequest,
						BindingResult result,
						HttpSession session,
						HttpServletResponse response) {
		if (result.hasErrors()) {
			return "redirect:/login";
		}

		System.out.println(memberLoginRequest);
		
		try {
			MemberLoginResponse res = memberLoginService.login(memberLoginRequest);
			AuthInfo authInfo = new AuthInfo(res.getId(), res.getEmail());
			session.setAttribute("member", authInfo);

			Cookie cookie = new Cookie("remember_email", memberLoginRequest.getEmail());
			if (memberLoginRequest.isRememberEmail()) {
				cookie.setMaxAge(60 * 60 * 24);
			}
			else {
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
			
			return "redirect:/";
		} catch (RuntimeException err) {
			return "redirect:/login";
		}
	}
}
