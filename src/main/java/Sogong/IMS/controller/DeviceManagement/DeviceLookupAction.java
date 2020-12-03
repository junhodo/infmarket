package Sogong.IMS.controller.DeviceManagement;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.dom.DOMValidateContext;

import Sogong.IMS.dao.DeviceDAO;
import Sogong.IMS.dao.FacilityPropertyDAO;
import Sogong.IMS.controller.Action;
import Sogong.IMS.model.Device;
import Sogong.IMS.model.FacilityProperty;

public class DeviceLookupAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
			 try {
				 request.setCharacterEncoding("utf-8");
				 String[] condtionNames = {"deviceID", "facilityPropertyID" , "registrantID" , "deviceName", "instruction"};
				 HashMap<String,Object> condtions = new HashMap<>();
				 for(String condition : condtionNames){
					 String value = request.getParameter(condition);
					 if(value != "")
						 condtions.put(condition, value);
				 }
				 Device[] lookupResult = DeviceDAO.getInstance().lookup(condtions);
				 request.setAttribute("lookupResult",lookupResult);
				 ServletContext context = request.getServletContext();
				 RequestDispatcher dispatcher = context.getRequestDispatcher("/facilityDeviceManage"); // 넘길 페이지 주소
				 dispatcher.forward(request, response);
			 } catch (Exception e1) {
				 e1.printStackTrace();
			 }
	}
}