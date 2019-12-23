package com.vinea.service;



import java.util.List;

import javax.inject.Inject;
 
import org.springframework.stereotype.Service;
 
import com.vinea.dao.PostDAO;
import com.vinea.dto.PostVO;
 
@Service
public class PostServiceImpl implements PostService {
 
    @Inject
    private PostDAO dao;
    
    @Override
    public List<PostVO> selectPost() throws Exception {
 
        return dao.selectPost();
    }

    @Override
    public void create(PostVO vo) throws Exception{
    	
    	String inDate   = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
		String inTime   = new java.text.SimpleDateFormat("HHmm").format(new java.util.Date());
		
		String title = vo.getTitle();
		String content = vo.getContent();
		
		title = title.replace("<", "&lt;");
		title = title.replace(">", "&gt;");
		title = title.replace("  ", "&nbsp;&nbsp");
		
		
		vo.setTitle(title);
		vo.setContent(content);
		vo.setDate(inDate);
		vo.setTime(inTime);
		
    	dao.create(vo);
    }
    
    
    @Override
    public int countPost(String searchOption,String keyword) throws Exception{
    	
    	return dao.countPost(searchOption,keyword);
    }
    
    @Override
    public List<PostVO> listPost(int start, int end, String searchOption, String keyword) throws Exception{
    
    	return dao.listPost(start,end,searchOption,keyword);
    }
    
    
    @Override
	public void viewCntPost(Integer num) throws Exception {
		
    	dao.viewCntPost(num);
    	
	}
    
    
    @Override
    public void modifyPost(PostVO vo) throws Exception{
    	
    	dao.modifyPost(vo);
    }
    
    @Override
    public void deletePost(Integer num) throws Exception{
    	
    	
    	dao.deletePost(num);
    }
    
    @Override
    public PostVO read(Integer num)throws Exception{
    	return dao.read(num);
    }
    

	
 
}
 
