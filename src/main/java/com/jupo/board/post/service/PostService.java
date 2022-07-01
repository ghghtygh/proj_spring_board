package com.jupo.board.post.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jupo.board.post.vo.PostVO;
 
public interface PostService {
    
    public List<PostVO> selectPostList(PostVO searchVO) throws Exception;
    
    public void viewCntPost(String postNo) throws Exception;
    
    public void modifyPost(PostVO vo, HttpServletRequest request) throws Exception;
    
    public void deletePost(String postNo) throws Exception;
 
    public void deletePosts(List<String> postNoList) throws Exception;
    
    public PostVO selectPostDetail(String postNo) throws Exception;
    
    public void insertPostInfo(PostVO vo, HttpServletRequest request) throws Exception;

    public int selectPostListCnt(PostVO searchVO) throws Exception;

    public List<Map<String,Object>> selectFileList(String postNo) throws Exception;

    public Map<String, Object> selectFile(String fileNo) throws Exception;
    
    public void deleteFile(String fileNo) throws Exception;


}
 

