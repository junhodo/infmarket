package Sogong.IMS.controller.AccomodationBookManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.MemberAuthorityGroupDAO;
import Sogong.IMS.model.Member;
import lombok.SneakyThrows;

@WebServlet({ "/accomodationBookHistoryManage/*", "/accomodationBookHistoryEnroll/*" })
public class AccomodationBookManagementController extends HttpServlet {
    private static final long serialVersionUID = 8559000000000000002L;

    private HashMap<String, Action> list = null;
    private HashMap<String, String> authorityList = null;

    @Override
    public void init(ServletConfig sc) throws ServletException {
        list = new HashMap<>();
        list.put("enroll.do", new AccomodationBookHistoryEnrollAction());
        list.put("lookup.do", new AccomodationBookHistoryLookupAction());
        list.put("modify.do", new AccomodationBookHistoryModifyAction());
        list.put("delete.do", new AccomodationBookHistoryDeleteAction());
        authorityList = new HashMap<>();
        authorityList.put("enroll.do", "예약_등록");
        authorityList.put("modify.do", "예약_수정");
        authorityList.put("delete.do", "예약_삭제");
        authorityList.put("lookup.do", "예약_조회");
    }

    // get이나 post 요청에 대한 처리를 수행합니다.
    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String url = request.getRequestURI();
        String servletPath = request.getServletPath();
        String path = url.substring(servletPath.length()).split("/")[1];
        PrintWriter printWriter = response.getWriter();
     
        if(request.getSession().getAttribute("member") != null && hasAuthority((Member) request.getSession().getAttribute("member"), authorityList.get(path))){
            Action action = list.get(path);
            action.excute(request, response);
        }
        else
            printWriter.print(String.format("<script>alert('권한이 없습니다'); location.replace('%s')</script>",request.getServletPath()));
    }

    public boolean hasAuthority(Member member, String authorityName) {
        MemberAuthorityGroupDAO memberAuthorityGroupDAO = new MemberAuthorityGroupDAO();

        return memberAuthorityGroupDAO.hasAuthority(member, authorityName);
    }
}