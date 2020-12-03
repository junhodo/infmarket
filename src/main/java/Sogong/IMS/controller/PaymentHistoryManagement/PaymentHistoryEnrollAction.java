package Sogong.IMS.controller.PaymentHistoryManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.util.StringUtils;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.PaymentHistoryDAO;
import Sogong.IMS.model.PaymentHistory;

public class PaymentHistoryEnrollAction implements Action {

    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub

        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");

            PrintWriter out = response.getWriter();
           
            String paymentID = request.getParameter("inputAccomodationID");
            int price = Integer.parseInt(request.getParameter("inputPrice"));
            LocalDateTime paymentTime  = LocalDateTime.parse(request.getParameter("inputPaymentTime"));
            String paymentMethod  = request.getParameter("inputPaymentMethod");

            new PaymentHistoryDAO().enroll(
                    PaymentHistory.builder()
                    .paymentHistoryID(paymentID)
                    .price(price)
                    .paymentMethod(paymentMethod)
                    .paymentTime(paymentTime)
                            .build());


            out.println("<script>alert('성공적으로 등록되었습니다.')</script>");
            out.println("<script>self.close()</script>");
            out.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}