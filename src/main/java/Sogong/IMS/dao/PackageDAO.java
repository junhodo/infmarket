package Sogong.IMS.dao;
import Sogong.IMS.model.Package;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PackageDAO {
    private static PackageDAO packageDAO = null;

    private PackageDAO() {
    }
    public static PackageDAO getInstance(){
        if(packageDAO == null)
            packageDAO = new PackageDAO();
        return packageDAO;
    }

    public Package[] lookup(HashMap<String, Object> conditions) throws NamingException, SQLException{
        Connection conn = null;
        Context context = new InitialContext();
        conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();
        Statement statement = conn.createStatement();
        StringBuilder query = new StringBuilder();
        query.append("select * from sogongdo.package");
        Iterator<String> iter = conditions.keySet().iterator();
        if (conditions.size() > 0)
            query.append(" where ");
        while (iter.hasNext()) {
            String key = iter.next();            //테이블의 속성명
            Object value = conditions.get(key);
            if (value instanceof String || value instanceof Integer)
                query.append(String.format("`%s` LIKE '%%%s%%'", key, (String) value));
            if (value instanceof int[]) {
                int[] range = (int[]) value;
                query.append(String.format("`%s` BETWEEN %d AND %d", key, range[0], range[1]));
            }
            if (iter.hasNext())
                query.append(" and ");
        }
        ResultSet resultSet = statement.executeQuery(query.toString());
        ArrayList<Package> packages = new ArrayList<>();
        while(resultSet.next()){
            packages.add(new Package(resultSet.getString("packageID"),resultSet.getString("registrantID"),resultSet.getString("packageName"),resultSet.getString("company"),
                    resultSet.getString("type"),resultSet.getString("explanation"),resultSet.getInt("price")));
        }
        conn.close();
        return packages.toArray(new Package[packages.size()]);
    }

    public boolean enroll(Package aPackage) throws SQLException {
        Connection conn = null;
        try {
            PreparedStatement stmt = null;
            Context context = new InitialContext();
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();


            String sql = "INSERT INTO `sogongdo`.`package` (`registrantID`, `packageName`, `price`, `company`, `type`, `explanation`) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, aPackage.getRegistrantID());
            stmt.setString(2, aPackage.getPackageName());
            stmt.setInt(3, aPackage.getPrice());
            stmt.setString(4, aPackage.getCompany());
            stmt.setString(5, aPackage.getType());
            stmt.setString(6, aPackage.getExplanation());
            stmt.execute();
            conn.close();
            return true;
        }
        catch (Exception e){
            conn.close();
            return false;
        }
    }

    public boolean delete(int packageID) throws SQLException {
        Connection conn = null;
        try {
            PreparedStatement stmt = null;
            Context context = new InitialContext();
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();
            String sql = "DELETE FROM `sogongdo`.`package` WHERE (`packageID` = ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, packageID);
            stmt.execute();
            conn.close();
            return true;
        }
        catch(Exception e){
            conn.close();
            return false;
        }

    }

    public boolean modify(Package aPakcage) throws SQLException {
        Connection conn = null;
        try {
            PreparedStatement stmt = null;
            Context context = new InitialContext();
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();
            String sql = "UPDATE `sogongdo`.`package` SET `registrantID` = ?, `packageName` = ?, `price` = ?, " +
                    "`company` = ?, `type` = ?, `explanation` = ? WHERE (`packageID` = ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, aPakcage.getRegistrantID());
            stmt.setString(2, aPakcage.getPackageName());
            stmt.setInt(3, aPakcage.getPrice());
            stmt.setString(4, aPakcage.getCompany());
            stmt.setString(5, aPakcage.getType());
            stmt.setString(6, aPakcage.getExplanation());
            stmt.setString(7, aPakcage.getPackageID());
            stmt.execute();
            conn.close();
            return true;
        }
        catch(Exception e){
            conn.close();
            return false;
        }


    }
}
