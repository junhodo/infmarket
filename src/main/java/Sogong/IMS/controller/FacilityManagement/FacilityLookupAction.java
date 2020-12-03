package Sogong.IMS.controller.FacilityManagement;


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

import Sogong.IMS.dao.FacilityDAO;
import Sogong.IMS.dao.FacilityPropertyDAO;
import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.PackageDAO;
import Sogong.IMS.model.Facility;
import Sogong.IMS.model.FacilityProperty;
import Sogong.IMS.model.Package;

public class FacilityLookupAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("ac");
			request.setCharacterEncoding("utf-8");
			String[] condtionNames = {"facilityID", "facilityName" , "registrantID" , "workspaceID"};
			HashMap<String,Object> condtions = new HashMap<>();
			for(String condition : condtionNames){
				String value = request.getParameter(condition);
				if(value != "")
					condtions.put(condition, value);
			}
			Facility[] lookupResult = FacilityDAO.getInstance().lookup(condtions);
			request.setAttribute("lookupResult", lookupResult);
			ServletContext context = request.getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/facilityManage"); // 넘길 페이지 주소
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}