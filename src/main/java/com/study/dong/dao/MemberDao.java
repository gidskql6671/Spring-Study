package com.study.dong.dao;

import com.study.dong.domain.Member;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MemberDao {
    private static long sequence = 0L;
    private Map<String, Member> map = new HashMap<>();
    
    public Optional<Member> findByEmail(String email) {
        return Optional.ofNullable(map.get(email));
    }
    
    public List<Member> findAll() {
        return new ArrayList<>(map.values());
    }
    
    public void insert(Member member) {
        member.setId(++sequence);
        map.put(member.getEmail(), member);
    }
    
    public void update(Member member) {
        map.put(member.getEmail(), member);
    }
}
