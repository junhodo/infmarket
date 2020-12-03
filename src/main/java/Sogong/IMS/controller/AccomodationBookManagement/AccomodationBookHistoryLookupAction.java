package Sogong.IMS.controller.AccomodationBookManagement;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import Sogong.IMS.dao.AccomodationBookHistoryDAO;
import Sogong.IMS.model.AccomodationBookHistory;

public class AccomodationBookHistoryLookupAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

       

        //1. 값이 없다면 null로 지정됩니다.
        String accomodationBookHistoryID = StringUtils.defaultIfBlank(request.getParameter("inputAccomodationBookHistoryID"), null);
        String name = StringUtils.defaultIfBlank(request.getParameter("inputName"), null);
        String registrantID =  StringUtils.defaultIfBlank(request.getParameter("inputRegistrantID"), null);

       

        // 2. 조건 검색을 위한 HashMap으로 String은 table의 column명, Object는 그 자료형이 됩니다.
        HashMap<String,Object> condition = new HashMap<>();
        
        // 3. 값이 있는 항목에 대해서만 조건 검색에 추가합니다.
        if(accomodationBookHistoryID != null) condition.put("accomodationBookHistoryID", accomodationBookHistoryID);

        if(name != null) condition.put("name", name);

        if(registrantID != null) condition.put("registrantID", registrantID);

        // 4. 결과를 가져옵니다. 출력의 편의성을 위해 Example[]에서 ArrayList로 변환했습니다.
        ArrayList<AccomodationBookHistory> accomodationBookHistories = new ArrayList<>(Arrays.asList(new AccomodationBookHistoryDAO().lookup(condition)));
    
        // 5. 보여줄 페이지에 해당 결과를 전달합니다.
        request.setAttribute("accomodationBookHistories", accomodationBookHistories);

        // 6. 보여줄 페이지를 지정합니다.
        ServletContext context = request.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/accomodationBookHistoryManage"); // 넘길 페이지 주소
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}