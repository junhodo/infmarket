package Sogong.IMS.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Sogong.IMS.model.FacilityProperty;

public class FacilityPropertyDAO {

    // Singleton - lazy holder
    private static class LazyHolder {
        private static final FacilityPropertyDAO dao = new FacilityPropertyDAO();
    }

    public static FacilityPropertyDAO getInstance() {
        return LazyHolder.dao;
    }

    public boolean enroll(FacilityProperty facilityProperty) {
		try {
			Connection conn = null;
			PreparedStatement stmt = null;
			Context context = new InitialContext();
			conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();
			final DateTimeFormatter DB_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String sql = "INSERT INTO `FacilityProperty`(`facilityPropertyID`, `facilityID`, `registrantID`, `openingDate`, `facilityScale`, `facilityCost`) VALUES (?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, facilityProperty.getFacilityPropertyID());
			stmt.setString(2, facilityProperty.getFacilityID());
            stmt.setString(3, facilityProperty.getRegistrantID());
			stmt.setString(4, facilityProperty.getOpeningDate().format(DB_TIME_FORMAT));
			stmt.setInt(5, facilityProperty.getFacilityScale());
			stmt.setInt(6, facilityProperty.getFacilityCost());
			stmt.execute();
			conn.close();
			return true;
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public FacilityProperty[] lookup(HashMap<String, Object> condtions) throws NamingException, SQLException {
		try {
			Connection conn = null;
			PreparedStatement stmt = null;

			// META-INF아래 context.xml
			Context context = new InitialContext();
			// DB Connection
			conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();


			String sql = "SELECT * FROM FacilityProperty";
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(sql);
			if(condtions.size() > 0)
				stringBuilder.append(" where ");
			Iterator<String> iter = condtions.keySet().iterator();
			while(iter.hasNext()){
				String key = iter.next();            //테이블의 속성명
				Object value = condtions.get(key);
				if (value instanceof String || value instanceof Integer)
					stringBuilder.append(String.format("`%s` LIKE '%%%s%%'", key, (String) value));
				if (iter.hasNext())
					stringBuilder.append(" and ");
			}
			stmt = conn.prepareStatement(stringBuilder.toString());
			ResultSet rs = stmt.executeQuery();

			ArrayList<FacilityProperty> list = new ArrayList<>();

			while (rs.next()) {
				String facilityPropertyID= rs.getString("facilityPropertyID");
				String facilityID = rs.getString("facilityID");
				String registrantID = rs.getString("registrantID");
				LocalDate openingDate = LocalDate.parse(rs.getString("openingDate"));
				int facilityScale = rs.getInt("facilityScale");
				int facilityCost = rs.getInt("facilityCost");
				list.add(
						new FacilityProperty(facilityPropertyID,facilityID, registrantID,openingDate,facilityScale,facilityCost)
				);
			}
			conn.close();
			return list.toArray(new FacilityProperty[list.size()]);
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean modify(FacilityProperty facilityProperty) throws NamingException, SQLException {
		try{
			final DateTimeFormatter DB_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			Connection conn = null;
			PreparedStatement stmt = null;

			// META-INF아래 context.xml
			Context context = new InitialContext();
			// DB Connection
			conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();

			String sql = "UPDATE `sogongdo`.`FacilityProperty` SET `facilityPropertyID` = ?, `facilityID` = ?, `registrantID` = ?, `OpeningDate` = ?, `facilityScale` = ?, `facilityCost` = ? WHERE `FacilityPropertyID` = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,facilityProperty.getFacilityPropertyID());
			stmt.setString(2,facilityProperty.getFacilityID());
			stmt.setString(3,facilityProperty.getRegistrantID());
			stmt.setString(4,facilityProperty.getOpeningDate().format(DB_TIME_FORMAT));
			stmt.setInt(5,facilityProperty.getFacilityScale());
			stmt.setInt(6,facilityProperty.getFacilityCost());
			stmt.setString(7,facilityProperty.getFacilityPropertyID());
			stmt.execute();
			conn.close();
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(String facilityPropertyID) {
		try {
			Connection conn = null;
			PreparedStatement stmt = null;
			Context context;
			String sql = "delete from FacilityProperty where FacilityPropertyID=?";
			context = new InitialContext();
			conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, facilityPropertyID);
			stmt.execute();
			conn.close();
			return true;
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}