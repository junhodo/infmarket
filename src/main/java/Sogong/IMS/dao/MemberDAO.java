package Sogong.IMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Sogong.IMS.model.Member;

public class MemberDAO {
	private static MemberDAO memberDAO = null;

    private MemberDAO() {

    }

    public static MemberDAO getInstance() {
        if(memberDAO == null) memberDAO = new MemberDAO();
        return memberDAO;
    }

    public boolean enroll(Member member) {
        try {
            Connection con = null;
            PreparedStatement stmt = null;

            Context context = new InitialContext();
            con = ((DataSource)context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            String sql = "INSERT INTO `member`(`memberID`, `memberPW`, `name`, `phoneNumber`, `address`, `email`, `memberType`, `department`) VALUES (?,?,?,?,?,?,?,?)";
            stmt = con.prepareStatement(sql);

            stmt.setString(1, member.getMemberID());
            stmt.setString(2, member.getMemberPW());
            stmt.setString(3, member.getName());
            stmt.setString(4, member.getPhoneNumber());
            stmt.setString(5, member.getAddress());
            stmt.setString(6, member.getEmail());
            stmt.setString(7, member.getMemberType());
            stmt.setString(8, member.getDepartment());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Member[] lookup(HashMap<String,Object> condition) {
        try {
            Connection con = null;
            PreparedStatement st = null;
            ResultSet rs = null;

            Context context = new InitialContext();
            con = ((DataSource)context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT * FROM ").append("`member`");

            if(condition.size() > 0) {
                stringBuilder.append("WHERE ");

                Iterator<String> it = condition.keySet().iterator();

                while(it.hasNext()) {
                    String columnName = it.next();
                    Object val = condition.get(columnName);

                    if(val instanceof String) {
                        stringBuilder.append(String.format("`%s` LIKE '%%%s%%' ", columnName, (String)val));
                    }

                    if(it.hasNext()) 
                        stringBuilder.append("AND ");
                }
            }
            String sql = stringBuilder.toString();
            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            ArrayList<Member> memList = new ArrayList<>();

            while(rs.next()) {
            	String name = rs.getString(1);
            	String memberID = rs.getString(2);
            	String memberPW = rs.getString(3);
            	String phoneNumber = rs.getString(4);
            	String address = rs.getString(5);
            	String email = rs.getString(6);
            	String memberType = rs.getString(7);
            	String department = rs.getString(8);
            	
            	Member member = new Member(name, memberID, memberPW, phoneNumber, address, email, memberType, department, null);
            	memList.add(member);
            }


            return memList.toArray(new Member[memList.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String memberID) {
        try {
            Connection con = null;
            PreparedStatement stmt = null;

            Context context = new InitialContext();
            con = ((DataSource)context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            String sql = "Delete FROM `member` WHERE `memberID`=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, memberID);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean modify(Member member) {
        try {
            Connection con = null;
            PreparedStatement st = null;

            Context context = new InitialContext();
            con = ((DataSource)context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            st = con.prepareStatement("UPDATE `member` SET `memberPW`=?,`name`=?,`phoneNumber`=?,`address`=?,`email`=?,`memberType`=?,`department`=? WHERE `memberID`=?");
            st.setString(1, member.getMemberPW());
            st.setString(2, member.getName());
            st.setString(3, member.getPhoneNumber());
            st.setString(4, member.getAddress());
            st.setString(5, member.getEmail());
            st.setString(6, member.getMemberType());
            st.setString(7, member.getDepartment());
            st.setString(8, member.getMemberID());
            
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Member login(String id, String pw) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        Context context = new InitialContext();

        conn = ((DataSource)context.lookup("java:comp/env/jdbc/mysql")).getConnection();
        String sql = "select * from sogongdo.member where memberID = ? and memberPW = ?";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        stmt.setString(2, pw);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next()){
             String memberID = resultSet.getString("memberID");
             String memberPW= resultSet.getString("memberPW");
             String name= resultSet.getString("name");
             String phoneNumber= resultSet.getString("phoneNumber");
             String address= resultSet.getString("address");
             String email= resultSet.getString("email");
             String memberType= resultSet.getString("memberType");
             String department= resultSet.getString("department");
             return new Member(memberID,memberPW,name,phoneNumber,address,email,memberType,department, null);
        }
        return null;
    }
}
