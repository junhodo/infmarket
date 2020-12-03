package Sogong.IMS.controller.RefundHistoryManagement;

import java.io.IOException;
import java.lang.reflect.Member;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.controller.Action;

public class RefundHistoryController {
    HashMap<String,Action> list;
    private static final long serialVersionUID = 8559171819500212874L;
    
    public void service(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, NamingException, IOException {
        String url = request.getRequestURI();
        String servletPath = request.getServletPath();

        String path = url.substring(servletPath.length());

        Action action = list.get(path);
        action.excute(request, response);
    }

    public void hasAuthority(Member member,String authorityName)
    {
       
    }

    public void init()
    {
        list = new HashMap<>();

        list.put("/enroll.do", new RefundHistoryEnrollAction());
        list.put("/lookup.do", new RefundHistoryLookupAction());
        list.put("/modify.do", new RefundHistoryModifyAction());
        list.put("/delete.do", new RefundHistoryDeleteAction());
    }
}