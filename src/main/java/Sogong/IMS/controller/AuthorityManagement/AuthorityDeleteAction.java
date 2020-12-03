package Sogong.IMS.controller.AuthorityManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.MemberAuthorityGroupDAO;
import Sogong.IMS.model.Member;
import Sogong.IMS.model.MemberAuthorityGroup;
import org.apache.commons.lang3.StringUtils;

public class AuthorityDeleteAction implements Action {

    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub

        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            ServletContext context = request.getServletContext();

            RequestDispatcher dispatcher = context.getRequestDispatcher("/authorityManage"); // 넘길 페이지 주소
            dispatcher.include(request, response);

            String memberID = StringUtils.defaultIfBlank(request.getParameter("inputMemberID"), null);

            if (memberID != null) {

                HashMap<String, Object> condition = new HashMap<>();
                condition.put("memberID", memberID);

                Member member = new MemberAuthorityGroupDAO().lookup(condition)[0];

                Boolean isOK = false;

                for (MemberAuthorityGroup mag : member.getMemberAuthorityGroups()) {
                    isOK = new MemberAuthorityGroupDAO().delete(mag);
                }

                if (isOK) {
                    out.println("<script>alert('삭제되었습니다.')</script>");
                } else {
                    out.println("<script>alert('실패했습니다.')</script>");
                }
                out.flush();
            }
        
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}