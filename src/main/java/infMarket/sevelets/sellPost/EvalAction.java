package infMarket.sevelets.sellPost;

import infMarket.models.mybatis.dao.Member.MemberMapperExecutor;
import infMarket.models.mybatis.dao.SellPost.SellPostMapperExecutor;
import infMarket.models.mybatis.dto.Member;
import infMarket.models.mybatis.dto.SellPost;
import infMarket.sevelets.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EvalAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try{
            String score = request.getParameter("score");
            String sellPostId = request.getParameter("sellPostId");
            SellPostMapperExecutor sellPostMapperExecutor = new SellPostMapperExecutor();
            MemberMapperExecutor memberMapperExecutor = new MemberMapperExecutor();
            SellPost sellPost = sellPostMapperExecutor.selectSellPost(Integer.parseInt(sellPostId));
            Member member = memberMapperExecutor.selectMember(sellPost.getMember_id());
            member.setSeller_rating((member.getSeller_rating()*member.getNum_evaluated()+Integer.parseInt(score))/(member.getNum_evaluated() + 1));
            member.setNum_evaluated(member.getNum_evaluated()+1);
            memberMapperExecutor.updateMember(member);
        }catch (Exception e){
            response.setStatus(400);
            e.printStackTrace();
        }

    }
}
