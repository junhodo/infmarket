package Sogong.IMS.dao;

import Sogong.IMS.model.PackageSalesPerformance;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

public class PackageSalesPerformanceDAO {
    private static class LazyHolder{
        private static final PackageSalesPerformanceDAO packageSalesPerformanceDAO = new PackageSalesPerformanceDAO();
    }
    public static PackageSalesPerformanceDAO getInstance(){
        return LazyHolder.packageSalesPerformanceDAO;
    }

    public PackageSalesPerformance[] lookup(HashMap<String,Object> conditions) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        Context context = new InitialContext();
        conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

        String sql = "select * from (select q.packageID, d.packageName, d.type, d.company, d.price ,sum(q.totalPrice) as totalPrice, sum(q.numPurchase) as numTotalPurchase  from\n" +
                "                (select * from (select packageID , purchaseDate, numPurchase, totalPrice from sogongdo.packagesaleshistorybymember as a \n" +
                "                union \n" +
                "                select packageID, purchaseDate, numPurchase, totalPrice from sogongdo.packagesaleshistorybynonmember as b) as c where ? <= c.purchaseDate and c.purchaseDate <= ? \n" +
                "                ) as q ,package as d where q.packageID = d.packageID group by d.packageID) as r where r.packageName like ? and r.type like ? and r.company like ? and ? <= r.totalPrice and " +
                "r.totalPrice <= ? and ? <= r.numTotalPurchase and r.numTotalPurchase <= ?";
        int idx = 1;
        stmt = conn.prepareStatement(sql);
        for(String key : conditions.keySet()){
            if(conditions.get(key) instanceof Integer)
                stmt.setInt(idx++, (Integer) conditions.get(key));
            else
                stmt.setString(idx++, (String) conditions.get(key));
        }
                ResultSet rs = stmt.executeQuery();
        Vector<PackageSalesPerformance> results = new Vector<>();
        while(rs.next()){
            String packageID = rs.getString(1);
            String packageName = rs.getString(2);
            String type = rs.getString(3);
            String company = rs.getString(4);
            int price  = rs.getInt(5);
            int totalPrice  = rs.getInt(6);
            int numTotalPurchase = rs.getInt(7);
            results.add(new PackageSalesPerformance(packageID,packageName,type,company,price,totalPrice,numTotalPurchase));
        }
        conn.close();
        return results.toArray(new PackageSalesPerformance[results.size()]);
    }



}
