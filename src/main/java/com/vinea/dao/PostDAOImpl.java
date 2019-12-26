package com.vinea.dao;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.vinea.dto.PostVO;

@Repository
public class PostDAOImpl implements PostDAO {

   @Inject
   private SqlSession sqlSession;
   
   private static final String Namespace = "com.vinea.mapper.postMapper";
   
   @Override
   public List<PostVO> selectPost() throws Exception {

       return sqlSession.selectList(Namespace+".selectPost");
   }
   
   @Override
   public void create(PostVO vo)throws Exception{
	   
	   sqlSession.insert(Namespace+".create",vo);
   }
   @Override
   public void insertFile(Map<String, Object> map){
	   
	   sqlSession.insert(Namespace+".insertFile",map);
	   
   }
   
   @Override
   public int countPost(String searchOption,String keyword) throws Exception{
	   
	   Map<String,Object> map = new HashMap<String,Object>();
	   
	   map.put("searchOption", searchOption);
	   map.put("keyword", keyword);
	   
	   return sqlSession.selectOne(Namespace+".countPost", map);
   }
   
   @Override
   public List<PostVO> listPost(int start, int end, String searchOption, String keyword) throws Exception{

	   Map<String,Object> map = new HashMap<String,Object>();
	   
	   map.put("start", start);
	   map.put("end", end);
	   
	   map.put("searchOption", searchOption);
	   map.put("keyword", keyword);
	   
	   return sqlSession.selectList(Namespace+".listPost",map);
   }
   
   @Override
   public void viewCntPost(Integer num) throws Exception{
	   
	   sqlSession.update(Namespace+".viewCntPost",num);
   }
   
   @Override
   public void modifyPost(PostVO vo) throws Exception{
	   
	   
	   sqlSession.update(Namespace+".modifyPost",vo);
   }
   
   @Override
   public void deletePost(Integer num) throws Exception{
	   
	   sqlSession.delete(Namespace+".deletePost",num);
   }
   
   @Override
   public PostVO read(Integer num) throws Exception{
	
	   return sqlSession.selectOne(Namespace+".read", num);
   }
   
   @Override
   public List<Map<String,Object>> selectFileList(Integer num) throws Exception{
	   return sqlSession.selectList(Namespace+".selectFileList", num);
   }
   
   @Override
   
   public Map<String,Object> selectFile(Integer num) throws Exception{
	   
	   return sqlSession.selectOne(Namespace+".selectFile",num);
   }
   


}