package infMarket.sevelets.post;

import infMarket.models.mybatis.dao.Post.PostMapperExecutor;
import infMarket.models.mybatis.dto.Member;
import infMarket.models.mybatis.dto.Post;
import infMarket.sevelets.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostUpdateAction implements Action {
    private static String MEMBER_SESSION_ID = "auth";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try{
            int postId = Integer.parseInt(request.getParameter("postId"));
            Member member = (Member) request.getSession().getAttribute(MEMBER_SESSION_ID);
            PostMapperExecutor postMapperExecutor = new PostMapperExecutor();
            Post post = postMapperExecutor.selectPost(postId);
            if(post.getMember_id().equals(member.getMember_id())){
                String title = request.getParameter("title");
                String text = request.getParameter("text");
                if(title.length() < 5 || text.length() < 5)
                    throw new Exception();
                post.setTitle(title);
                post.setText(text);
                postMapperExecutor.updatePost(post);
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            response.setStatus(400);
            e.printStackTrace();
        }
    }
}
