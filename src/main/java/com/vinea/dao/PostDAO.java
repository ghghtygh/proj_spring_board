package com.vinea.dao;

import java.util.List;
import java.util.Map;

import com.vinea.dto.PostVO;
 
public interface PostDAO {
    
    public List<PostVO> selectPost() throws Exception;

    public List<PostVO> listPost(int start, int pageSize, String searchOption, String keyword) throws Exception;

    public void viewCntPost(Integer postNo) throws Exception;
    
    public void modifyPost(PostVO vo) throws Exception;
    
    public void deletePost(Integer postNo) throws Exception;
    
    public PostVO read(Integer postNo) throws Exception;
    
    public void create(PostVO vo)throws Exception;

    public int countPost(String searchOption,String keyword) throws Exception;

    public int countFile(Integer postNo) throws Exception;
    
	public void insertFile(Map<String, Object> map);

	public List<Map<String,Object>> selectFileList(Integer postNo) throws Exception;

	public Map<String, Object> selectFile(Integer fileNo) throws Exception;

	public void deleteFiles(Integer postNo) throws Exception;

	public void deleteFile(Integer fileNo) throws Exception;

	    
}