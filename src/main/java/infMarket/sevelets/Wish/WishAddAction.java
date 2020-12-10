package infMarket.sevelets.Wish;

import infMarket.models.mybatis.dao.Post.PostMapperExecutor;
import infMarket.models.mybatis.dao.SellPost.SellPostMapperExecutor;
import infMarket.models.mybatis.dao.Wish.WishMapperExecutor;
import infMarket.models.mybatis.dto.Member;
import infMarket.models.mybatis.dto.Post;
import infMarket.models.mybatis.dto.SellPost;
import infMarket.models.mybatis.dto.Wish;
import infMarket.sevelets.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class WishAddAction implements Action {
    private static String MEMBER_SESSION_ID = "auth";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try{
            System.out.println("dasdas");
            Member member = (Member) request.getSession().getAttribute("auth");
            if(member == null)
                throw new Exception("400");
            String sellPostId = request.getParameter("sellPostId");
            WishMapperExecutor wishMapperExecutor = new WishMapperExecutor();
            SellPostMapperExecutor sellPostMapperExecutor = new SellPostMapperExecutor();
            SellPost sellPost = sellPostMapperExecutor.selectSellPost(Integer.parseInt(sellPostId));
            List<Wish> myWistList = wishMapperExecutor.selectMemberWishList(member.getMember_id());
            for(Wish wish : myWistList){
                if(wish.getSell_post_id() == Integer.parseInt(sellPostId))
                    throw new Exception("401");
            }
            Wish wish = new Wish();
            wish.setMember_id(member.getMember_id());
            wish.setSell_post_id(sellPost.getSell_post_id());
            wish.setItem_name(sellPost.getItem_name());
            wishMapperExecutor.insertWish(wish);

        }catch (Exception e){
            response.setStatus(Integer.parseInt(e.getMessage()));
            e.printStackTrace();
        }
    }
}
