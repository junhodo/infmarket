package Sogong.IMS.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Sogong.IMS.model.AuthorityGroup;
import Sogong.IMS.model.Member;
import Sogong.IMS.model.MemberAuthorityGroup;
import lombok.SneakyThrows;

public class MemberAuthorityGroupDAO {

    public boolean enroll(MemberAuthorityGroup memberAuthorityGroup){

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // META-INF아래 context.xml
            Context context = new InitialContext();
            // DB Connection
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            // db 연결 코드
            // String url=
            // "jdbc:mysql://totomo.iptime.org:3306/sogongdo?serverTimezone=UTC&zeroDateTimeBehavior=convertToNull";
            // String id= "admin";
            // String pwd= "tejava";

            // conn = DriverManager.getConnection(url, id, pwd);

            String sql = "INSERT INTO `sogongdo`.`memberauthoritygroup` (`memberID`,`authorityGroupID`,`memberAuthorityGroupID`) VALUES (?,?,default) ON DUPLICATE KEY UPDATE memberID=?, authorityGroupID=?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, memberAuthorityGroup.getMemberID());
            stmt.setInt(2, memberAuthorityGroup.getAuthorityGroup().getAuthorityGroupID());
            stmt.setString(3, memberAuthorityGroup.getMemberID());
            stmt.setInt(4, memberAuthorityGroup.getAuthorityGroup().getAuthorityGroupID());

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    public Member[] lookup(HashMap<String, Object> condition) {

        try {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            Context context = new InitialContext();
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            StringBuilder builder = new StringBuilder();

            builder.append("SELECT * FROM (").append(
                    "SELECT `M`.`department`, `M`.`memberID`, `M`.`memberType`, `MAG`.`memberAuthorityGroupID`, `AG`.`authorityGroupID`, `AG`.`authorityGroupName`")
                    .append("FROM `member` AS `M`")
                    .append("JOIN `memberauthoritygroup` AS `MAG` ON `M`.`memberID`=`MAG`.`memberID`")
                    .append("JOIN `authoritygroup` AS `AG` ON `AG`.`authorityGroupID`=`MAG`.`authorityGroupID`")
                    .append(") AS `result` ");
                    
            // .append("ORDER BY `result`.`authorityGroupName` ASC ");

            // SELECT * FROM (
            // SELECT `M`.`department`, `M`.`memberID`, `M`.`memberType`,
            // `MAG`.`memberAuthorityGroupID`, `AG`.`authorityGroupID`,
            // `AG`.`authorityGroupName`
            // FROM `member` AS `M`
            // JOIN `memberauthoritygroup` AS `MAG` ON `M`.`memberID`=`MAG`.`memberID`
            // JOIN `authoritygroup` AS `AG` ON
            // `AG`.`authorityGroupID`=`MAG`.`authorityGroupID`
            // ) AS `result`

            // 조건 검색
            if (condition.size() > 0) {
                builder.append("WHERE ");

                // condition은 속성과 값으로 구성되어있다.
                // key : memberName, value : 소공도
                Iterator<String> iter = condition.keySet().iterator();

                while (iter.hasNext()) {
                    String columnName = iter.next(); // 테이블의 속성명
                    Object value = condition.get(columnName); // 그 속성의 값

                    if (value instanceof String) {
                        builder.append(String.format("`result`.`%s` LIKE '%%%s%%' ", columnName, (String) value));
                    }

                    if (value instanceof AuthorityGroup) {
                        builder.append(String.format(
                                "`result`.`memberID` IN(SELECT memberID FROM `memberauthoritygroup` JOIN `authoritygroup` ON `memberauthoritygroup`.`authorityGroupID`=`authoritygroup`.`authorityGroupID` WHERE `authoritygroupName` LIKE '%%%s%%')",
                                ((AuthorityGroup) value).getAuthorityGroupName()));
                    }

                    if (iter.hasNext())
                        builder.append("AND ");
                }
            }

            String sql = builder.toString();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            HashMap<String, Member> memberAuthorityGroups = new HashMap<>();

            while (rs.next()) {
                String memberID = rs.getString("memberID");

                if (memberAuthorityGroups.get(memberID) == null) {
                    memberAuthorityGroups.put(memberID,
                            Member.builder().department(rs.getString("department")).memberID(memberID)
                                    .memberType(rs.getString("memberType")).memberAuthorityGroups(new ArrayList<>())
                                    .build());
                }

                if (rs.getString("authorityGroupName") != null && rs.getInt("authorityGroupID") != Types.NULL) {

                    AuthorityGroup authorityGroup = AuthorityGroup.builder()
                            .authorityGroupName(rs.getString("authorityGroupName"))
                            .authorityGroupID(rs.getInt("authorityGroupID")).build();

                    memberAuthorityGroups.get(memberID).getMemberAuthorityGroups().add(
                            new MemberAuthorityGroup(rs.getInt("memberAuthorityGroupID"), authorityGroup, memberID));
                }
            }

            Member[] members = memberAuthorityGroups.values().toArray(new Member[memberAuthorityGroups.size()]);

            return members;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public boolean hasAuthority(Member member, String authorityGroupName) {

        try {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            // META-INF아래 context.xml
            Context context = new InitialContext();
            // DB Connection
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            String sql = "SELECT 1 FROM (SELECT memberID, authorityGroupName FROM memberauthoritygroup JOIN authoritygroup ON memberauthoritygroup.authorityGroupID=authoritygroup.authorityGroupID) as `table` WHERE `table`.memberID=? AND `table`.authorityGroupName=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, member.getMemberID());
            stmt.setString(2, authorityGroupName);

            rs = stmt.executeQuery();

            if (rs.next())
                return true;
            else
                return false;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }


    public boolean modify(Member member) {

        try {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            // META-INF아래 context.xml
            Context context = new InitialContext();
            // DB Connection
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            String sql = "DELETE FROM `sogongdo`.`memberauthoritygroup`WHERE `memberID` = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, member.getMemberID());
            stmt.executeUpdate();

            for(MemberAuthorityGroup mag : member.getMemberAuthorityGroups()){
                sql = "INSERT INTO `sogongdo`.`memberauthoritygroup` (`memberID`,`authorityGroupID`,`memberAuthorityGroupID`) VALUES (?,?,default) ON DUPLICATE KEY UPDATE memberID=?, authorityGroupID=?";
                stmt = conn.prepareStatement(sql);
    
                stmt.setString(1, member.getMemberID());
                stmt.setInt(2, mag.getAuthorityGroup().getAuthorityGroupID());
                stmt.setString(3, member.getMemberID());
                stmt.setInt(4, mag.getAuthorityGroup().getAuthorityGroupID());
                stmt.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(MemberAuthorityGroup memberAuthorityGroup) {
        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            // META-INF아래 context.xml
            Context context = new InitialContext();
            // DB Connection
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            stmt = conn.prepareStatement(
                    "DELETE FROM `sogongdo`.`memberauthoritygroup`WHERE `memberAuthorityGroupID` = ?");

            stmt.setInt(1, memberAuthorityGroup.getMemberAuthorityGroupID());

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }
}