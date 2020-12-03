package Sogong.IMS.controller.FacilityManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.dao.FacilityDAO;
import Sogong.IMS.controller.Action;
import Sogong.IMS.model.Facility;
import Sogong.IMS.model.Member;

public class FacilityEnrollAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

            Facility facility = new Facility(request.getParameter("facilityID"), ((Member)request.getSession().getAttribute("member")).getMemberID(),request.getParameter("workspaceID")
                    ,request.getParameter("facilityName"));
            PrintWriter printWriter = response.getWriter();
            if(FacilityDAO.getInstance().enroll(facility) == true)
                printWriter.print("<script>alert('성공적으로 등록되었습니다')</script>");
            else
                printWriter.print("<script>alert('등록에 실패했습니다.')</script>");
            printWriter.print("<script>self.close()</script>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}