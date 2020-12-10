package infMarket.sevelets.member;

import infMarket.models.mybatis.dto.Member;
import infMarket.sevelets.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        try{
            request.getSession().removeAttribute("auth");
            response.sendRedirect("/");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
