package Sogong.IMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Sogong.IMS.model.RefundHistory;

public class RefundHistoryDAO {
    public boolean enroll(RefundHistory refundHistory) 
    {
        try {
            // DB 연결
            Connection conn = null;
            PreparedStatement stmt = null;

            Context context = new InitialContext();
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            String sql = "INSERT INTO `RefundHistory`(`paymentHistoryID`,`accomodationBookID`,`registantID`,`price`,`paymentMethod`, `paymentTime`) VALUES (?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,refundHistory.getRefundHistoryID());
            stmt.setString(2,refundHistory.getPaymentHistoryID());
            stmt.setString(3,refundHistory.getRegistrantID());
            stmt.setString(4,refundHistory.getResonsRefund());
            stmt.setInt(5,refundHistory.getRefundPrice());
            stmt.setTimestamp(6, Timestamp.valueOf(refundHistory.getRefundTime()));
            stmt.setString(7,refundHistory.getRefundMethod());
            
            stmt.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return false;
    }
    public RefundHistory[] lookup(HashMap<String, Object> condition)
    {
        try 
        {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            Context context = new InitialContext();
            conn = ((DataSource)context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            // conn = DriverManager.getConnection("jdbc:mysql://totomo.iptime.org:3306/sogongdo?serverTimezone=UTC", "admin", "tejava");

            StringBuilder sqlBuilder = new StringBuilder();

            sqlBuilder.append("SELECT * FROM (SELECT A.name, PH.`price`, AH.`checkInTime` , AH.`checkOutTime`,PH.`paymentTime`, PH.`paymentMethod`, PH.`registantID` FROM `paymenthistory` as PH JOIN `accomodationbookhistory` as AH ON PH.`accomodationBookHistoryID` = AH.`accomodationBookHistoryID` JOIN `accomodation` as A ON AH.`accomodationID` = A.`accomodationID`) as RESULT ");

            // 조건 검색
            if (condition.size()>0) 
            {
                sqlBuilder.append("WHERE ");

                // condition은 속성과 값으로 구성되어있다.
                // iter는 테이블 속성명이 들어있다.
                
                Iterator<String> iter = condition.keySet().iterator();

                while (iter.hasNext()) 
                {
                    String columnName = iter.next();            //테이블의 속성명

                    Object value = condition.get(columnName);   //그 속성의 값

                    // 자료형이 String이나 Integer라면
                    if(value instanceof String || value instanceof Integer)
                    {
                        sqlBuilder.append(String.format("`%s` LIKE '%%%s%%' ", columnName, (String)value));
                    }

                    // 자료형이 LocalDate[]   여기에는 시작일과 종료일 둘다 있으므로 배열이 됩니다.
                    if(value instanceof LocalDateTime[])
                    {
                        LocalDateTime[] dateTimeRange = (LocalDateTime[])value;
                        sqlBuilder.append(String.format("`%s` BETWEEN '%s' AND  '%s'",columnName, dateTimeRange[0].toString(),dateTimeRange[1].toString()));
                    }

                    if (iter.hasNext())
                        sqlBuilder.append("AND ");
                }
            }
            String sql = sqlBuilder.toString();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            ArrayList<RefundHistory> refundList = new ArrayList<>();

            while (rs.next()) 
            {
                // 코드 처리부

                refundList.add
                (
                    RefundHistory.builder()
                                        .refundHistoryID(rs.getString("refundHistoryID"))
                                        .paymentHistoryID(rs.getString("paymentHistoryID"))
                                        .registrantID(rs.getString("registrantID"))
                                        .refundPrice(rs.getInt("refundPrice"))
                                        .resonsRefund(rs.getString("resonsRefund"))
                                        .refundMethod(rs.getString("refundMethod"))
                                        .refundTime(rs.getTimestamp("paymentTime").toLocalDateTime())
                                        .build()
                );

            }

            return refundList.toArray(new RefundHistory[refundList.size()]);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public boolean delete(RefundHistory refundHistory)
    {
        try 
        {
            Connection conn = null;
            PreparedStatement stmt = null;

            // META-INF아래 context.xml
            Context context = new InitialContext();
            // DB Connection
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            stmt = conn.prepareStatement(
                    "DELETE FROM `PaymentHistory` WHERE `paymentHistoryID`=?");
            stmt.setString(1, refundHistory.getPaymentHistoryID());
            
            return stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean modify(RefundHistory refundHistory)
    {
        
        try {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            Context context = new InitialContext();
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();


            String sql = "UPDATE `sogongdo`.`refundHistory` SET `resonsRefund` =?, `refundPrice` = ?, `refundTime` = ?,`refundMethod` WHERE `refundHistoryID` = ?;";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,refundHistory.getRefundHistoryID());
            stmt.setString(2,refundHistory.getPaymentHistoryID());
            stmt.setString(3,refundHistory.getRegistrantID());
            stmt.setString(4,refundHistory.getResonsRefund());
            stmt.setInt(5,refundHistory.getRefundPrice());
            stmt.setTimestamp(6, Timestamp.valueOf(refundHistory.getRefundTime()));
            stmt.setString(7,refundHistory.getRefundMethod());
            

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