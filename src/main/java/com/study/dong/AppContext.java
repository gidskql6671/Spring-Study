package com.study.dong;

import com.study.dong.dao.MemberDao;
import com.study.dong.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }
    
    @Bean
    public MemberService memberService() {
        return new MemberService(memberDao());
    }
}
