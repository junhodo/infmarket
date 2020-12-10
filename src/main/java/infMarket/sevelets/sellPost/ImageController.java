package infMarket.sevelets.sellPost;

import infMarket.sevelets.Action;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/image/*"})
public class ImageController extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String servletPath = request.getServletPath();
        String imageFileName = uri.substring(servletPath.length()).split("/")[1];
        String tomcatPath = System.getProperty("user.dir") + "\\image";
        String imageFilePath = tomcatPath + "\\" + imageFileName;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(imageFilePath));
        response.setContentType("image/gif");
        byte[] by = new byte[1024]; //한번에 읽어올 파일크기 1024 바이트
        ServletOutputStream out = response.getOutputStream();
        while(in.available() > 0) {
            in.read(by);
            out.write(by); //1024바이트씩 출력
        }
        in.close();
        out.close();
    }
}
