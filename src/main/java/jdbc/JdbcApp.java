package jdbc;

import jdbc.config.AppContext;
import jdbc.dao.MemberDao;
import jdbc.domain.Member;
import jdbc.service.MemberService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class JdbcApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);

        MemberDao memberDao = ctx.getBean(MemberDao.class);
        MemberService memberService = ctx.getBean(MemberService.class);
        
        List<Member> members = memberDao.findAll();
        System.out.println(members.toString());
        if (members.isEmpty()) {
            ctx.close();
            return;
        }
        
        Member member = members.get(0);
        Random rnd = new Random();
        StringBuilder randomStr = new StringBuilder();
        for(int i = 0; i < 10; i++) {
            randomStr.append((char) ((rnd.nextInt(26)) + 97));
        }
        memberService.changePassword(member.getEmail(), member.getPassword(), randomStr.toString());

        Optional<Member> newMember = memberDao.findByEmail(member.getEmail());
        if (newMember.isEmpty()) {
            ctx.close();
            return;
        }
        System.out.println(newMember.get());
        
        ctx.close();
    }
}
