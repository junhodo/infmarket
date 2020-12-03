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

import Sogong.IMS.model.FacilityCharge;

public class FacilityChargeDAO {
    public boolean enroll(FacilityCharge facilityCharge) {
        try {

            // DB연결
            Connection conn = null;
            PreparedStatement stmt = null;

            // META-INF아래 context.xml
            Context context  = new InitialContext();
            // DB Connection
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            // INSERT INTO `facilitycharge`(`workspaceID`, `facilityID`, `chargeName`, `charge`, `isDiscount`, `discountRate`, `resistrantID`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6],[value-7])
            String sql = "INSERT INTO `facilitycharge`(`workspaceID`, `facilityID`, `chargeName`, `charge`, `isDiscount`, `discountRate`, `resistrantID`) VALUES (?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            // 시설ID, 요금명, 요금, 할인여부, 할인율, 등록자ID를 각 인덱스 파라미터에 등록
            stmt.setString(1, facilityCharge.getFacilityID());
            stmt.setString(2, facilityCharge.getWorkspaceID());
            stmt.setString(3, facilityCharge.getChargeName());
            stmt.setInt(4, facilityCharge.getCharge());
            stmt.setBoolean(5, facilityCharge.isDiscount());
            stmt.setFloat(6, facilityCharge.getDiscountRate());
            stmt.setString(7, facilityCharge.getResistrantID());

            stmt.executeUpdate();

            return true;
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 관광지명, 시설명을 조건으로 해당 시설의 요금 조회
    // 추가적으로 요금명, 등록자를 추가하여 조회 가능
    public FacilityCharge[] lookup(HashMap<String, Object> condition) {

        try {
            // DB연결
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            Context context  = new InitialContext();
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            StringBuilder sqlBuilder = new StringBuilder();

            sqlBuilder.append("SELECT * FROM `facilitycharge` ");

            if (condition.size() > 0) {
                sqlBuilder.append("WHERE ");

                // condition은 속성과 값으로 구성되어있다.
                // iter는 테이블 속성명이 들어있다.
                Iterator<String> iter = condition.keySet().iterator();

                // 반복자를 사용해 condition에 들어있는 key:value 값을 이용하여 sql문을 작성
                while(iter.hasNext()) {
                    String columnName = iter.next();            // 테이블의 속성명
                    Object value = condition.get(columnName);   // 그 속성의 값

                    if (value instanceof String || value instanceof Integer) {
                        sqlBuilder.append(String.format("`%s` LIKE `%%s%%` ", columnName, (String)value));
                    }

                    if (iter.hasNext())
                        sqlBuilder.append("AND ");
                }
            }

            String sql = sqlBuilder.toString();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            ArrayList<FacilityCharge> facilityCharges = new ArrayList<>();

            while(rs.next()) {
                facilityCharges.add(
                   FacilityCharge.builder()
                        .workspaceID(rs.getString("workspaceID"))
                        .facilityID(rs.getString("facilityID"))
                        .chargeName(rs.getString("chargeName"))
                        .charge(rs.getInt("charge"))
                        .isDiscount(rs.getBoolean("isDiscount"))
                        .discountRate(rs.getFloat("discountRate"))
                        .resistrantID(rs.getString("resistrantID"))
                        .build()
                   );
            }

            return facilityCharges.toArray(new FacilityCharge[facilityCharges.size()]);

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean modify(String prevChargeName, FacilityCharge facilityCharge) {

        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            Context context = new InitialContext();
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            String sql = "UPDATE `facilitycharge` SET `chargeName`=?,`charge`=?,`isDiscount`=?,`discountRate`=?, WHERE `workspaceID`=?, `facilityID`=?, `chargeName`=?";
            // UPDATE `facilitycharge` SET `chargeName`=?,`charge`=?,`isDiscount`=?,`discountRate`=?, WHERE `facilityID` = ?

            stmt = conn.prepareStatement(sql);

            stmt.setString(1, facilityCharge.getChargeName());
            stmt.setInt(2, facilityCharge.getCharge());
            stmt.setBoolean(3, facilityCharge.isDiscount());
            stmt.setFloat(4, facilityCharge.getDiscountRate());
            stmt.setString(5, facilityCharge.getWorkspaceID());
            stmt.setString(6, facilityCharge.getFacilityID());
            stmt.setString(7, prevChargeName);

            stmt.executeUpdate();
            return true;

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(FacilityCharge facilityCharge) {
        
        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            Context context = new InitialContext();
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            // DELETE FROM `facilitycharge` WHERE `workspaceID`=?, `facilityID`=?, `chargeName`=?
            String sql = "DELETE FROM `facilitycharge` WHERE `workspaceID`=?, `facilityID`=?, `chargeName`=?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1, facilityCharge.getFacilityID());
            stmt.setString(2, facilityCharge.getWorkspaceID());
            stmt.setString(3, facilityCharge.getChargeName());

            stmt.executeUpdate();
            return true;
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return false;
    }
}