package jdbc.dao;

import jdbc.domain.Member;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MemberDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<Member> findByEmail(String email) {
        List<Member> results = jdbcTemplate.query(
                "SELECT * FROM member WHERE email = ?",
                memberRowMapper(),
                email
        );
        
        return results.stream().findAny();
    }

    public void insert(Member member) {
        
    }

    public void update(Member member) {

    }

    public List<Member> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM member",
                memberRowMapper()
        );
    }
    
    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member(
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("name"));
            member.setId(rs.getLong("id"));
            return member;
        };
    }
}
