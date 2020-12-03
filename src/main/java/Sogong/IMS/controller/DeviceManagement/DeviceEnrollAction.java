package Sogong.IMS.controller.DeviceManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.dao.DeviceDAO;
import Sogong.IMS.controller.Action;
import Sogong.IMS.model.Device;
import Sogong.IMS.model.Member;

public class DeviceEnrollAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            Device device = new Device(request.getParameter("deviceID"), request.getParameter("facilityPropertyID"),((Member)request.getSession().getAttribute("member")).getMemberID()
                    ,request.getParameter("deviceName"),request.getParameter("instruction"),Integer.parseInt(request.getParameter("instructionCost")));
            PrintWriter printWriter = response.getWriter();
            if(DeviceDAO.getInstance().enroll(device) == true)
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