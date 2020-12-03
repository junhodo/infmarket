package Sogong.IMS.controller.memberManagement;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.MemberDAO;
import Sogong.IMS.model.Member;

public class MemberEnrollAction implements Action {

	@Override
	public void excute(HttpServletRequest rq, HttpServletResponse rs) {
		try {
			rq.setCharacterEncoding("utf-8");
			rs.setContentType("text/html; charset=utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 아직 덜 추가된 부분이 있으니 나머지는 memberEnroll.jsp 에서 input태그의 name을 보시고 작성해주시면 됩니다.
		Member member = Member.builder()
						 .memberID(rq.getParameter("inputId"))
						 .memberPW(rq.getParameter("inputPassword"))
						 .name(rq.getParameter("inputName"))
						 .phoneNumber(rq.getParameter("inputPhoneNumber"))
						 .address(rq.getParameter("inputAddress"))
						 .email(rq.getParameter("inputEmail"))
						 .memberType(rq.getParameter("inputType"))
						 .department(rq.getParameter("inputDepartment"))
						 .build();

		PrintWriter printWriter;
		try {
			printWriter = rs.getWriter();

			if (MemberDAO.getInstance().enroll(member) == true) {
				printWriter.print("<script>alert('성공적으로 등록되었습니다')</script>");
			} else {
				printWriter.print("<script>alert('등록에 실패했습니다.')</script>");
			}
			printWriter.print("<script>self.close()</script>");
			printWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
