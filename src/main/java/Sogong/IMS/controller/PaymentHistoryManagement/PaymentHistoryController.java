package Sogong.IMS.controller.PaymentHistoryManagement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;


import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.controller.Action;
import Sogong.IMS.model.Member;

public class PaymentHistoryController {

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

        list.put("/enroll.do", new PaymentHistoryEnrollAction());
        list.put("/lookup.do", new PaymentHistoryLookupAction());
        list.put("/modify.do", new PaymentHistoryModifyAction());
        list.put("/delete.do", new PaymentHistoryDeleteAction());
    }
}