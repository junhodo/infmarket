package infMarket.sevelets.sellPost;

import com.oreilly.servlet.MultipartRequest;
import infMarket.models.mybatis.dao.Post.PostMapperExecutor;
import infMarket.models.mybatis.dao.SellPost.SellPostMapper;
import infMarket.models.mybatis.dao.SellPost.SellPostMapperExecutor;
import infMarket.models.mybatis.dto.Member;
import infMarket.models.mybatis.dto.Post;
import infMarket.models.mybatis.dto.SellPost;
import infMarket.sevelets.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class SellPostUpdateAction implements Action {
    private static String MEMBER_SESSION_ID = "auth";
    private static final int MAX_FILE_SIZE = 1000*1024;
    private static final String FILE_ENCODING = "utf-8";
    private static final String IMAGE_GET_PATH = "/api/image/";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try{
            Member member = (Member) request.getSession().getAttribute(MEMBER_SESSION_ID);
            if(member == null)
                throw new Exception();
            String tomcatPath = System.getProperty("user.dir") + "\\image";
            MultipartRequest multipartRequest = new MultipartRequest(request, tomcatPath, MAX_FILE_SIZE, FILE_ENCODING, new MyFileRenamePolicy());
            String title = multipartRequest.getParameter("title");
            String text = multipartRequest.getParameter("text");
            String price = multipartRequest.getParameter("price");
            String sellPostId = multipartRequest.getParameter("sellPostId");
            File imageFile = multipartRequest.getFile("imageFile");

            SellPost sellPost = new SellPost();
            if(imageFile != null) {
                sellPost.setImage_url(IMAGE_GET_PATH + imageFile.getName());
            }
            sellPost.setItem_description(text);
            sellPost.setItem_name(title);
            sellPost.setMember_id(member.getMember_id());
            sellPost.setMember_name(member.getName());
            sellPost.setPrice(Integer.parseInt(price));
            sellPost.setSell_post_id(Integer.parseInt(sellPostId));

            SellPostMapperExecutor sellPostMapperExecutor = new SellPostMapperExecutor();
            sellPostMapperExecutor.updateSellPost(sellPost);

        }catch (Exception e){
            response.setStatus(400);
            e.printStackTrace();
        }
    }
}
