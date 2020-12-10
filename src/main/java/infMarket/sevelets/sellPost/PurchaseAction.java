package infMarket.sevelets.sellPost;

import com.oreilly.servlet.MultipartRequest;
import infMarket.models.mybatis.dao.Member.MemberMapperExecutor;
import infMarket.models.mybatis.dao.SellPost.SellPostMapperExecutor;
import infMarket.models.mybatis.dto.Member;
import infMarket.models.mybatis.dto.SellPost;
import infMarket.sevelets.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class PurchaseAction implements Action {
    private static final String MEMBER_SESSION_ID = "auth";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try{
            System.out.println("dasdsa");
            Member member = (Member) request.getSession().getAttribute(MEMBER_SESSION_ID);
            if(member == null)
                throw new Exception();
            String sellPostId = request.getParameter("sellPostId");
            SellPostMapperExecutor sellPostMapperExecutor = new SellPostMapperExecutor();
            SellPost sellPost = sellPostMapperExecutor.selectSellPost(Integer.parseInt(sellPostId));
            if(member.getPoint() < sellPost.getPrice())
                throw new Exception();

            member.setPoint(member.getPoint() - sellPost.getPrice());
            MemberMapperExecutor memberMapperExecutor = new MemberMapperExecutor();
            memberMapperExecutor.updateMember(member);

            sellPost.setStatus("판매 완료");
            sellPostMapperExecutor.updateSellPost(sellPost);
        }catch (Exception e){
            response.setStatus(400);
            e.printStackTrace();
        }
    }
}
