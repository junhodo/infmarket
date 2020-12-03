package Sogong.IMS.controller.AuthorityManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.MemberAuthorityGroupDAO;
import Sogong.IMS.dao.MemberDAO;
import Sogong.IMS.model.Member;

@WebServlet("/authorityManage/*")
public class AuthorityController extends HttpServlet {

    private static final long serialVersionUID = 8559171819500212874L;

    HashMap<String, Action> list = null;

    @Override
    public void init(ServletConfig sc) throws ServletException {
        list = new HashMap<>();

        list.put("enroll.do", new AuthorityEnrollAction());
        list.put("lookup.do", new AuthorityLookupAction());
        list.put("modify.do", new AuthorityModifyAction());
        list.put("delete.do", new AuthorityDeleteAction());
    }

    // get이나 post 요청에 대한 처리를 수행합니다.
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            HashMap<String, String> authorityList = new HashMap<>();
            authorityList.put("enroll.do", "관리자");
            authorityList.put("modify.do", "관리자");
            authorityList.put("delete.do", "관리자");
            authorityList.put("lookup.do", "관리자");

            String url = request.getRequestURI();
            String servletPath = request.getServletPath();
            String path = url.substring(servletPath.length()).split("/")[1];

            if (request.getSession().getAttribute("member") != null
                    && hasAuthority((Member) request.getSession().getAttribute("member"), authorityList.get(path))) {
                Action action = list.get(path);
                action.excute(request, response);
            } else {
                out.print(String.format("<script>alert('권한이 없습니다'); location.replace('%s');</script>",
                        request.getServletPath()));
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean hasAuthority(Member member, String authorityName) {
        return new MemberAuthorityGroupDAO().hasAuthority(member, authorityName);
    }
}