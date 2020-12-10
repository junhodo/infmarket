package infMarket.models.mybatis.dao.Member;

import infMarket.models.mybatis.dto.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface MemberMapper {
    public List<Member> selectAllMembers();

    public Member selectMember(@Param("id") String id);

    public boolean insertMember(Member member);

    public boolean updateMember(Member member);

    public void deleteMember(String memberId);

    public Member login(@Param("id") String id, @Param("pw") String pw);

    public List<Member> selectTopThreeLevelMember();
}
