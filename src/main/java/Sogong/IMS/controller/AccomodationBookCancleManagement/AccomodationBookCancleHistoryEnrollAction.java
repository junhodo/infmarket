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
import Sogong.IMS.dao.AccomodationBookHistoryDAO;
import Sogong.IMS.dao.MemberDAO;
import Sogong.IMS.model.AccomodationBookCancleHistory;

public class AccomodationBookCancleHistoryEnrollAction implements Action {
    
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");

            PrintWriter out = response.getWriter();

            String accomodationBookCancleHistoryID = request.getParameter("inputAccomodationBookCancleHistoryID");
            LocalDate cancleDate = LocalDate.parse(request.getParameter("inputCancleDate")); // yyyy-mm-dd
            String cancleReason = request.getParameter("inputCancleReason");
            String registrantID = request.getParameter("inputRegistrantID");
            String accomodationBookHistoryID = request.getParameter("inputAccomodationBookHistoryID");

            Boolean isOk = null;
            HashMap<String, Object> condition = new HashMap<>();
            condition.put("memberID", registrantID);
            MemberDAO memDAO = MemberDAO.getInstance();

            if (memDAO.lookup(condition).length <= 0) {
                out.println("<script>alert('존재하지 않는 등록자ID입니다.')</script>");
                out.println("<script>self.close()</script>");
                isOk = false;
            } else {
                condition.clear();
                isOk = true;
            }
            AccomodationBookHistoryDAO accBk = new AccomodationBookHistoryDAO();
            condition.put("accomodationBookHistoryID", accomodationBookHistoryID);
            if (accBk.lookup(condition).length <= 0) {
                out.println("<script>alert('존재하지 않는 예약번호입니다.')</script>");
                out.println("<script>self.close()</script>");
                isOk = false;
            } else {
                condition.clear();
                isOk = true;
            }

            if (isOk) {
                AccomodationBookCancleHistoryDAO accBkCancleDAO = new AccomodationBookCancleHistoryDAO();
                condition.put("accomodationBookCancleHistoryID", accomodationBookCancleHistoryID);
                if (accBkCancleDAO.lookup(condition).length <= 0) {
                    isOk = accBkCancleDAO.enroll(AccomodationBookCancleHistory.builder()
                            .accomodationBookCancleHistoryID(accomodationBookCancleHistoryID).cancleDate(cancleDate)
                            .cancleReason(cancleReason).registrantID(registrantID)
                            .accomodationBookHistoryID(accomodationBookHistoryID).build());

                    if (isOk) {
                        out.println("<script>alert('성공적으로 등록되었습니다.')</script>");
                        out.println("<script>self.close()</script>");
                    } else {
                        out.println("<script>alert('등록이 실패되었습니다. 등록 정보를 다시 한번 확인하세요')</script>");
                        out.println("<script>self.close()</script>");
                    }
                } else {
                    out.println("<script>alert('이미 존재하는 예약취소번호입니다.')</script>");
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