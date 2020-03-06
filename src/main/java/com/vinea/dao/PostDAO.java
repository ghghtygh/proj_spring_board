package com.vinea.dao;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.vinea.dto.PostVO;

@Repository
public class PostDAO{

   @Inject
   private SqlSession sqlSession;
   
   private static final String Namespace = "com.vinea.mapper.postMapper";
   
   public List<PostVO> selectPost() throws Exception {

       return sqlSession.selectList(Namespace+".selectPost");
   }
   
   public void create(PostVO vo)throws Exception{
	   
	   sqlSession.insert(Namespace+".create",vo);
   }
   
   public void insertFile(Map<String, Object> map){
	   
	   sqlSession.insert(Namespace+".insertFile",map);
	   
   }
   
   public int countPost(String searchOption,String keyword) throws Exception{
	   
	   Map<String,Object> map = new HashMap<String,Object>();
	   
	   map.put("searchOption", searchOption);
	   map.put("keyword", keyword);
	   
	   return sqlSession.selectOne(Namespace+".countPost", map);
   }
   
   public int countFile(String postNo) throws Exception{
	   
	   
	   return sqlSession.selectOne(Namespace+".fileCheck",postNo);
   }
   
   public List<PostVO> listPost(Map<String,Object> map) throws Exception{

	  
	   
	   return sqlSession.selectList(Namespace+".listPost",map);
   }
   
   public void viewCntPost(String postNo) throws Exception{
	   
	   sqlSession.update(Namespace+".viewCntPost", postNo);
   }
   
   public void modifyPost(PostVO vo) throws Exception{
	   
	   
	   sqlSession.update(Namespace+".modifyPost", vo);
   }
   
   public void deletePost(String postNo) throws Exception{
	   
	   sqlSession.update(Namespace+".deletePost", postNo);
   }
   
   public PostVO read(String postNo) throws Exception{
	
	   return sqlSession.selectOne(Namespace+".read", postNo);
   }
   
   public List<Map<String,Object>> selectFileList(String postNo) throws Exception{
	   return sqlSession.selectList(Namespace+".selectFileList", postNo);
   }
   
   public Map<String,Object> selectFile(String fileNo) throws Exception{
	   
	   return sqlSession.selectOne(Namespace+".selectFile",fileNo);
   }
   
   public void deleteFiles(String postNo) throws Exception{
	   sqlSession.update(Namespace+".deleteFiles", postNo);
   }

   public void deleteFile(String fileNo) throws Exception{
	   sqlSession.update(Namespace+".deleteFile", fileNo);
   }

}