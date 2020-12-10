package infMarket.models.mybatis.dao.SellPost;


import infMarket.models.mybatis.dto.Post;
import infMarket.models.mybatis.dto.SellPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellPostMapper {
    public SellPost selectSellPost(@Param("sellPostId") int sellPostId);
    public boolean insertSellPost(SellPost sellPost);
    public boolean updateSellPost(SellPost sellPost);
    public boolean deleteSellPost(@Param("sellPostId") int sellPostId);
    public List<SellPost> selectSellPostsByRange(@Param("start") int start, @Param("end") int end);
    public List<SellPost> selectAllSellPost();
    public int getTotalPageNum();
}
