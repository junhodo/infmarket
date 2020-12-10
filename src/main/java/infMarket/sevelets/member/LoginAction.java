package infMarket.sevelets.member;

import infMarket.models.mybatis.dao.Member.MemberMapperExecutor;
import infMarket.models.mybatis.dto.Member;
import infMarket.sevelets.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginAction implements Action {
    private static String MEMBER_SESSION_ID = "auth";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html;charset=UTF-8");
            MemberMapperExecutor memberMapperExecutor = new MemberMapperExecutor();
            Member member = memberMapperExecutor.login(request.getParameter("id"),request.getParameter("pw"));
            if(member != null){
                request.getSession().setAttribute("auth", member);
                response.sendRedirect("/");
            }else{
                PrintWriter out = response.getWriter();
                out.println("<script>alert('아이디 또는 비밀번호를 확인해주세요'); location.href='/login';</script>");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
