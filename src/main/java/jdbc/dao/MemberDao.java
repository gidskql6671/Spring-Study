package jdbc.dao;

import jdbc.domain.Member;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
    
    public void clear() {
        jdbcTemplate.update("DELETE FROM member");
    }

    public void insert(Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                conn -> {
                    PreparedStatement pstmt = conn.prepareStatement(
                            "INSERT INTO member(email, password, name) VALUES(?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS
                    );
                    
                    pstmt.setString(1, member.getEmail());
                    pstmt.setString(2, member.getPassword());
                    pstmt.setString(3, member.getName());
                    
                    return pstmt;
                },
                keyHolder
        );
        
        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue());
    }

    public void update(Member member) {
        /* 간단한 PreparedStatement 사용 */
//        jdbcTemplate.update(
//                "UPDATE member SET name = ?, password = ? WHERE email = ?",
//                member.getName(), member.getPassword(), member.getEmail()
//        );

        /* PreparedStatementCreator를 사용할 수 있다. */
//        jdbcTemplate.update(
//                new PreparedStatementCreator() {
//                    @Override
//                    public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
//                        PreparedStatement pstmt = conn.prepareStatement(
//                                "UPDATE member SET name = ?, password = ? WHERE email = ?"
//                        );
//                        pstmt.setString(1, member.getName());
//                        pstmt.setString(2, member.getPassword());
//                        pstmt.setString(3, member.getEmail());
//
//                        return pstmt;
//                    }
//                }
//        );

        /* PreparedStatementCreator를 람다로 간편하게 사용할 수 있다. */
        jdbcTemplate.update(
                conn -> {
                    PreparedStatement pstmt = conn.prepareStatement(
                            "UPDATE member SET name = ?, password = ? WHERE email = ?"
                    );
                    pstmt.setString(1, member.getName());
                    pstmt.setString(2, member.getPassword());
                    pstmt.setString(3, member.getEmail());

                    return pstmt;
                }
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
                    rs.getString("name"));
            member.setId(rs.getLong("id"));
            return member;
        };
    }
}
