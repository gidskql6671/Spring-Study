package com.study.dong.dao;

import com.study.dong.domain.Member;

import java.util.HashMap;
import java.util.Map;

public class MemberDao {
    private static long sequence = 0L;
    private Map<String, Member> map = new HashMap<>();
    
    public Member selectByEmail(String email) {
        return map.get(email);
    }
    
    public void insert(Member member) {
        member.setId(++sequence);
        map.put(member.getEmail(), member);
    }
    
    public void update(Member member) {
        map.put(member.getEmail(), member);
    }
}
