package Sogong.IMS.controller.FacilityChargeManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.dao.FacilityChargeDAO;
import Sogong.IMS.model.FacilityCharge;
import org.apache.commons.lang3.StringUtils;

import Sogong.IMS.controller.Action;

public class FacilityChargeDeleteAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");

            PrintWriter out  = response.getWriter();
            ServletContext context = request.getServletContext();

            RequestDispatcher dispatcher = context.getRequestDispatcher("/src/main/webapp/facilitychargeManage/facilitychargeManage.jsp");
            dispatcher.include(request, response);

            String workspaceID = StringUtils.defaultIfBlank(request.getParameter("inputWorkspaceID"), null);
            String facilityID = StringUtils.defaultIfBlank(request.getParameter("inputFacilityID"), null);
            String chargeName = StringUtils.defaultIfBlank(request.getParameter("inputChargeName"), null);

            if (workspaceID != null && chargeName != null && facilityID != null) {

                HashMap<String, Object> condition = new HashMap<>();
                condition.put("workspaceID", workspaceID);
                condition.put("facilityID", facilityID);
                condition.put("chargeName", chargeName);

                FacilityCharge facilityCharge = new FacilityChargeDAO().lookup(condition)[0];

                boolean isOK = false;

                isOK = new FacilityChargeDAO().delete(facilityCharge);

                if (isOK) {
                    out.println("<script>alert('삭제되었습니다.')</script>");
                } else {
                    out.println("<script>alert('실패했습니다.')</script>");
                }
                out.flush();
            }

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}