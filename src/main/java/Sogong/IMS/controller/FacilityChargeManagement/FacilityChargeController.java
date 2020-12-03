package Sogong.IMS.controller.FacilityChargeManagement;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.MemberAuthorityGroupDAO;
import Sogong.IMS.model.Member;
import lombok.SneakyThrows;

@WebServlet("/facilityChargeManage/*")
public class FacilityChargeController extends HttpServlet {

    private static final long serialVersionUID = 8559171819500212874L;

    HashMap<String, Action> list = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        list = new HashMap<>();

        list.put("enroll.do", new FacilityChargeEnrollAction());
        list.put("lookup.do", new FacilityChargeLookupAction());
        list.put("modify.do", new FacilityChargeModifyAction());
        list.put("delete.do", new FacilityChargeDeleteAction());
    }

    @SneakyThrows
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String url = request.getRequestURI();
        String servletPath = request.getServletPath();
        String path = url.substring(servletPath.length()).split("/")[1];

        PrintWriter out = response.getWriter();

        HashMap<String, String> authorityList = new HashMap<>();
        authorityList.put("enroll.do", "시설요금_등록");
        authorityList.put("modify.do", "시설요금_수정");
        authorityList.put("delete.do", "시설요금_삭제");
        authorityList.put("lookup.do", "시설요금_조회");

        if (request.getSession().getAttribute("member") != null
                && hasAuthority((Member) request.getSession().getAttribute("member"), authorityList.get(path))) {
            Action action = list.get(path);
            action.excute(request, response);
        } else {
            out.print(String.format("<script>alert('권한이 없습니다'); location.replace('%s');</script>",
                    request.getServletPath()));
        }
    }

    public boolean hasAuthority(Member member, String authorityName) {
        return new MemberAuthorityGroupDAO().hasAuthority(member, authorityName);
    }
}