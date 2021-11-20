package com.study.dong.assembler;

import com.study.dong.dao.MemberDao;
import com.study.dong.service.MemberService;

public class Assembler {
    private MemberDao memberDao;
    private MemberService memberService;

    public Assembler() {
        memberDao = new MemberDao();
        memberService = new MemberService(memberDao);
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public MemberService getMemberService() {
        return memberService;
    }
}
