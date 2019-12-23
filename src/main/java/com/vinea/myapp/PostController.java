package com.vinea.myapp;


import java.util.List;
 
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vinea.dto.PostVO;
import com.vinea.dto.UserVO;
import com.vinea.service.PostPager;
import com.vinea.service.PostService;


@Controller
public class PostController {
    
    
    @Inject
    private PostService service;
    
    @RequestMapping(value = "/")
    public String home(@RequestParam(defaultValue="1") int page,
    		@RequestParam(defaultValue="all") String searchOption,
    		@RequestParam(defaultValue="") String keyword, Model model) throws Exception{
 
    	// 전체 포스트 개수
    	int count = service.countPost(searchOption,keyword);
        
    	// 페이징 위한 객체
    	PostPager pager = new PostPager(count,page);
    	
    	int start = pager.getStartIndex();
    	
    	int pagesize = pager.getPageSize();
    	
        List<PostVO> postList = service.listPost(start,pagesize,searchOption,keyword);
        
        
    	//List<PostVO> postList = service.selectPost();
    	
    	
        model.addAttribute("postList", postList);
        model.addAttribute("cnt",count);
        model.addAttribute("pager", pager);
        model.addAttribute("searchOption",searchOption);
        model.addAttribute("keyword",keyword);
        
        return "home";
    }
    
    @RequestMapping(value="/doModify", method=RequestMethod.GET) 
    public String doModifyGET() throws Exception{
    	
    	
    	
    	return "redirect:/";
    }
    
    @RequestMapping(value="/doModify", method=RequestMethod.POST) 
    public String doModifyPost(@RequestParam("num")int num,@RequestParam("page")int page, Model model) throws Exception{
    	
    	
    	model.addAttribute(service.read(num));
    	model.addAttribute("page",page);
    	return "modify";
    }
    
    @RequestMapping(value="/modify", method=RequestMethod.GET) 
    public String modifyGET() throws Exception{
    	
    	
    	return "redirect:/";
    }
    
    @RequestMapping(value="/modify", method=RequestMethod.POST) 
    public String modifyPOST(PostVO post) throws Exception{
    	
    	service.modifyPost(post);
    	
    	return "redirect:/read?num="+post.getPostNum();
    }
    
    
    @RequestMapping(value="/delete", method=RequestMethod.POST) 
    public String deleteGET(@RequestParam("num")int num) throws Exception{
    	
    	service.deletePost(num);
    	
    	return "redirect:/";
    }
    
    
    @RequestMapping(value="/read", method=RequestMethod.GET) 
    public void read(@RequestParam("num")int num,
    		@RequestParam(defaultValue="1") int page, Model model) throws Exception{
    	
    	service.viewCntPost(num);
    	model.addAttribute(service.read(num));
    	model.addAttribute("page",page);
    }
    
    
    @RequestMapping(value="/write", method=RequestMethod.GET)
    public String writeGET(HttpSession session) throws Exception{
    	
    	UserVO user = (UserVO) session.getAttribute("user");
    	
    	if (user==null) {
    		return "redirect:/signin";
    	}else {
    		return "write";
    	}
    }
    
    @RequestMapping(value="/write", method=RequestMethod.POST)
    public String writePOST(PostVO post) throws Exception{
    	
    	service.create(post);
    	
    	return "redirect:/";
    }
    
    
    
}