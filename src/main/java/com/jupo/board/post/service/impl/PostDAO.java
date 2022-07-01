package com.jupo.board.post.service.impl;

import java.util.List;
import java.util.Map;
import com.jupo.board.post.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostDAO{

    /***
     * 게시글 리스트를 조회한다.
     * @param vo
     * @return
     * @throws Exception
     */
   public List<PostVO> selectPostList(PostVO vo) throws Exception;

   public void insertPostInfo(PostVO vo) throws Exception;

   public void insertFile(Map<String, Object> map) throws Exception;

   public int selectPostListCnt(PostVO searchVO) throws Exception;

   public int countFile(String postNo) throws Exception;

   public void viewCntPost(String postNo) throws Exception;

   public void modifyPost(PostVO vo) throws Exception;

   public void deletePost(String postNo) throws Exception;

   public PostVO selectPostDetail(String postNo) throws Exception;

   public List<Map<String,Object>> selectFileList(String postNo) throws Exception;

   public Map<String,Object> selectFile(String fileNo) throws Exception;

   public void deleteFiles(String postNo) throws Exception;

   public void deleteFile(String fileNo) throws Exception;

}