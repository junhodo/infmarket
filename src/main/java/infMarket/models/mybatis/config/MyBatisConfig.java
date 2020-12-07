package infMarket.models.mybatis.config;


import infMarket.models.mybatis.dao.MemberMapper;
import infMarket.models.mybatis.dto.Member;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServlet;



public class MyBatisConfig extends HttpServlet {

    private static SqlSessionFactory sqlSessionFactory;

    public void init() {
        System.out.println("Loading!!! wow!");
        try {
            InputStream inputStream = Resources.getResourceAsStream("infMarket/models/mybatis/config/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
            List<Member> members = memberMapper.selectAllMembers();
            for(Member member : members){
                System.out.println(member.getId());
                System.out.println(member.getPw());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public static void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        MyBatisConfig.sqlSessionFactory = sqlSessionFactory;
    }
}