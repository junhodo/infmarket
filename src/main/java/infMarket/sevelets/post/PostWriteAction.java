package infMarket.sevelets.post;

import infMarket.models.mybatis.dao.Post.PostMapperExecutor;
import infMarket.models.mybatis.dto.Member;
import infMarket.models.mybatis.dto.Post;
import infMarket.sevelets.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostWriteAction implements Action {
    private static String MEMBER_SESSION_ID = "auth";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try{
            Member member = (Member) request.getSession().getAttribute(MEMBER_SESSION_ID);
            PostMapperExecutor postMapperExecutor = new PostMapperExecutor();
            String title = request.getParameter("title");
            String text = request.getParameter("text");
            System.out.println(title);
            System.out.println(text);
            if(title.length() < 5 || text.length() < 5)
                throw new Exception();
            Post post = new Post();
            post.setMember_name(member.getName());
            post.setMember_id(member.getMember_id());
            post.setText(text);
            post.setTitle(title);
            postMapperExecutor.insertPost(post);
        }catch (Exception e){
            response.setStatus(400);
            e.printStackTrace();
        }
    }
}
