package jdbc.dao;

import jdbc.config.TestConfiguration;
import jdbc.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

class MemberDaoTest {
    private MemberDao memberDao;
    
    @BeforeEach
    public void setUp() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfiguration.class);
        memberDao = ctx.getBean(MemberDao.class);
    }
    
    @Test
    void findAllWhenEmpty() {
        List<Member> members = memberDao.findAll();
        
        Assertions.assertTrue(members.isEmpty());
    }
}