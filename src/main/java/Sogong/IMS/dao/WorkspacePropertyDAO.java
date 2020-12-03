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

import Sogong.IMS.model.WorkspaceProperty;

public class WorkspacePropertyDAO {
      // 사업장속성 등록
      public boolean enroll(WorkspaceProperty workspaceproperty) {

        try {
            // DB 연결
            Connection conn = null;
            PreparedStatement stmt = null;

            Context context = new InitialContext();
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();


            /*
                WorkspaceProperty 테이블의 속성은 총 10개이므로, INSERT 시 속성은 총 10개가 필요하며 VALUE도 동일하게 10개 필요합니다.

                VALUE뒤에 ?가 2개가 아닌 10개가 있어야 합니다. 
            */

            String sql = "INSERT INTO `workspaceproperty`(`corporateRegistrationNumber`, `representitiveName`, `workspaceAddress`, `phoneNum`, `typeOfBusiness`, `workspaceMemberCount`, `workspaceStatus`, `registraintID`, `workspaceID`) VALUES (?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, workspaceproperty.getCorporateRegistrationNumber());
            stmt.setString(2, workspaceproperty.getRepresentativeName());
            stmt.setString(3, workspaceproperty.getWorkspaceAddress());
            stmt.setString(4, workspaceproperty.getPhoneNum());
            stmt.setString(5, workspaceproperty.getTypeOfBusiness());
            stmt.setInt(6, workspaceproperty.getWorkspaceMemberCount());
            stmt.setString(7, workspaceproperty.getWorkspaceStatus());
            stmt.setString(8, workspaceproperty.getRegistrantID());
            stmt.setString(9, workspaceproperty.getWorkspaceID());
            stmt.setString(10, workspaceproperty.getWorkspaceName());

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

    // 사업장속성 수정
    public boolean modify(WorkspaceProperty workspaceproperty) {

        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            Context context = new InitialContext();
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            String sql = "UPDATE `sogongdo`.`workspaceproperty` SET `representativeName` = ?, `workspaceAddress` = ?, `phoneNum` = ?, `typeOfBusiness` = ?, `workspaceMemberCount` = ?, `workspaceStatus` = ?, `registrantID` = ?, `workspaceID` = ?, `workspaceName` = ? WHERE `corporateRegistrationNumber` = ?;";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1, workspaceproperty.getRepresentativeName());
            stmt.setString(2, workspaceproperty.getWorkspaceAddress());
            stmt.setString(3, workspaceproperty.getPhoneNum());
            stmt.setString(4, workspaceproperty.getTypeOfBusiness());
            stmt.setInt(5, workspaceproperty.getWorkspaceMemberCount());
            stmt.setString(6, workspaceproperty.getWorkspaceStatus());
            stmt.setString(7, workspaceproperty.getRegistrantID());
            stmt.setString(8, workspaceproperty.getWorkspaceID());
            stmt.setString(9, workspaceproperty.getWorkspaceName());
            stmt.setString(10, workspaceproperty.getCorporateRegistrationNumber());
            

            /*
                ?가 10개인데 지금 설정한건 8 개입니다. 7번째 순서가 registrantID가 되어아 하고 7,10번째 부분이 빠져있습니다.

                stmt.setString(7, workspaceproperty.getRegistrantID());
                stmt.setString(10, workspaceproperty.getCorporateRegistrantNumber());
            */

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    // 사업장속성 삭제
    public boolean delete(WorkspaceProperty workspaceproperty) {

        try {
			Connection con = null;
			PreparedStatement st = null;
			
			Context context = new InitialContext();
			con = ((DataSource)context.lookup("java:comp/env/jdbc/mysql")).getConnection();
			
			String sql = "Delete FROM `workspaceproperty` WHERE corporateRegistrationNumber = ?";
			st = con.prepareStatement(sql);
			
			st.setString(1, workspaceproperty.getCorporateRegistrationNumber());
			st.executeUpdate();
			
            return true;
            
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
        return false;
    }

    // 사업장속성 조회
    public WorkspaceProperty[] lookup(HashMap<String, Object> condition) {

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

            ArrayList<WorkspaceProperty> workspacepropertyList = new ArrayList<>();

            while (rs.next()) {
                // 코드 처리부
                String corporateRegistrationNumber = rs.getString("corporateRegistrationNumber");
                String representativeName = rs.getString("representativeName");
                String workspaceAddress = rs.getString("workspaceAddress");
                String phoneNum = rs.getString("phoneNum");
                String typeOfBusiness = rs.getString("typeOfBusiness");
                int workspaceMemberCount = rs.getInt("workspaceMemberCount");
                String workspaceStatus = rs.getString("workspaceStatus");
                String registrantID = rs.getString("registrantID");
                String workspaceID = rs.getString("workspaceID");
                String workspaceName = rs.getString("workspaceName");

                workspacepropertyList.add(
                    new WorkspaceProperty(corporateRegistrationNumber, representativeName, workspaceAddress, phoneNum, typeOfBusiness, workspaceMemberCount, workspaceStatus, registrantID, workspaceID, workspaceName)
                );

            }

            return workspacepropertyList.toArray(new WorkspaceProperty[workspacepropertyList.size()]);

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