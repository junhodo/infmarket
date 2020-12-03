package Sogong.IMS.controller.PackageManagement;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.PackageDAO;
import Sogong.IMS.model.Package;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

//TODO 등록자 id 세션으로 부터 받아오기
public class PackageEnrollAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            Package aPakcage = new Package(null,"test", request.getParameter("packageName"),request.getParameter("company"),request.getParameter("type")
                    ,request.getParameter("explanation"),Integer.parseInt(request.getParameter("price")));
            PrintWriter printWriter = response.getWriter();
            if(PackageDAO.getInstance().enroll(aPakcage) == true)
                printWriter.print("<script>alert('성공적으로 등록되었습니다')</script>");
            else
                printWriter.print("<script>alert('등록에 실패했습니다.')</script>");
            printWriter.print("<script>self.close()</script>");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
