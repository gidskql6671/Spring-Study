package webmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webmvc.dao.MemberDao;
import webmvc.domain.Member;
import webmvc.dto.ErrorResponse;
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
	public ResponseEntity<Object> detail(@PathVariable("id") Long memberId) {
		try {
			Member member = memberDao
					.findById(memberId)
					.orElseThrow(MemberNotFoundException::new);

			return ResponseEntity
					.ok(new MemberDetailResponse(
							member.getId(), 
							member.getEmail(), 
							member.getRegisterDateTime()
					));
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorResponse("Not Found Member"));
			
			// body 내용이 비어있다면, build()를 통해 body 없이 보낼 수 있음.
			// NotFound(404)나 BadRequest(400)과 같이 자주 쓰는 상태코드는 메서드로 있음.
			// return ResponseEntity.notFound().build();
		}
	}
}
