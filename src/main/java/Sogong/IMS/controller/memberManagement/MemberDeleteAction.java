package Sogong.IMS.controller.memberManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.MemberDAO;

public class MemberDeleteAction implements Action {

	@Override
	public void excute(HttpServletRequest rq, HttpServletResponse rs) {
		try {
			rq.setCharacterEncoding("utf-8");
            rs.setCharacterEncoding("utf-8");
            rs.setContentType("text/html; charset=utf-8");
            PrintWriter printWriter = rs.getWriter();
            ServletContext context = rq.getServletContext();

			RequestDispatcher dispatcher = context.getRequestDispatcher("/memberManage");
			dispatcher.include(rq, rs);

			String memberID = StringUtils.defaultIfBlank(rq.getParameter("memberID"), null);

			if (MemberDAO.getInstance().delete(memberID) == true) {
				printWriter.print("<script>alert('성공적으로 삭제되었습니다')</script>");
			} else {
				printWriter.print("<script>alert('삭제에 실패했습니다.')</script>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
