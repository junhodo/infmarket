package infMarket.sevelets.member;

import infMarket.sevelets.Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/member/*"})
public class MemberController extends HttpServlet {
    private MemberActions memberActions = new MemberActions();
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String servletPath = request.getServletPath();
        String path = uri.substring(servletPath.length()).split("/")[1];
        Action action = memberActions.getAction(path);
        action.execute(request,response);
    }
}
