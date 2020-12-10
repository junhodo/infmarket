package infMarket.models.mybatis.dao.Wish;

import infMarket.models.mybatis.config.MyBatisConfig;
import infMarket.models.mybatis.dao.SellPost.SellPostMapper;
import infMarket.models.mybatis.dto.SellPost;
import infMarket.models.mybatis.dto.Wish;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class WishMapperExecutor implements WishMapper {
    @Override
    public List<Wish> selectMemberWishList(String memberId) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        WishMapper wishMapper = sqlSession.getMapper(WishMapper.class);
        try{
            List<Wish> wishes = wishMapper.selectMemberWishList(memberId);
            sqlSession.commit();
            sqlSession.close();
            return wishes;
        }catch(Exception e){
            sqlSession.rollback();
            sqlSession.close();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Wish> selectMemberWishListInSell(String memberId) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        WishMapper wishMapper = sqlSession.getMapper(WishMapper.class);
        try{
            List<Wish> wishes = wishMapper.selectMemberWishListInSell(memberId);
            sqlSession.commit();
            sqlSession.close();
            return wishes;
        }catch(Exception e){
            sqlSession.rollback();
            sqlSession.close();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Wish selectWish(int wishId) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        WishMapper wishMapper = sqlSession.getMapper(WishMapper.class);
        try{
            Wish wish = wishMapper.selectWish(wishId);
            sqlSession.close();
            return wish;
        }catch(Exception e){
            sqlSession.rollback();
            sqlSession.close();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertWish(Wish wish) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        WishMapper wishMapper = sqlSession.getMapper(WishMapper.class);
        try{
            wishMapper.insertWish(wish);
            sqlSession.commit();
            sqlSession.close();
            return true;
        }catch(Exception e){
            sqlSession.rollback();
            sqlSession.close();
            e.printStackTrace();
        }
        return false;
    }
}
