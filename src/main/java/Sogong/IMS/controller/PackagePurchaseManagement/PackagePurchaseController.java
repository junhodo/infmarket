package Sogong.IMS.controller.PackagePurchaseManagement;

import Sogong.IMS.controller.Action;
import lombok.SneakyThrows;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/packagePurchase/*")
public class PackagePurchaseController extends HttpServlet {
    HashMap<String, Action> actionList =null;
    @SneakyThrows
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getRequestURI();
        String servletPath = req.getServletPath();
        String path = url.substring(servletPath.length()).split("/")[1];
        Action action = actionList.get(path);
        action.excute(req, resp);


    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        actionList = new HashMap<>();
        actionList.put("purchase.do",new PackagePurchaseAction());
        actionList.put("lookup.do",new PackagePurchaseLookupAction());
    }
}
