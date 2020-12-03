package Sogong.IMS.controller.FacilityPropertyManagement;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.dao.FacilityPropertyDAO;
import Sogong.IMS.controller.Action;

public class FacilityPropertyDeleteAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            ServletContext context = request.getServletContext();
            String url = request.getRequestURI();
            String servletPath = request.getServletPath();
            String facilityPropertyID = url.substring(servletPath.length()).split("/")[2];
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            RequestDispatcher dispatcher = context.getRequestDispatcher("/facilityPropertyManage"); // 넘길 페이지 주소
            dispatcher.include(request, response);
            PrintWriter printWriter = response.getWriter();
            if(FacilityPropertyDAO.getInstance().delete(facilityPropertyID) == true)
                printWriter.print("<script>alert('성공적으로 삭제되었습니다')</script>");
            else
                printWriter.print("<script>alert('삭제에 실패했습니다.')</script>");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}