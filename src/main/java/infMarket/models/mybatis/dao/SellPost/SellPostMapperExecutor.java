package infMarket.models.mybatis.dao.SellPost;

import infMarket.models.mybatis.config.MyBatisConfig;
import infMarket.models.mybatis.dao.Post.PostMapper;
import infMarket.models.mybatis.dto.Post;
import infMarket.models.mybatis.dto.SellPost;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class SellPostMapperExecutor implements SellPostMapper {
    private static final int POST_PER_PAGE = 10;

    @Override
    public SellPost selectSellPost(int sellPostId) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SellPostMapper sellPostMapper = sqlSession.getMapper(SellPostMapper.class);
        try{
            SellPost sellPost = sellPostMapper.selectSellPost(sellPostId);
            sqlSession.close();
            return sellPost;
        }catch(Exception e){
            sqlSession.rollback();
            sqlSession.close();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertSellPost(SellPost sellPost) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SellPostMapper sellPostMapper = sqlSession.getMapper(SellPostMapper.class);
        try{
            sellPostMapper.insertSellPost(sellPost);
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

    @Override
    public boolean updateSellPost(SellPost sellPost) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SellPostMapper sellPostMapper = sqlSession.getMapper(SellPostMapper.class);
        try{
            System.out.println(sellPost.getSell_post_id());
            System.out.println(sellPost.getItem_name());
            sellPostMapper.updateSellPost(sellPost);
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

    @Override
    public boolean deleteSellPost(int sellPostId) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SellPostMapper sellPostMapper = sqlSession.getMapper(SellPostMapper.class);
        try{
            sellPostMapper.deleteSellPost(sellPostId);
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

    @Override
    public List<SellPost> selectSellPostsByRange(int start, int end) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SellPostMapper sellPostMapper = sqlSession.getMapper(SellPostMapper.class);
        try{
            List<SellPost> sellPosts = sellPostMapper.selectSellPostsByRange(start, end);
            sqlSession.commit();
            sqlSession.close();
            return sellPosts;
        }catch(Exception e){
            sqlSession.rollback();
            sqlSession.close();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SellPost> selectAllSellPost() {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SellPostMapper sellPostMapper = sqlSession.getMapper(SellPostMapper.class);
        try{
            List<SellPost> sellPosts = sellPostMapper.selectAllSellPost();
            sqlSession.commit();
            sqlSession.close();
            return sellPosts;
        }catch(Exception e){
            sqlSession.rollback();
            sqlSession.close();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getTotalPageNum() {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SellPostMapper sellPostMapper = sqlSession.getMapper(SellPostMapper.class);
        try{
            int totalPost = sellPostMapper.getTotalPageNum();
            sqlSession.commit();
            sqlSession.close();
            return (int) Math.ceil((double) totalPost / POST_PER_PAGE);
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
        }
        return 0;
    }
}
