package infMarket.models.mybatis.dao.Wish;



import infMarket.models.mybatis.dto.Wish;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WishMapper {
    public List<Wish> selectMemberWishList(@Param("memberId") String memberId);
    public List<Wish> selectMemberWishListInSell(@Param("memberId") String memberId);
    public Wish selectWish(@Param("wishId") int wishId);
    public boolean insertWish(Wish wish);

}
