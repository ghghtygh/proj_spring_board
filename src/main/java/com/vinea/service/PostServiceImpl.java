package com.vinea.service;



import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinea.common.FileUtils;
import com.vinea.dao.PostDAO;
import com.vinea.dto.PostVO;
 


@Service
public class PostServiceImpl implements PostService {
 
    @Inject
    private PostDAO dao;
    
    @Autowired
    @Resource(name="fileUtils")
    private FileUtils fileUtils;
    
    SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
	
	
    private final static Logger logger = Logger.getLogger(PostServiceImpl.class);
    
    @Override
    public List<PostVO> selectPost() throws Exception {
 
        return dao.selectPost();
    }
    
    
    
    @Override
    public void create(PostVO vo, HttpServletRequest request) throws Exception{
    	
    	vo.setTitle(checkTitle(vo.getTitle()));
    	
    	dao.create(vo);
    	
    	
    	List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(vo,request);
    	
    	for (int i =0,size=list.size();i<size;i++){
    		dao.insertFile(list.get(i));
    	}
    	
    	
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
    	
    	vo.setTitle(checkTitle(vo.getTitle()));
    	
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
    
    @Override
    public List<Map<String,Object>> selectFileList(Integer num) throws Exception{
    	
    	return dao.selectFileList(num);
    }
    
    @Override
    public Map<String,Object> selectFile(Integer num) throws Exception{
    	
    	return dao.selectFile(num);
    }
	public String checkTitle(String title) throws Exception{
	   if (title.equals("")){
		   return "제목 없음";
	   }
		   
	   title = title.replace("<", "&lt;");
	   title = title.replace(">", "&gt;");
	   title = title.replace("  ", "&nbsp;&nbsp");
	   
	   return title;
	}
 
}
 
