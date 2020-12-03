package Sogong.IMS.controller.FacilityChargeManagement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.FacilityChargeDAO;
import Sogong.IMS.model.FacilityCharge;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FacilityChargeLookupAction implements Action{
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");

            String[] conditionNames = {"workspaceID","facilityID","chargeName","registrantID"};
            HashMap<String,Object> conditions = new HashMap<>();

            for(String condition : conditionNames){
                String value = StringUtils.defaultIfBlank(request.getParameter(condition), null);

                if(value != null)
                    conditions.put(condition, value);
            }

            ArrayList<FacilityCharge> facilityCharges = new ArrayList<>(Arrays.asList( new FacilityChargeDAO().lookup(conditions)));

            request.setAttribute("facilityCharges", facilityCharges);

            ServletContext context = request.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/facilityChargeManage");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}