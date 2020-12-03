package Sogong.IMS.controller.WorkspaceManagement;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.WorkspaceDAO;
import Sogong.IMS.model.Member;
import Sogong.IMS.model.Workspace;

public class WorkspaceEnrollAction implements Action {

    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            

            Member member = (Member) request.getSession().getAttribute("member");
            Workspace workspace = new Workspace(request.getParameter("workspaceID"), request.getParameter("workspaceName"),member.getMemberID());

            PrintWriter printWriter = response.getWriter();

            if(new WorkspaceDAO().enroll(workspace) == true)
                printWriter.print("<script>alert('성공적으로 등록되었습니다')</script>");
            else
                printWriter.print("<script>alert('등록에 실패했습니다.')</script>");
                
            printWriter.print("<script>self.close()</script>");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}