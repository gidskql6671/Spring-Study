package jdbc.dao;

import jdbc.config.TestConfiguration;
import jdbc.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberDaoTest {
    private MemberDao memberDao;
    
    @BeforeEach
    public void setUp() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfiguration.class);
        memberDao = ctx.getBean(MemberDao.class);
        memberDao.clear();
    }
    
    @Test
    void findAllWhenEmpty() {
        // when
        List<Member> members = memberDao.findAll();
        
        // then
        assertTrue(members.isEmpty());
    }

    @Test
    void 중복되지않은_이메일을_가진_멤버_생성() {
        // given
        Member member = new Member(
                "test@abc.com", "test111", "testName"
        );
        
        // when
        memberDao.insert(member);

        // then
        assertNotNull(member.getId());
    }
    
    @Test
    void 중복된_이메일을_가진_멤버_생성() {
        // given
        Member member1 = new Member(
                "test@abc.com", "test111", "testName"
        );
        memberDao.insert(member1);
        Member member2 = new Member(
                "test@abc.com", "test222", "testName22"
        );

        // when & then
        assertThrows(DuplicateKeyException.class, () -> memberDao.insert(member2));
    }

    @Test
    void 존재하는_멤버의_findByEmail() {
        // given
        String email = "test@abc.com";
        String password = "test111";
        String name = "testName";
        memberDao.insert(new Member(email, password, name));
        
        // when
        Optional<Member> res = memberDao.findByEmail(email);

        // then
        assertTrue(res.isPresent());
        Member member = res.get();
        assertEquals(email, member.getEmail());
        assertEquals(password, member.getPassword());
        assertEquals(name, member.getName());
    }
    
    @Test
    void 존재하지_않는_멤버의_findByEmail() {
        // given
        String email = "test@abc.com";

        // when
        Optional<Member> res = memberDao.findByEmail(email);

        // then
        assertFalse(res.isPresent());
    }
}