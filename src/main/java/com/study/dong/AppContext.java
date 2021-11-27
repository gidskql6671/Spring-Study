package com.study.dong;

import com.study.dong.dao.MemberDao;
import com.study.dong.service.MemberService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
    @Bean
    @Qualifier("firstMemberDao") // 생략시, Bean의 이름이 한정자로 지정된다.
    public MemberDao memberDao1() {
        return new MemberDao();
    }
    @Bean
    public MemberDao memberDao2() {
        return new MemberDao();
    }
    
    @Bean
    public MemberService memberService() {
        return new MemberService();
    }
}
