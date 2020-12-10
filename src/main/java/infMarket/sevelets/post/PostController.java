package infMarket.sevelets.post;

import infMarket.models.mybatis.dto.Post;
import infMarket.sevelets.Action;
import infMarket.sevelets.member.MemberActions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/post/*"})
public class PostController extends HttpServlet {
    private PostActions postActions = new PostActions();
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String servletPath = request.getServletPath();
        String path = uri.substring(servletPath.length()).split("/")[1];
        Action action = postActions.getAction(path);
        action.execute(request,response);
    }
}
