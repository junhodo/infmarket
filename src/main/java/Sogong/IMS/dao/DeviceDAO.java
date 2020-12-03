package Sogong.IMS.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import Sogong.IMS.model.Device;

public class DeviceDAO {

    // Singleton - lazy holder
    private static class LazyHolder {
        private static final DeviceDAO dao = new DeviceDAO();
    }

    public static DeviceDAO getInstance() {
        return LazyHolder.dao;
    }

    public boolean enroll(Device device) throws NamingException {
			try {
				Connection conn = null;
				PreparedStatement stmt = null;
				Context context = new InitialContext();
				String sql = "insert into Device (deviceID, facilityPropertyID, registrantID, deviceName, instruction, instructionCost) values(?, ?, ?, ?, ?, ?)";
				context = new InitialContext();
				conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, device.getDeviceID());
				stmt.setString(2, device.getFacilityPropertyID());
				stmt.setString(3, device.getRegistrantID());
				stmt.setString(4, device.getDeviceName());
				stmt.setString(5, device.getInstruction());
				stmt.setInt(6, device.getInstructionCost());
				stmt.execute();
				conn.close();
				return true;
			}catch (Exception e){
				e.printStackTrace();
				return false;
			}
    }

	public Device[] lookup(HashMap<String, Object> condtions) throws NamingException, SQLException {
    	try {
			Connection conn = null;
			PreparedStatement stmt = null;
			Context context = new InitialContext();
			conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();
			String sql = "SELECT * FROM Device";
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(sql);
			if(condtions.size() > 0)
				stringBuilder.append(" where ");
			Iterator<String> iter = condtions.keySet().iterator();
			while (iter.hasNext()){
				String key = iter.next();
				Object value = condtions.get(key);
				if (value instanceof String || value instanceof Integer)
					stringBuilder.append(String.format("`%s` LIKE '%%%s%%'", key, (String) value));
				if (iter.hasNext())
					stringBuilder.append(" and ");
			}
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			ArrayList<Device> list = new ArrayList<>();
			while (rs.next()) {
				String deviceID = rs.getString("deviceID");
				String facilityPropertyID = rs.getString("facilityPropertyID");
				String registrantID = rs.getString("registrantID");
				String deviceName = rs.getString("deviceName");
				String instruction = rs.getString("instruction");
				int instructionCost = rs.getInt("instructionCost");
				list.add(new Device(deviceID, facilityPropertyID, registrantID, deviceName, instruction, instructionCost));
			}
			conn.close();
			return list.toArray(new Device[list.size()]);
		}catch (Exception e){
    		e.printStackTrace();
    		return null;
		}
	}

	public boolean modify(Device device) throws SQLException, NamingException {
    	try{
			Connection conn = null;
			PreparedStatement stmt = null;
			Context context = new InitialContext();
			conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();
			String sql = "UPDATE `sogongdo`.`Device` SET `deviceID` = ?, `facilityPropertyID` = ?, `registrantID` = ?, `deviceName` = ?, `instruction` = ?, `instructionCost` = ? WHERE `DeviceID` = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, device.getDeviceID());
			stmt.setString(2, device.getFacilityPropertyID());
			stmt.setString(3, device.getRegistrantID());
			stmt.setString(4, device.getDeviceName());
			stmt.setString(5, device.getInstruction());
			stmt.setInt(6, device.getInstructionCost());
			stmt.setString(7, device.getDeviceID());
			stmt.execute();
			conn.close();
			return true;
		}catch (Exception e){
    		e.printStackTrace();
    		return false;
		}

	}

	public boolean delete(String deviceID) {
    	try{
			Connection conn = null;
			PreparedStatement stmt = null;
			Context context;
			String sql = "delete from Device where deviceID=?";
			context = new InitialContext();
			conn = ((DataSource) context.lookup("java:comp/env/jdbc/mysql")).getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, deviceID);
			stmt.execute();
			conn.close();
			return true;
		}catch (Exception e){
    		e.printStackTrace();
    		return false;
		}
	}
}