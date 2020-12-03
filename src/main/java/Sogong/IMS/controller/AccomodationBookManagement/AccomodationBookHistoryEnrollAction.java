package Sogong.IMS.controller.AccomodationBookManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.AccomodationBookHistoryDAO;
import Sogong.IMS.dao.MemberAuthorityGroupDAO;
import Sogong.IMS.dao.MemberDAO;
import Sogong.IMS.model.AccomodationBookHistory;

public class AccomodationBookHistoryEnrollAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");

            PrintWriter out = response.getWriter();

            LocalDateTime[] createDate = null;
            String dateRange =  StringUtils.defaultIfBlank(request.getParameter("inputCheckDateTimeRange"), null);

            if(dateRange != null){
                createDate = new LocalDateTime[2];
              
                createDate[0] = LocalDateTime.parse(dateRange.split("~")[0]);   // 시작일
                createDate[1] = LocalDateTime.parse(dateRange.split("~")[0]);   // 종료일
            }
           
            String accomodationBookHistoryID = request.getParameter("inputAccomodationBookHistoryID");
            int numOfPeople = Integer.parseInt(request.getParameter("inputNumOfPeople"));
            String name = request.getParameter("inputName");
            String phoneNum  = request.getParameter("inputPhoneNum");
            LocalDate bookDate  = LocalDate.parse(request.getParameter("inputBookDate"));
            String bookState  = request.getParameter("inputBookState");
            int paymentPrice = Integer.parseInt(request.getParameter("inputPaymentPrice"));
            LocalDateTime checkInTime = createDate[0];
            LocalDateTime checkOutTime = createDate[1];
            String enteringState = request.getParameter("inputEnteringState");
            String memberID = request.getParameter("inputMemberID");
            String registrantID = request.getParameter("inputRegistrantID");
            String accomodationID = request.getParameter("inputAccomodationID");
            int roomNum = Integer.parseInt(request.getParameter("inputRoomNum"));
            

            Boolean isOk = null;
            HashMap<String, Object> condition = new HashMap<>();
           
           condition.put("memberID",memberID);
           MemberDAO memDAO = MemberDAO.getInstance();
           if (memDAO.lookup(condition).length <= 0) {
            out.println("<script>alert('존재하지 않는 회원ID입니다.')</script>");
            out.println("<script>self.close()</script>");
            isOk = false;
           } else {
               condition.clear();
               isOk = true;
           }
           
           if(isOk){
            AccomodationBookHistoryDAO accBookDAO = new AccomodationBookHistoryDAO();
            condition.put("accomodationBookHistoryID", accomodationBookHistoryID);
 
            if (accBookDAO.lookup(condition).length <= 0) {
                isOk = accBookDAO
                        .enroll(AccomodationBookHistory.builder().accomodationBookHistoryID(accomodationBookHistoryID)
                                .numOfPeople(numOfPeople).name(name).phoneNum(phoneNum).bookDate(bookDate)
                                .bookState(bookState).paymentPrice(paymentPrice).checkInTime(checkInTime)
                                .checkOutTime(checkOutTime).enteringState(enteringState).memberID(memberID)
                                .registrantID(registrantID).accomodationID(accomodationID).roomNum(roomNum).build());
 
                if (isOk) {
                    out.println("<script>alert('성공적으로 등록되었습니다.')</script>");
                    out.println("<script>self.close()</script>");
                } else {
                    out.println("<script>alert('등록이 실패되었습니다. 등록 정보를 다시 한번 확인하세요')</script>");
                    out.println("<script>self.close()</script>");
                }
 
            } else {
                out.println("<script>alert('이미 존재하는 예약번호입니다.')</script>");
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