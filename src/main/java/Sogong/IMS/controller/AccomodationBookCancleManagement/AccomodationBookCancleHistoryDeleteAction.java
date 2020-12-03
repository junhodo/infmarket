package Sogong.IMS.controller.AccomodationBookCancleManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.AccomodationBookCancleHistoryDAO;
import Sogong.IMS.model.AccomodationBookCancleHistory;

public class AccomodationBookCancleHistoryDeleteAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");

            PrintWriter out = response.getWriter();

            String accomodationBookCancleHistoryID = request.getParameter("inputAccomodationBookCancleHistoryID");
         
            
            new AccomodationBookCancleHistoryDAO().delete(
                AccomodationBookCancleHistory.builder()
                    .accomodationBookCancleHistoryID(accomodationBookCancleHistoryID)
                            .build());

            out.println("<script>alert('성공적으로 삭제되었습니다.')</script>");
            out.print(String.format("<script>location.replace('%s')</script>",request.getServletPath()));
           
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}