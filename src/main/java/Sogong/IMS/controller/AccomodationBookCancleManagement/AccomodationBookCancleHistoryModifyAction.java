package Sogong.IMS.controller.AccomodationBookCancleManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.AccomodationBookCancleHistoryDAO;
import Sogong.IMS.dao.MemberDAO;
import Sogong.IMS.model.AccomodationBookCancleHistory;

public class AccomodationBookCancleHistoryModifyAction implements Action{
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");

            PrintWriter out = response.getWriter();
    
            String accomodationBookCancleHistoryID = request.getParameter("inputAccomodationBookCancleHistoryID");
            LocalDate cancleDate  = LocalDate.parse(request.getParameter("inputCancleDate")); //yyyy-mm-dd
            String cancleReason  = request.getParameter("inputCancleReason");
            String registrantID = request.getParameter("inputRegistrantID");
            String accomodationBookHistoryID = request.getParameter("inputAccomodationBookHistoryID");
           
            Boolean isOk = null;
            HashMap<String, Object> condition = new HashMap<>();

            condition.put("registrantID", registrantID);
            MemberDAO memDAO = MemberDAO.getInstance();
            if (memDAO.lookup(condition).length <= 0) {
                out.println("<script>alert('존재하지 않는 등록자ID입니다.')</script>");
                out.println("<script>self.close()</script>");
                isOk = false;
            } else {
                condition.clear();
                isOk = true;
            }
            
            if(isOk){
                AccomodationBookCancleHistoryDAO accBkCancle = new AccomodationBookCancleHistoryDAO();
                isOk = accBkCancle.modify(AccomodationBookCancleHistory.builder()
                        .accomodationBookCancleHistoryID(accomodationBookCancleHistoryID).cancleDate(cancleDate)
                        .cancleReason(cancleReason).registrantID(registrantID)
                        .accomodationBookHistoryID(accomodationBookHistoryID).build());
                    if (isOk) {
                        out.println("<script>alert('성공적으로 수정되었습니다.')</script>");
                        out.println("<script>self.close()</script>");
                    } else {
                        out.println("<script>alert('수정이 실패되었습니다. 수정 정보를 다시 한번 확인하세요')</script>");
                        out.println("<script>self.close()</script>");
                    }
            }
            out.flush();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}