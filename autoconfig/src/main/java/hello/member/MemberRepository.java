package hello.member;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    private final JdbcTemplate template;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        template = jdbcTemplate;
    }

    public void initTable() { //예제 단순화를 위해 이곳에 작성
        template.execute("create table member(member_id varchar primary key, name varchar)");
    }

    public void save(Member member) {
        template.update("insert into member(member_id, name) values(?,?)",
                member.getMemberId(),
                member.getName());
    }

    public Member find(String memberId) {
        return template.queryForObject("select member_id, name from member where member_id=?",
        BeanPropertyRowMapper.newInstance(Member.class), memberId);
    }

    public List<Member> findAll() {
        return template.query("select member_id, name from member",
                BeanPropertyRowMapper.newInstance(Member.class));
    }

}
