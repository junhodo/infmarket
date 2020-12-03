package Sogong.IMS.controller.PackageManagement;

import Sogong.IMS.controller.Action;
import Sogong.IMS.dao.MemberAuthorityGroupDAO;
import Sogong.IMS.model.Member;
import lombok.SneakyThrows;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet({"/packageManage/*"})
public class PackageController extends HttpServlet {
    private HashMap<String, Action> list = null;
    private HashMap<String, String> authorityList = null;
    @SneakyThrows
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PrintWriter printWriter = response.getWriter();
        String url = request.getRequestURI();
        String servletPath = request.getServletPath();

        String path = url.substring(servletPath.length()).split("/")[1];

        if(request.getSession().getAttribute("member") != null && hasAuthority((Member) request.getSession().getAttribute("member"), authorityList.get(path))){
            Action action = list.get(path);
            action.excute(request, response);
        }
        else
            printWriter.print(String.format("<script>alert('권한이 없습니다'); location.replace('%s')</script>",request.getServletPath()));
    }
    @Override
    public void init(ServletConfig sc) throws ServletException {
        list = new HashMap<>();
        list.put("enroll.do", new PackageEnrollAction());
        list.put("modify.do", new PackageModifyAction());
        list.put("delete.do", new PackageDeleteAction());
        list.put("lookup.do", new PackageLookupAction());
        authorityList = new HashMap<>();
        authorityList.put("enroll.do", "상품_등록");
        authorityList.put("modify.do", "상품_수정");
        authorityList.put("delete.do", "상품_삭제");
        authorityList.put("lookup.do", "상품_조회");
    }

    public boolean hasAuthority(Member member, String authorityName){
        MemberAuthorityGroupDAO memberAuthorityGroupDAO = new MemberAuthorityGroupDAO();
        return memberAuthorityGroupDAO.hasAuthority(member, authorityName);
    }

}
