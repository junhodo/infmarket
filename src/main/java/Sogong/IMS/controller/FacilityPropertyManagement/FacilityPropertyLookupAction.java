package Sogong.IMS.controller.FacilityPropertyManagement;

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
import Sogong.IMS.model.Facility;
import Sogong.IMS.model.FacilityProperty;

public class FacilityPropertyLookupAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			String[] condtionNames = {"facilityPropertyID", "facilityID" , "registrantID"};
			HashMap<String,Object> condtions = new HashMap<>();
			for(String condition : condtionNames){
				String value = request.getParameter(condition);
				if(value != "")
					condtions.put(condition, value);
			}
			ServletContext context = request.getServletContext();
			FacilityProperty[] lookupResult = FacilityPropertyDAO.getInstance().lookup(condtions);
			request.setAttribute("lookupResult", lookupResult);
			RequestDispatcher dispatcher = context.getRequestDispatcher("/facilityPropertyManage"); // 넘길 페이지 주소
			dispatcher.forward(request, response);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	}