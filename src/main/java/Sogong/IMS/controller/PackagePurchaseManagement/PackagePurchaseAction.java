package Sogong.IMS.controller.PackagePurchaseManagement;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.PackagePurchaseHistoryByMemberDAO;
import Sogong.IMS.dao.PackagePurchaseHistoryByNonMemberDAO;
import Sogong.IMS.model.Member;
import Sogong.IMS.model.Package;
import Sogong.IMS.model.PackagePurchaseHistoryByMember;
import Sogong.IMS.model.PackagePurchaseHistoryByNonMember;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PackagePurchaseAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter printWriter = response.getWriter();

            Package aPakcage = new Package( request.getParameter("packageID"),"test", request.getParameter("packageName"),request.getParameter("company"),request.getParameter("type")
                    ,request.getParameter("explanation"),Integer.parseInt(request.getParameter("price")));
            int totalPrice = aPakcage.getPrice() * Integer.parseInt(request.getParameter("numPurchase"));
            boolean result;
            if(request.getSession().getAttribute("member") != null){
                Member member = (Member) request.getSession().getAttribute("member");
                if(member == null)
                    printWriter.print("<script>alert('비정상적인 접근입니다')</script>");
                else{
                    result = PackagePurchaseHistoryByMemberDAO.getInstance().enroll(new PackagePurchaseHistoryByMember(aPakcage, LocalDateTime.now(), totalPrice,Integer.parseInt(request.getParameter("numPurchase")),
                            "구매완료",request.getParameter("paymentMethod"),member));
                    if(result == true)
                        printWriter.print("<script>alert('성공적으로 구매하였습니다')</script>");
                    else
                        printWriter.print("<script>alert('구매에 실패했습니다')</script>");
                }
            }
            else{
                if(request.getParameter("buyerName") != null){
                    result = PackagePurchaseHistoryByNonMemberDAO.getInstance().enroll(new PackagePurchaseHistoryByNonMember(aPakcage,LocalDateTime.now(),totalPrice,Integer.parseInt(request.getParameter("numPurchase")),
                            "구매완료",request.getParameter("paymentMethod"), request.getParameter("buyerName"), request.getParameter("buyerEmail"), request.getParameter("buyerPhoneNumber")));
                    if(result == true)
                        printWriter.print("<script>alert('성공적으로 구매하였습니다')</script>");
                    else
                        printWriter.print("<script>alert('구매에 실패했습니다')</script>");
                }
                else
                    printWriter.print("<script>alert('비정상적인 접근입니다')</script>");

            }
            printWriter.print("<script>self.close()</script>");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
