package infMarket.models.mybatis.config;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

import javax.servlet.http.HttpServlet;



public class MyBatisConfig extends HttpServlet {

    private static SqlSessionFactory sqlSessionFactory;

    public void init() {
        System.out.println("Loading!!! wow!");
        try {
            InputStream inputStream = Resources.getResourceAsStream("infMarket/models/mybatis/config/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//            }
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