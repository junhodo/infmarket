package Sogong.IMS.controller.FacilityManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.controller.FacilityManagement.*;
import Sogong.IMS.dao.MemberAuthorityGroupDAO;
import Sogong.IMS.controller.Action;
import Sogong.IMS.model.Member;

@WebServlet("/facilityManage/*")
public class FacilityController extends HttpServlet {
    private static final long serialVersionUID = 8559171819500212874L;

    HashMap<String, Action> list = null;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        String servletPath = request.getServletPath();
        String path = url.substring(servletPath.length()).split("/")[1];


        HashMap<String,String> authorityList = new HashMap<>();
        authorityList.put("enroll.do", "시설_등록");
        authorityList.put("modify.do", "시설_수정");
        authorityList.put("delete.do", "시설_삭제");
        authorityList.put("lookup.do", "시설_조회");

        if(request.getSession().getAttribute("member") != null && hasAuthority((Member) request.getSession().getAttribute("member"), authorityList.get(path))){
            Action action = list.get(path);
            action.excute(request, response);
        }
        else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(String.format("<script>alert('권한이 없습니다'); location.replace('%s')</script>", request.getServletPath()));
        }
    }
    @Override
    public void init(ServletConfig sc) throws ServletException {
        list = new HashMap<>();
        list.put("enroll.do", new FacilityEnrollAction());
        list.put("modify.do", new FacilityModifyAction());
        list.put("delete.do", new FacilityDeleteAction());
        list.put("lookup.do", new FacilityLookupAction());
    }

    public boolean hasAuthority(Member member, String authorityName) {
        MemberAuthorityGroupDAO memberAuthorityGroupDAO = new MemberAuthorityGroupDAO();
        return memberAuthorityGroupDAO.hasAuthority(member, authorityName);
    }
}