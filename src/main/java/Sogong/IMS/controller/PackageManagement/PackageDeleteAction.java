package Sogong.IMS.controller.PackageManagement;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.PackageDAO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class PackageDeleteAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            ServletContext context = request.getServletContext();
            String url = request.getRequestURI();
            String servletPath = request.getServletPath();
            String packageID = url.substring(servletPath.length()).split("/")[2];
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            if(PackageDAO.getInstance().delete(Integer.parseInt(packageID)) == true)
                printWriter.print("<script>alert('성공적으로 삭제되었습니다')</script>");
            else
                printWriter.print("<script>alert('삭제에 실패했습니다.')</script>");

            printWriter.print(String.format("<script>location.replace('%s')</script>",request.getServletPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
