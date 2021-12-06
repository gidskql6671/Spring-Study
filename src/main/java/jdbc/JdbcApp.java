package jdbc;

import jdbc.config.AppContext;
import jdbc.dao.MemberDao;
import jdbc.domain.Member;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class JdbcApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);

        MemberDao memberDao = ctx.getBean(MemberDao.class);

        List<Member> members = memberDao.findAll();
        System.out.println(members.toString());
        
        ctx.close();
    }
}
