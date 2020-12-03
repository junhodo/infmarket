package Sogong.IMS.controller.AccomodationBookCancleManagement;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
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
import Sogong.IMS.dao.AccomodationBookCancleHistoryDAO;
import Sogong.IMS.model.AccomodationBookCancleHistory;

public class AccomodationBookCancleHistoryLookupAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

       

        //1. 값이 없다면 null로 지정됩니다.
        String accomodationBookCancleHistoryID = StringUtils.defaultIfBlank(request.getParameter("inputAccomodationBookCancleHistoryID"), null);
        String accomodationBookHistoryID = StringUtils.defaultIfBlank(request.getParameter("inputAccomodationBookHistoryID"), null);
        String register = StringUtils.defaultIfBlank(request.getParameter("inputRegister"), null);
   

       

        // LocalDate[] createDate = null;

        // if(dateRange != null){
        //     createDate = new LocalDate[2];

        //     createDate[0] = LocalDate.parse(dateRange.split("~")[0]);   // 시작일
        //     createDate[1] = LocalDate.parse(dateRange.split("~")[1]);   // 종료일
        // }

        // 2. 조건 검색을 위한 HashMap으로 String은 table의 column명, Object는 그 자료형이 됩니다.
        HashMap<String,Object> condition = new HashMap<>();
        
        // 3. 값이 있는 항목에 대해서만 조건 검색에 추가합니다.
        if(accomodationBookCancleHistoryID != null) condition.put("accomodationBookCancleHistoryID", accomodationBookCancleHistoryID);

        if(accomodationBookHistoryID != null) condition.put("accomodationBookHistoryID", accomodationBookHistoryID);

        if(register != null) condition.put("register", register);

        

        // 4. 결과를 가져옵니다. 출력의 편의성을 위해 Example[]에서 ArrayList로 변환했습니다.
        ArrayList<AccomodationBookCancleHistory> accomodationBookCancleHistories = new ArrayList<>(Arrays.asList(new AccomodationBookCancleHistoryDAO().lookup(condition)));

        // 5. 보여줄 페이지에 해당 결과를 전달합니다.
        request.setAttribute("accomodationBookCancleHistories", accomodationBookCancleHistories);

        // 6. 보여줄 페이지를 지정합니다.
        ServletContext context = request.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/accomodationBookCancleHistoryManage"); // 넘길 페이지 주소
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}