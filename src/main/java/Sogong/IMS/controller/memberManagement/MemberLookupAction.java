package Sogong.IMS.controller.memberManagement;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.MemberDAO;
import Sogong.IMS.model.Member;

public class MemberLookupAction implements Action{

	@Override
	public void excute(HttpServletRequest rq, HttpServletResponse rs) {
		try {
			rq.setCharacterEncoding("utf-8");
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String[] conditionNames = {"memberID", "name"};
		HashMap<String, Object> conditions = new HashMap<>();
		
		for(String condition : conditionNames) {
			String value = StringUtils.defaultIfBlank(rq.getParameter(condition), null);
			
			if(value != null)
				conditions.put(condition, value);
		}
		
		ArrayList<Member> members = new ArrayList<>(Arrays.asList(MemberDAO.getInstance().lookup(conditions)));

		rq.setAttribute("members", members);
		ServletContext context = rq.getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/memberManage");
		
		try {
			dispatcher.forward(rq, rs);
		} catch(ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
