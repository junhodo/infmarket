package Sogong.IMS.controller.FacilityPropertyManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.dao.FacilityPropertyDAO;
import Sogong.IMS.controller.Action;
import Sogong.IMS.model.FacilityProperty;
import Sogong.IMS.model.Member;

public class FacilityPropertyEnrollAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            LocalDate date = LocalDate.parse(request.getParameter("openingDate"));
            FacilityProperty facilityProperty = new FacilityProperty(request.getParameter("facilityPropertyID"), request.getParameter("facilityID"), ((Member)request.getSession().getAttribute("member")).getMemberID(), date
                    , Integer.parseInt(request.getParameter("facilityScale")), Integer.parseInt(request.getParameter("facilityCost")));
            PrintWriter printWriter = response.getWriter();
            if(FacilityPropertyDAO.getInstance().enroll(facilityProperty) == true)
                printWriter.print("<script>alert('성공적으로 등록되었습니다')</script>");
            else
                printWriter.print("<script>alert('등록에 실패했습니다.')</script>");
            printWriter.print("<script>self.close()</script>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}