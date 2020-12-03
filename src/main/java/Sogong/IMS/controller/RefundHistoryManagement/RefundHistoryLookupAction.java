package Sogong.IMS.controller.RefundHistoryManagement;

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
import Sogong.IMS.dao.RefundHistoryDAO;
import Sogong.IMS.model.RefundHistory;

public class RefundHistoryLookupAction implements Action {

    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

       

        //1. 값이 없다면 null로 지정됩니다.
        String paymentMethod = StringUtils.defaultIfBlank(request.getParameter("inputPaymentMethod"), null);
        String refundMethod = StringUtils.defaultIfBlank(request.getParameter("inputRefundMethod"), null);
        String customerName =  StringUtils.defaultIfBlank(request.getParameter("inputCustomerName"), null);
        String registrantID =  StringUtils.defaultIfBlank(request.getParameter("inputRegistrantID"), null);
        String paymentPrice =  StringUtils.defaultIfBlank(request.getParameter("inputStartPaymentPrice"), null);
        String refundPrice =  StringUtils.defaultIfBlank(request.getParameter("inputStartRefundPrice"), null);
        String paymentTime =  StringUtils.defaultIfBlank(request.getParameter("inputPaymentTime"), null);
        String refundTime =  StringUtils.defaultIfBlank(request.getParameter("inputPaymentTime"), null);
        String checkOut =  StringUtils.defaultIfBlank(request.getParameter("inputCheckOut"), null);
        String checkIn =  StringUtils.defaultIfBlank(request.getParameter("inputCheckIn"), null);

        // 2. 조건 검색을 위한 HashMap으로 String은 table의 column명, Object는 그 자료형이 됩니다.
        HashMap<String,Object> condition = new HashMap<>();
        
        // 3. 값이 있는 항목에 대해서만 조건 검색에 추가합니다.
        if(paymentMethod != null) condition.put("paymentMethod", paymentMethod);

        if(refundMethod != null) condition.put("paymentMethod", refundMethod);

        if(customerName != null) condition.put("customerName", customerName);

        if(registrantID != null) condition.put("registrantID", registrantID);

        if(paymentPrice != null) condition.put("paymentPrice", paymentPrice);

        if(refundPrice != null) condition.put("refundPrice", refundPrice);

        if(paymentTime != null) condition.put("paymentTime", paymentTime);

        if(refundTime != null) condition.put("refundTime", refundTime);

        if(checkOut != null) condition.put("checkOut", checkOut);

        if(checkIn != null) condition.put("checkIn", checkIn);
        // 4. 결과를 가져옵니다. 출력의 편의성을 위해 Example[]에서 ArrayList로 변환했습니다.
        ArrayList<RefundHistory> refundHistories = new ArrayList<>(Arrays.asList(new RefundHistoryDAO().lookup(condition)));
    
        // 5. 보여줄 페이지에 해당 결과를 전달합니다.
        request.setAttribute("refundHistories", refundHistories);

        // 6. 보여줄 페이지를 지정합니다.
        ServletContext context = request.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/refundManage"); // 넘길 페이지 주소
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}