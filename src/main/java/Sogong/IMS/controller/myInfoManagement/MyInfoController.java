package Sogong.IMS.controller.myInfoManagement;

import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.controller.Action;
import Sogong.IMS.controller.memberManagement.MemberDeleteAction;
import Sogong.IMS.controller.memberManagement.MemberModifyAction;

@WebServlet("/myInfo/*")
public class MyInfoController extends HttpServlet{
	HashMap<String, Action> list = null;
	
	@Override
	public void init(ServletConfig sc) throws ServletException {
		list = new HashMap<>();
		list.put("modify.do", new MemberModifyAction());
		list.put("delete.do", new MemberDeleteAction());
	}
	
	@Override
	public void service(HttpServletRequest rq, HttpServletResponse rs){
		String url = rq.getRequestURI();
		String servletPath = rq.getServletPath();
		String path = url.substring(servletPath.length()).split("/")[1];
		
		Action action = list.get(path);
		action.excute(rq, rs);
	}
}
