package infMarket.models.mybatis.dao.Post;

import infMarket.models.mybatis.config.MyBatisConfig;
import infMarket.models.mybatis.dao.Member.MemberMapper;
import infMarket.models.mybatis.dto.Member;
import infMarket.models.mybatis.dto.Post;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PostMapperExecutor implements PostMapper {
    private static  final int POST_PER_PAGE = 10;
    @Override
    public List<Post> selectPostsByRange(int start, int end) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
        try{
            List<Post> posts = postMapper.selectPostsByRange(start, end);
            sqlSession.commit();
            sqlSession.close();
            return posts;
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
        PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
        try{
            int totalPost = postMapper.getTotalPageNum();
            System.out.println(totalPost);
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

    @Override
    public Post selectPost(int postId) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
        try{
            Post post = postMapper.selectPost(postId);
            sqlSession.close();
            return post;
        }catch(Exception e){
            sqlSession.rollback();
            sqlSession.close();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertPost(Post post) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
        try{
            postMapper.insertPost(post);
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
    public boolean updatePost(Post post) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
        try{
            postMapper.updatePost(post);
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
    public boolean deletePost(int postId) {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
        try{
            postMapper.deletePost(postId);
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
    public List<Post> selectAllPost() {
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
        try{
            List<Post> posts = postMapper.selectAllPost();
            sqlSession.commit();
            sqlSession.close();
            return posts;
        }catch(Exception e){
            sqlSession.rollback();
            sqlSession.close();
            e.printStackTrace();
        }
        return null;
    }
}
