package webmvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webmvc.dao.MemberDao;
import webmvc.domain.Member;
import webmvc.dto.MemberDetailResponse;
import webmvc.exception.MemberNotFoundException;

@RestController
@RequestMapping("/members")
public class MemberDetailController {
	
	private final MemberDao memberDao;

	public MemberDetailController(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@GetMapping("/{id}")
	public MemberDetailResponse detail(@PathVariable("id") Long memberId) {
		try {
			Member member = memberDao
					.findById(memberId)
					.orElseThrow(MemberNotFoundException::new);

			return new MemberDetailResponse(member.getId(), member.getEmail());
		}
		catch(RuntimeException e) {
			throw new MemberNotFoundException();
		}
	}
}
