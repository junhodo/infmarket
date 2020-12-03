package Sogong.IMS.dao;

import Sogong.IMS.model.AccomodationBookCancleHistory;
import Sogong.IMS.model.Member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AccomodationBookCancleHistoryDAO {
    public boolean enroll(AccomodationBookCancleHistory accomodationBookCancleHistory) {

        try {

            //DB 연결
            Connection conn = null;
            PreparedStatement stmt = null;

            
            //META-INF아래 context.xml
            Context context = new InitialContext();
            //DB Connection
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

        /*
            String url= "jdbc:mysql://totomo.iptime.org:3306/sogongdo?serverTimezone=UTC&zeroDateTimeBehavior=convertToNull";
            String id= "admin";
            String pwd= "tejava";

            conn = DriverManager.getConnection(url, id, pwd);
        */

            /*
             * `accomodationbookcanclehistory` ( 
             * `accomodationBookCancleHistoryID` varchar(20) NOT NULL, 
             * `cancleDate` date NOT NULL, 
             * `cancleReason` varchar(200) NOT NULL, 
             * `registrantID` varchar(20) NOT NULL, 
             * `accomodationBookHistoryID` varchar(20) NOT NULL,
             */
            String sql = "INSERT INTO `accomodationbookcanclehistory` VALUES (?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, accomodationBookCancleHistory.getAccomodationBookCancleHistoryID());
            stmt.setString(2, accomodationBookCancleHistory.getAccomodationBookHistoryID());
            stmt.setDate(3, Date.valueOf(accomodationBookCancleHistory.getCancleDate()));
            stmt.setString(4, accomodationBookCancleHistory.getCancleReason());
            stmt.setString(5, accomodationBookCancleHistory.getRegistrantID());
           
         
            stmt.execute();

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

   

    public boolean modify(AccomodationBookCancleHistory accomodationBookCancleHistory ){
        try {

            //DB 연결
            Connection conn = null;
            PreparedStatement stmt = null;

            //META-INF아래 context.xml
            Context context = new InitialContext();
            //DB Connection
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();
            /*
             * `accomodationbookcanclehistory` ( 
             * `accomodationBookCancleHistoryID` varchar(20) NOT NULL, 
             * `cancleDate` date NOT NULL, 
             * `cancleReason` varchar(200) NOT NULL, 
             * `registrantID` varchar(20) NOT NULL, 
             * `accomodationBookHistoryID` varchar(20) NOT NULL,
             */
            String sql = "UPDATE 'accomodationbookhistory' SET cancleReason = ? WHERE accomodationBookHistoryID = ?";
            stmt = conn.prepareStatement(sql);

           
            stmt.setString(1, accomodationBookCancleHistory.getCancleReason());
            stmt.setString(2, accomodationBookCancleHistory.getAccomodationBookCancleHistoryID());
            stmt.execute();

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

    public boolean delete(AccomodationBookCancleHistory accomodationBookCancleHistory){
        try {

            //DB 연결
            Connection conn = null;
            PreparedStatement stmt = null;

            //META-INF아래 context.xml
            Context context = new InitialContext();
            //DB Connection
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();
           
            String sql = "DELETE FROM 'accomodationbookhistory' WHERE accomodationBookHistoryID = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, accomodationBookCancleHistory.getAccomodationBookCancleHistoryID());
            stmt.execute();

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

    public AccomodationBookCancleHistory[] lookup(HashMap<String, Object> condition) {

        try {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            Context context = new InitialContext();
            conn = ((DataSource)context.lookup("java:comp/env/jdbc/mysql")).getConnection();

            // conn = DriverManager.getConnection("jdbc:mysql://totomo.iptime.org:3306/sogongdo?serverTimezone=UTC", "admin", "tejava");

            StringBuilder sqlBuilder = new StringBuilder();

            sqlBuilder.append("SELECT * FROM ")
                        .append("`accomodationbookcanclehistory` ");

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

                    // 자료형이 LocalDate[]   여기에는 시작일과 종료일 둘다 있으므로 배열이 됩니다.
                    if(value instanceof LocalDate[]){
                        LocalDate[] dateRange = (LocalDate[])value;
                        sqlBuilder.append(String.format("`%s` BETWEEN '%s' AND  '%s'",columnName, dateRange[0].toString(),dateRange[1].toString()));
                    }

                    if (iter.hasNext())
                        sqlBuilder.append("AND ");
                }
            }

            String sql = sqlBuilder.toString();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            ArrayList<AccomodationBookCancleHistory> accomodationBookCancleHistories = new ArrayList<>();

            while (rs.next()) {
                // 코드 처리부

                accomodationBookCancleHistories.add(
                    new AccomodationBookCancleHistory(
                        rs.getString("accomodationBookCancleHistoryID") , 
                        rs.getObject("cancleDate", LocalDate.class), 
                        rs.getString("cancleReason"), 
                        rs.getString("registrantID"),
                        rs.getString("accomodationBookHistoryID")
                    )
                );

            }

            return accomodationBookCancleHistories.toArray(new AccomodationBookCancleHistory[accomodationBookCancleHistories.size()]);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;


/*
        try {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            // db 연결 코드
            String url = "jdbc:mysql://totomo.iptime.org:3306/sogongdo?serverTimezone=UTC&zeroDateTimeBehavior=convertToNull";
            String id = "admin";
            String pwd = "tejava";

            conn = DriverManager.getConnection(url, id, pwd);

            stmt = conn.prepareStatement("SELECT * FROM accomodationbookcanclehistory");
            rs = stmt.executeQuery();

            ArrayList<AccomodationBookCancleHistory> accomodationBookCancleHistories = new ArrayList<>();

            while(rs.next()){
               accomodationBookCancleHistories.add(new AccomodationBookCancleHistory(
                   rs.getString("accomodationBookCancleHistoryID") , rs.getObject("cancleDate", LocalDate.class), 
                   rs.getString("cancleReason"), rs.getString("registrantID"), rs.getString("accomodationBookHistoryID")));
              
            }

            return accomodationBookCancleHistories.toArray(new AccomodationBookCancleHistory[accomodationBookCancleHistories.size()]);
          

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
      

        return null;
        */
    }
}