package Sogong.IMS.controller.FacilityManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.dao.DeviceDAO;
import Sogong.IMS.dao.FacilityDAO;
import Sogong.IMS.dao.FacilityPropertyDAO;
import Sogong.IMS.controller.Action;
import Sogong.IMS.model.Device;
import Sogong.IMS.model.Facility;
import Sogong.IMS.model.FacilityProperty;

public class FacilityModifyAction implements Action {
@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
    try {
        System.out.println("mo");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Facility facility = new Facility(request.getParameter("facilityID"), request.getParameter("registrantID"),request.getParameter("workspaceID")
                ,request.getParameter("facilityName"));
        PrintWriter printWriter = response.getWriter();
        if(FacilityDAO.getInstance().modify(facility) == true)
            printWriter.print("<script>alert('성공적으로 수정되었습니다')</script>");
        else
            printWriter.print("<script>alert('수정에 실패했습니다.')</script>");
        printWriter.print("<script>self.close()</script>");
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }
}