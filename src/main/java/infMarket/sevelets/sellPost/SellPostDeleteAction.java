package infMarket.sevelets.sellPost;

import infMarket.models.mybatis.dao.Post.PostMapperExecutor;
import infMarket.models.mybatis.dao.SellPost.SellPostMapper;
import infMarket.models.mybatis.dao.SellPost.SellPostMapperExecutor;
import infMarket.models.mybatis.dto.Member;
import infMarket.models.mybatis.dto.Post;
import infMarket.models.mybatis.dto.SellPost;
import infMarket.sevelets.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SellPostDeleteAction implements Action {
    private static String MEMBER_SESSION_ID = "auth";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try{
            int postId = Integer.parseInt(request.getParameter("postId"));
            Member member = (Member) request.getSession().getAttribute(MEMBER_SESSION_ID);
            SellPostMapperExecutor sellPostMapperExecutor = new SellPostMapperExecutor();

            SellPost sellPost = sellPostMapperExecutor.selectSellPost(postId);

            if(sellPost.getMember_id().equals(member.getMember_id()) || member.getAuthority().equals("ADMIN")){
                sellPostMapperExecutor.deleteSellPost(sellPost.getSell_post_id());
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            response.setStatus(400);
            e.printStackTrace();
        }
    }
}
