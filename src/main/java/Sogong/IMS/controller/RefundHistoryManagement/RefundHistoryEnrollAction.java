package Sogong.IMS.controller.RefundHistoryManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.RefundHistoryDAO;
import Sogong.IMS.model.RefundHistory;

public class RefundHistoryEnrollAction implements Action {
   
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");

            PrintWriter out = response.getWriter();
           
            String paymentID = request.getParameter("inputPaymentID");
            int price = Integer.parseInt(request.getParameter("inputPrice"));
            LocalDateTime refundTime  = LocalDateTime.parse(request.getParameter("inputPaymentTime"));
            String refundMethod  = request.getParameter("inputRefundMethod");

            new RefundHistoryDAO().enroll(
                    RefundHistory.builder()
                    .paymentHistoryID(paymentID)
                    .refundPrice(price)
                    .refundMethod(refundMethod)
                    .refundTime(refundTime)
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