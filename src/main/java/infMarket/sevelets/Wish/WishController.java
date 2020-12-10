package infMarket.sevelets.Wish;

import infMarket.sevelets.Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/wish/*"})
public class WishController extends HttpServlet {
    private WishActions postActions = new WishActions();
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String servletPath = request.getServletPath();
        String path = uri.substring(servletPath.length()).split("/")[1];
        Action action = postActions.getAction(path);
        action.execute(request,response);
    }
}
