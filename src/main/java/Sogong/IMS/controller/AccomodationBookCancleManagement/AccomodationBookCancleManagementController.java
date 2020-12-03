package Sogong.IMS.controller.AccomodationBookCancleManagement;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.MemberAuthorityGroupDAO;
import Sogong.IMS.model.Member;
import lombok.SneakyThrows;

@WebServlet({ "/accomodationBookCancleHistoryManage/*", "/accomodationBookCancleHistoryEnroll/*" })
public class AccomodationBookCancleManagementController extends HttpServlet {
     
    private static final long serialVersionUID = 8559000000000000001L;
     private HashMap<String, Action> list = null;
     private HashMap<String, String> authorityList = null;
     @Override
     public void init(ServletConfig sc) throws ServletException {
         list = new HashMap<>();
         list.put("enroll.do", new AccomodationBookCancleHistoryEnrollAction());
         list.put("lookup.do", new AccomodationBookCancleHistoryLookupAction());
         list.put("modify.do", new AccomodationBookCancleHistoryModifyAction());
         list.put("delete.do", new AccomodationBookCancleHistoryDeleteAction());
         authorityList = new HashMap<>();
        authorityList.put("enroll.do", "예약취소_등록");
        authorityList.put("modify.do", "예약취소_수정");
        authorityList.put("delete.do", "예약취소_삭제");
        authorityList.put("lookup.do", "예약취소_조회");
     }
 
     // get이나 post 요청에 대한 처리를 수행합니다.
     @SneakyThrows
     @Override
     public void service(HttpServletRequest request, HttpServletResponse response) {
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