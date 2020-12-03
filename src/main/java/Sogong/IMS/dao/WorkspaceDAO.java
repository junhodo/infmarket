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

import Sogong.IMS.model.Workspace;

public class WorkspaceDAO {
    // 사업장 등록
    public boolean enroll(Workspace workspace) {

        try {
            // DB 연결
            Connection conn = null;
            PreparedStatement stmt = null;

            Context context = new InitialContext();
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            /*
                코멘트 : workspaceID는 자동으로 생성되지 않으므로 INSERT시 추가되어야 합니다.
                
                INSERT INTO `workspace`(`workspaceID`,`workspaceName`,`registrantID`) VALUES (?,?,?)
            
                stmt.setString(1, workspace.getWorkspaceID());
                stmt.setString(2, workspace.getWorkspaceName());
                stmt.setString(3, workspace.getRegistrantID());
            */

            String sql = "INSERT INTO `workspace`(`workspaceID`,`workspaceName`,`registrantID`) VALUES (?,?,?)";
            stmt = conn.prepareStatement(sql);

            //id는 autoincrement이므로 추가하지 않습니다.
            stmt.setString(1, workspace.getWorkspaceID());
            stmt.setString(2, workspace.getWorkspaceName());
            stmt.setString(3, workspace.getRegistrantID());

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

    // 사업장 수정
    public boolean modify(Workspace workspace) {

        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            Context context = new InitialContext();
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            String sql = "UPDATE `sogongdo`.`workspace` SET `workspaceName` = ?, `registrantID` = ? WHERE `workspaceID` = ?;";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,workspace.getWorkspaceName());
            stmt.setString(2,workspace.getRegistrantID());
            stmt.setString(3,workspace.getWorkspaceID());

            /*
                sql에 ?가 3개인데 2개 밖에 set이 지정이 안되있으니
                77번째 줄에 stmt.getString(3,workspace.getWorkspaceID());를 추가합니다.
            */

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

    // 사업장 삭제
    public boolean delete(Workspace workspace) {

        try {
			Connection con = null;
			PreparedStatement st = null;
			
			Context context = new InitialContext();
			con = ((DataSource)context.lookup("java:comp/env/jdbc/mysql")).getConnection();
			
			String sql = "Delete FROM `workspace` WHERE workspaceID = ?";
			st = con.prepareStatement(sql);
			
			st.setString(1, workspace.getWorkspaceID());
			st.executeUpdate();
			
            return true;
            
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
        return false;
    }

    // 사업장 조회
    public Workspace[] lookup(HashMap<String, Object> condition) {

        try {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            Context context = new InitialContext();
            conn = ((DataSource)context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            // conn = DriverManager.getConnection("jdbc:mysql://totomo.iptime.org:3306/sogongdo?serverTimezone=UTC", "admin", "tejava");

            StringBuilder sqlBuilder = new StringBuilder();

            sqlBuilder.append("SELECT * FROM ")
                        .append("`workspace` ");

            // 조건 검색
            if (condition.size()>0) {
                sqlBuilder.append("WHERE ");

                // condition은 속성과 값으로 구성되어있다.
                // iter는 테이블 속성명이 들어있다.
                Iterator<String> iter = condition.keySet().iterator();

                while (iter.hasNext()) {
                    String columnName = iter.next();            //테이블의 속성명

                    Object value = condition.get(columnName);   //그 속성의 값

                    // 자료형이 String이나 Integer라면
                    if(value instanceof String || value instanceof Integer){
                        sqlBuilder.append(String.format("`%s` LIKE '%%%s%%' ", columnName, (String)value));
                    }

                    if (iter.hasNext())
                        sqlBuilder.append("AND ");
                }
            }

            String sql = sqlBuilder.toString();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            ArrayList<Workspace> workspaceList = new ArrayList<>();

            while (rs.next()) {
                // 코드 처리부
                String workspaceID = rs.getString("workspaceID");
                String workspaceName = rs.getString("workspaceName");
                String registrantID = rs.getString("registrantID");

                workspaceList.add(
                    new Workspace(workspaceID, workspaceName, registrantID)
                );

            }

            return workspaceList.toArray(new Workspace[workspaceList.size()]);

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