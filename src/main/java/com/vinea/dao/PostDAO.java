package com.vinea.dao;

import java.util.List;

import com.vinea.dto.PostVO;
 
public interface PostDAO {
    
    public List<PostVO> selectPost() throws Exception;

    public List<PostVO> listPost(int start, int end, String searchOption, String keyword) throws Exception;

    public void viewCntPost(Integer num) throws Exception;
    
    public void modifyPost(PostVO vo) throws Exception;
    
    public void deletePost(Integer num) throws Exception;
    
    public PostVO read(Integer num) throws Exception;
    
    public void create(PostVO vo)throws Exception;

    public int countPost(String searchOption,String keyword) throws Exception;

	    
}