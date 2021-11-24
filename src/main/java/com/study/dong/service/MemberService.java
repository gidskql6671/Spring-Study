package com.study.dong.service;

import com.study.dong.dao.MemberDao;
import com.study.dong.domain.Member;
import com.study.dong.dto.RegisterRequest;
import com.study.dong.exception.DuplicateMemberException;
import com.study.dong.exception.MemberNotFoundException;

public class MemberService {
    private MemberDao memberDao;
    
    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    
    public Long join(RegisterRequest req) {
        Member member = memberDao.selectByEmail(req.getEmail());
        if (member != null) {
            throw new DuplicateMemberException("dup email " + req.getEmail());
        }
        
        Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName());
        memberDao.insert(newMember);
        return newMember.getId();
    }
    
    public void changePassword(String email, String oldPwd, String newPwd) {
        Member member = memberDao.selectByEmail(email);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        
        member.changePassword(oldPwd, newPwd);
        
        memberDao.update(member);
    }
    
    public Member findMemberByEmail(String email) {

        return memberDao.selectByEmail(email);
    }
}
