package jdbc.service;

import com.study.dong.exception.MemberNotFoundException;
import jdbc.dao.MemberDao;
import jdbc.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService {
    
    public MemberDao memberDao;
    
    @Autowired
    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    
    @Transactional
    public void changePassword(String email, String oldPwd, String newPwd) {
        Optional<Member> optionalMember = memberDao.findByEmail(email);
        Member member = optionalMember.orElseThrow(MemberNotFoundException::new);
        
        member.changePassword(oldPwd, newPwd);
        memberDao.update(member);
    }
}
