package webmvc.service;

import org.springframework.stereotype.Service;
import webmvc.dao.MemberDao;
import webmvc.domain.Member;
import webmvc.dto.MemberLoginRequest;
import webmvc.dto.MemberLoginResponse;
import webmvc.exception.WrongIdPasswordException;

import java.util.Optional;

@Service
public class MemberLoginService {

    private final MemberDao memberDao;

    public MemberLoginService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    
    public MemberLoginResponse login(MemberLoginRequest req) {
        Optional<Member> optionalMember = memberDao.findByEmail(req.getEmail());
        if (optionalMember.isEmpty()) {
            throw new WrongIdPasswordException();
        }
        
        Member member = optionalMember.get();
        if (!member.matchPassword(req.getPassword())) {
            throw new WrongIdPasswordException();
        }
        
        return new MemberLoginResponse(member.getId(), member.getEmail());
    }
}
