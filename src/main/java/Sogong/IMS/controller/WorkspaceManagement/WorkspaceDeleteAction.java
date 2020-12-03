package Sogong.IMS.controller.WorkspaceManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.WorkspaceDAO;
import Sogong.IMS.model.Workspace;

public class WorkspaceDeleteAction implements Action {

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

            String workspaceID = StringUtils.defaultIfBlank(request.getParameter("workspaceID"), null);

            if (workspaceID != null) {
                
                Workspace workspace = new Workspace(workspaceID, null, null);
                Boolean isOK = new WorkspaceDAO().delete(workspace);

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