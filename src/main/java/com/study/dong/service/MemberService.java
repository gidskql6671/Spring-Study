package com.study.dong.service;

import com.study.dong.dao.MemberDao;
import com.study.dong.domain.Member;
import com.study.dong.dto.RegisterRequest;
import com.study.dong.exception.DuplicateMemberException;
import com.study.dong.exception.MemberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MemberService {
    private MemberDao memberDao;

    public MemberService() {}

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public Long join(RegisterRequest req) {
        Optional<Member> res = memberDao.findByEmail(req.getEmail());
        if (res.isPresent()) {
            throw new DuplicateMemberException("dup email " + req.getEmail());
        }

        Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName());
        memberDao.insert(newMember);
        return newMember.getId();
    }
    
    public void changePassword(String email, String oldPwd, String newPwd) {
        Optional<Member> res = memberDao.findByEmail(email);
        Member member = res.orElseThrow(MemberNotFoundException::new);
        
        member.changePassword(oldPwd, newPwd);
        
        memberDao.update(member);
    }
    
    public Optional<Member> findMemberByEmail(String email) {
        return memberDao.findByEmail(email);
    }
}
