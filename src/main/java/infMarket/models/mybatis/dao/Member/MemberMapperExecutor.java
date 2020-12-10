package infMarket.models.mybatis.dao.Member;

import infMarket.models.mybatis.config.MyBatisConfig;
import infMarket.models.mybatis.dto.Member;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MemberMapperExecutor implements MemberMapper{

    @Override
    public List<Member> selectAllMembers() {
        return null;
    }

    @Override
    public Member selectMember(String memberId) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        try{
            Member member = memberMapper.selectMember(memberId);
            sqlSession.commit();
            sqlSession.close();
            return member;
        }catch(Exception e){
            sqlSession.rollback();
            sqlSession.close();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertMember(Member member) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        try{
            memberMapper.insertMember(member);
            sqlSession.commit();
            sqlSession.close();
            return true;
        }catch(Exception e){
            sqlSession.rollback();
            sqlSession.close();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateMember(Member member) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        try{
            memberMapper.updateMember(member);
            sqlSession.commit();
            sqlSession.close();
            return true;
        }catch(Exception e){
            sqlSession.rollback();
            sqlSession.close();
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public void deleteMember(String memberId) {

    }

    @Override
    public Member login(String id, String pw) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        Member member = memberMapper.login(id,pw);
        sqlSession.close();
        return member;
    }

    @Override
    public List<Member> selectTopThreeLevelMember() {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        try{
            List<Member> members = memberMapper.selectTopThreeLevelMember();
            sqlSession.commit();
            sqlSession.close();
            return members;
        }catch(Exception e){
            sqlSession.rollback();
            sqlSession.close();
            e.printStackTrace();
        }
        return null;
    }
}
