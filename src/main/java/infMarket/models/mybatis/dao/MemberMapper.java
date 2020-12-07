package infMarket.models.mybatis.dao;

import infMarket.models.mybatis.dto.Member;

import java.util.List;
public interface MemberMapper {
    public List<Member> selectAllMembers();
    public Member selectMember(String memberId);
    public void insertMember(Member member);
    public void updateMember(Member member);
    public void deleteMember(String memberId);
}
