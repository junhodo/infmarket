package Sogong.IMS.controller.WorkspaceManagement;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
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

public class WorkspaceLookupAction implements Action {

    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        
        String[] conditionNames = {"workspaceName"};
        HashMap<String,Object> conditions = new HashMap<>();

        for(String condition : conditionNames){
            String value = StringUtils.defaultIfBlank(request.getParameter(condition), null);

            if(value != null)
                conditions.put(condition, value);
        }
        ArrayList<Workspace> workspaces = new ArrayList<>(Arrays.asList(new WorkspaceDAO().lookup(conditions)));

        request.setAttribute("workspaces", workspaces);

        ServletContext context = request.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/workspaceManage"); // 넘길 페이지 주소
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }  
}