package Sogong.IMS.dao;

import Sogong.IMS.model.PackagePurchaseHistoryByMember;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class PackagePurchaseHistoryByMemberDAO {
    private PackagePurchaseHistoryByMemberDAO() {}
    private static class LazyHolder {
        private static final PackagePurchaseHistoryByMemberDAO INSTANCE = new PackagePurchaseHistoryByMemberDAO();
    }
    public static PackagePurchaseHistoryByMemberDAO getInstance(){
        return LazyHolder.INSTANCE;
    }


    public boolean enroll(PackagePurchaseHistoryByMember packagePurchaseHistoryByMember) throws SQLException {
        Connection conn = null;
        try {
            final DateTimeFormatter DB_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            PreparedStatement stmt = null;
            Context context = new InitialContext();
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();
            String sql = "INSERT INTO `sogongdo`.`packagesaleshistorybymember` (`packageID`, `memberID`, `purchaseDate`, `numPurchase`, `totalPrice`, `state` , `paymentMethod`) " +
                    "VALUES (?, ?, ?, ?, ?, ?,?);";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(packagePurchaseHistoryByMember.getaPackage().getPackageID()));
            stmt.setString(2, packagePurchaseHistoryByMember.getMember().getMemberID());
            stmt.setString(3, packagePurchaseHistoryByMember.getPurchaseDate().format(DB_TIME_FORMAT));
            stmt.setInt(4, packagePurchaseHistoryByMember.getNumPurchase());
            stmt.setInt(5, packagePurchaseHistoryByMember.getTotalPrice());
            stmt.setString(6, packagePurchaseHistoryByMember.getState());
            stmt.setString(7, packagePurchaseHistoryByMember.getPaymentMethod());
            stmt.execute();
            conn.close();
            return true;
        }
        catch (Exception e){
            conn.close();
            return false;
        }
    }

    public PackagePurchaseHistoryByMember[] enroll(HashMap<String,String> conditions){
        return null;
    }

}
