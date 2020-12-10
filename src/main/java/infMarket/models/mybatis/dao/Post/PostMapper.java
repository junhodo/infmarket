package infMarket.models.mybatis.dao.Post;


import infMarket.models.mybatis.dto.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostMapper {
    public List<Post> selectPostsByRange(@Param("start") int start, @Param("end") int end);
    public int getTotalPageNum();
    public Post selectPost(@Param("postId") int postId);
    public boolean insertPost(Post post);
    public boolean updatePost(Post post);
    public boolean deletePost(@Param("postId") int postId);
    List<Post> selectAllPost();
}
