package Sogong.IMS.dao;

import Sogong.IMS.model.PackagePurchaseHistoryByNonMember;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class PackagePurchaseHistoryByNonMemberDAO {
    private PackagePurchaseHistoryByNonMemberDAO() {}


    private static class LazyHolder {
        private static final PackagePurchaseHistoryByNonMemberDAO INSTANCE = new PackagePurchaseHistoryByNonMemberDAO();
    }
    public static PackagePurchaseHistoryByNonMemberDAO getInstance(){
        return LazyHolder.INSTANCE;
    }

    public boolean enroll(PackagePurchaseHistoryByNonMember packagePurchaseHistoryByNonMemberDAO) throws NamingException, SQLException {
        Connection conn = null;
        try {
            Context context = new InitialContext();
            conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();
            final DateTimeFormatter DB_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            PreparedStatement stmt = null;
            String sql = "INSERT INTO `sogongdo`.`packagesaleshistorybynonmember` (`packageID`, `buyerName`, `buyerEmail`, `buyerPhoneNumber`, `purchaseDate`, `numPurchase`, `totalPrice`, `state` , `paymentMethod`) " +
                    "VALUES (?, ?, ?, ?, ?, ?,?,?,?);";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(packagePurchaseHistoryByNonMemberDAO.getaPackage().getPackageID()));
            stmt.setString(2, packagePurchaseHistoryByNonMemberDAO.getBuyerName());
            stmt.setString(3, packagePurchaseHistoryByNonMemberDAO.getBuyerEmail());
            stmt.setString(4, packagePurchaseHistoryByNonMemberDAO.getBuyerPhoneNumber());
            stmt.setString(5, packagePurchaseHistoryByNonMemberDAO.getPurchaseDate().format(DB_TIME_FORMAT));
            stmt.setInt(6, packagePurchaseHistoryByNonMemberDAO.getNumPurchase());
            stmt.setInt(7, packagePurchaseHistoryByNonMemberDAO.getTotalPrice());
            stmt.setString(8, packagePurchaseHistoryByNonMemberDAO.getState());
            stmt.setString(9, packagePurchaseHistoryByNonMemberDAO.getPaymentMethod());
                        stmt.execute();
            conn.close();
            return true;
        }
        catch (Exception e){
            conn.close();
            return false;
        }
    }

}
