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
import Sogong.IMS.model.Facility;
import Sogong.IMS.controller.Action;
import Sogong.IMS.model.Device;
import Sogong.IMS.model.FacilityProperty;

public class FacilityPropertyModifyAction implements Action {
@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            LocalDate date = LocalDate.parse(request.getParameter("openingDate"));

            FacilityProperty facilityProperty = new FacilityProperty(request.getParameter("facilityPropertyID"), request.getParameter("facilityID"), request.getParameter("registrantID"), date
                    , Integer.parseInt(request.getParameter("facilityScale")), Integer.parseInt(request.getParameter("facilityCost")));
            PrintWriter printWriter = response.getWriter();
            if (FacilityPropertyDAO.getInstance().modify(facilityProperty) == true)
                printWriter.print("<script>alert('성공적으로 수정되었습니다')</script>");
            else
                printWriter.print("<script>alert('수정에 실패했습니다.')</script>");
            printWriter.print("<script>self.close()</script>");
        } catch (Exception e) {
            e.printStackTrace();
        }
}
}