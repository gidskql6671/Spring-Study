package webmvc.dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import webmvc.domain.Member;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Component
public class MemberDao {
    private final JdbcTemplate jdbcTemplate;
    
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
    
    public Optional<Member> findById(Long id) {
        List<Member> results = jdbcTemplate.query(
                "SELECT * FROM member WHERE id = ?",
                memberRowMapper(),
                id
        );

        return results.stream().findAny();
    }

    public void clear() {
        jdbcTemplate.update("DELETE FROM member");
    }

    public void insert(Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                conn -> {
                    PreparedStatement pstmt = conn.prepareStatement(
                            "INSERT INTO member(email, password, register_date) VALUES(?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS
                    );

                    pstmt.setString(1, member.getEmail());
                    pstmt.setString(2, member.getPassword());
                    pstmt.setTimestamp(3,
                            Timestamp.valueOf(member.getRegisterDateTime()));

                    return pstmt;
                },
                keyHolder
        );

        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue());
    }

    public void update(Member member) {
        jdbcTemplate.update(
                "UPDATE member SET password = ? WHERE email = ?",
                member.getPassword(), member.getEmail()
        );
    }

    public List<Member> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM member",
                memberRowMapper()
        );
    }

    public int count() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM member",
                Integer.class
        );
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member(
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getTimestamp("register_date").toLocalDateTime());
            member.setId(rs.getLong("id"));
            return member;
        };
    }
}
