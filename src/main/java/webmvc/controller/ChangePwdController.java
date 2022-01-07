package webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webmvc.dto.AuthInfo;
import webmvc.dto.ChangePwdRequest;
import webmvc.service.ChangePwdService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/password")
public class ChangePwdController {

	private final ChangePwdService changePwdService;

	public ChangePwdController(ChangePwdService changePwdService) {
		this.changePwdService = changePwdService;
	}

	@GetMapping
	public String form() {
		return "member/changePwdForm";
	}

	@PostMapping
	public String submit(@Valid ChangePwdRequest req, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "member/changePwdForm";
		}

		AuthInfo authInfo = (AuthInfo) session.getAttribute("member");
		try {
			changePwdService.changePassword(authInfo.getEmail(), req.getCurrentPassword(), req.getNewPassword());
			return "index";
		}
		catch (RuntimeException e) {
			return "member/changePwdForm";
		}
	}
}
