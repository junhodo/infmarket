package Sogong.IMS.controller.WorkspaceManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.MemberAuthorityGroupDAO;
import Sogong.IMS.model.Member;

@WebServlet({"/workspaceManage/*"})
public class WorkspaceController extends HttpServlet{
    HashMap<String, Action> actionList;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

            HashMap<String, String> authorityList = new HashMap<>();
            authorityList.put("enroll.do", "사업장_등록");
            authorityList.put("modify.do", "사업장_수정");
            authorityList.put("delete.do", "사업장_삭제");
            authorityList.put("lookup.do", "사업장_조회");

            PrintWriter printWriter = response.getWriter();

            String url = request.getRequestURI();
            String servletPath = request.getServletPath();
    
            String path = url.substring(servletPath.length()).split("/")[1];
    
            if(request.getSession().getAttribute("member") != null && hasAuthority((Member) request.getSession().getAttribute("member"), authorityList.get(path))){
                Action action = actionList.get(path);
                action.excute(request, response);
            }
            else
                printWriter.print(String.format("<script>alert('권한이 없습니다'); location.replace('%s')</script>",request.getServletPath()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        actionList = new HashMap<>();
        actionList.put("enroll.do", new WorkspaceEnrollAction());
        actionList.put("modify.do", new WorkspaceModifyAction());
        actionList.put("delete.do", new WorkspaceDeleteAction());
        actionList.put("lookup.do", new WorkspaceLookupAction());
    }

    public boolean hasAuthority(Member member, String authorityName) {
        return new MemberAuthorityGroupDAO().hasAuthority(member, authorityName);
    }
}