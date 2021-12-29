package webmvc.service;

import org.springframework.stereotype.Service;
import webmvc.dao.MemberDao;
import webmvc.domain.Member;
import webmvc.dto.MemberSignupRequest;
import webmvc.exception.DuplicateMemberException;

import java.util.Optional;

@Service
public class MemberSignupService {
    private final MemberDao memberDao;

    public MemberSignupService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    
    public Long signup(MemberSignupRequest req) throws DuplicateMemberException {
        Optional<Member> existsMember = memberDao.findByEmail(req.getEmail());
        if (existsMember.isPresent()) {
            throw new DuplicateMemberException();
        }
        
        Member member = new Member(req.getEmail(), req.getPassword());
        memberDao.insert(member);
        return member.getId();
    }
}
