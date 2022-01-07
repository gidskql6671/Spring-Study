package webmvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webmvc.dao.MemberDao;
import webmvc.domain.Member;
import webmvc.exception.MemberNotFoundException;

import java.util.Optional;

@Service
public class ChangePwdService {
	
	private final MemberDao memberDao;

	public ChangePwdService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Transactional
	public void changePassword(String email, String currectPassword, String newPassword) {
		Optional<Member> optionalMember = memberDao.findByEmail(email);
		Member member = optionalMember.orElseThrow(MemberNotFoundException::new);
		
		member.changePassword(currectPassword, newPassword);
		memberDao.update(member);
	}
}
