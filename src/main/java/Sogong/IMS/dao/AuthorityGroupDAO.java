package Sogong.IMS.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Sogong.IMS.model.AuthorityGroup;

public class AuthorityGroupDAO {

    public AuthorityGroup[] lookup() {
        try {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            // 기존 코드
            Context context = new InitialContext();
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            // db 연결 코드
            // String url= "jdbc:mysql://totomo.iptime.org:3306/sogongdo?serverTimezone=UTC&zeroDateTimeBehavior=convertToNull";
            // String id= "admin";
            // String pwd= "tejava";

            // conn = DriverManager.getConnection(url, id, pwd);


            stmt = conn.prepareStatement("SELECT * FROM authoritygroup");
            rs = stmt.executeQuery();

            ArrayList<AuthorityGroup> authorityGroups = new ArrayList<>();

            while (rs.next()) {
                authorityGroups.add(AuthorityGroup.builder().authorityGroupID(rs.getInt("authorityGroupID"))
                        .authorityGroupName(rs.getString("authorityGroupName")).description(rs.getString("description"))
                        .build());
            }

            return authorityGroups.toArray(new AuthorityGroup[authorityGroups.size()]);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}