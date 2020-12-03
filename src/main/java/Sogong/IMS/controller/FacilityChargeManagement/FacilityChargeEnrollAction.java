package Sogong.IMS.controller.FacilityChargeManagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.FacilityChargeDAO;
import Sogong.IMS.model.Facility;
import Sogong.IMS.model.FacilityCharge;
import Sogong.IMS.model.Workspace;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class FacilityChargeEnrollAction implements Action{
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();

            FacilityChargeDAO fcDAO = new FacilityChargeDAO();

            String resistrantID = request.getParameter("resistrantID");
            String workspaceID = request.getParameter("workspaceID");
            String facilityID = request.getParameter("facilityID");
            String chargeName = request.getParameter("inputChargeName");
            int charge = Integer.parseInt(request.getParameter("inputCharge"));
            float discountRate = Float.parseFloat(request.getParameter("inputDiscountRate"));

            boolean isDiscount = false;

            int intIsDiscount = Integer.parseInt(request.getParameter("selectedDiscount"));
            switch (intIsDiscount) {
                case 1:
                    isDiscount = true;
                    break;
                case 0:
                    isDiscount = false;
                    break;
            }

            if (workspaceID != null && chargeName != null && facilityID != null) {

                HashMap<String, Object> condition = new HashMap<>();
                condition.put("workspaceID", workspaceID);
                condition.put("facilityID", facilityID);
                condition.put("chargeName", chargeName);

                // 등록 전 관광지명, 시설명, 요금명으로 이미 요금이 존재 하는지 조회
                FacilityCharge[] result = fcDAO.lookup(condition);

                if (result ==  null) {

                    FacilityCharge facilityCharge = new FacilityCharge();

                    facilityCharge.setWorkspaceID(workspaceID);
                    facilityCharge.setFacilityID(facilityID);
                    facilityCharge.setChargeName(chargeName);
                    facilityCharge.setCharge(charge);
                    facilityCharge.setDiscount(isDiscount);
                    facilityCharge.setDiscountRate(discountRate);
                    facilityCharge.setResistrantID(resistrantID);

                    boolean isOK = fcDAO.enroll(facilityCharge);
                    
                    if (isOK) {
                        out.println("<script>alert('성공적으로 등록되었습니다.')</script>");
                        out.println("<script>self.close()</script>");
                    } else {
                        out.println("<script>alert('존재하지 않는 요금입니다.')</script>");
                        out.println("<script>self.close()</script>");
                    }
                } else {
                    out.println("<script>alert('이미 등록된 요금입니다.')</script>");
                    out.println("<script>self.close()</script>");
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}