package Sogong.IMS.controller.PaymentHistoryManagement;

import java.io.IOException;
import java.io.PrintWriter;
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
import Sogong.IMS.dao.PaymentHistoryDAO;
import Sogong.IMS.model.AuthorityGroup;
import Sogong.IMS.model.PaymentHistory;

public class PaymentHistoryLookupAction implements Action {

    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        String[] conditionNames = {"paymentMethod", "customerName" , "registrantID", "startPaymentPrice","endPaymentPrice","paymentTime","checkOut","checkIn"};
        HashMap<String,Object> conditions = new HashMap<>();

        for(String condition : conditionNames){
            String value = StringUtils.defaultIfBlank(request.getParameter(condition), null);

            if(value != null)
                conditions.put(condition, value);
        }

        ArrayList<PaymentHistory> paymentHistories = new ArrayList<>(Arrays.asList(new PaymentHistoryDAO().lookup(conditions)));
    
        request.setAttribute("paymentHistories", paymentHistories);

        ServletContext context = request.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/paymentManage"); // 넘길 페이지 주소
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}