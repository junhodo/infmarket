package Sogong.IMS.controller.AuthorityManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.model.AuthorityGroup;
import org.apache.commons.lang3.StringUtils;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.MemberAuthorityGroupDAO;
import Sogong.IMS.model.Member;

public class AuthorityLookupAction implements Action {

    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub

        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        AuthorityGroup authorityGroup = null;
        String authorityName = StringUtils.defaultIfBlank(request.getParameter("authorityName"), null);

        String[] conditionNames = {"memberID", "memberType" , "department"};
        HashMap<String,Object> conditions = new HashMap<>();

        for(String condition : conditionNames){
            String value = StringUtils.defaultIfBlank(request.getParameter(condition), null);

            if(value != null)
                conditions.put(condition, value);
        }

        if(authorityName != null){
            authorityGroup = AuthorityGroup.builder().authorityGroupName(authorityName).build();
            conditions.put(authorityName, authorityGroup);
        }

        ArrayList<Member> members = new ArrayList<>(Arrays.asList(new MemberAuthorityGroupDAO().lookup(conditions)));

        request.setAttribute("members", members);

        ServletContext context = request.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/authorityManage"); // 넘길 페이지 주소
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}