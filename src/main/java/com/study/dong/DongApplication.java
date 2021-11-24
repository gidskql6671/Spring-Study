package com.study.dong;

import com.study.dong.domain.Member;
import com.study.dong.dto.RegisterRequest;
import com.study.dong.service.MemberService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DongApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppContext.class);

        MemberService memberService = ctx.getBean("memberService", MemberService.class);

        RegisterRequest req = new RegisterRequest("test", "test1234", "이름");
        memberService.join(req);
        Member member = memberService.findMemberByEmail("test");

        System.out.println(member.toString());

        ctx.close();
    }
}
