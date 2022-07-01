package com.jupo.board.app.web;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.jupo.board.post.vo.PostVO;
import com.jupo.board.post.service.PostService;

@Controller
public class AndroidController {
	
	@Inject
    private PostService postService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/android")
	public ModelAndView androidTest() throws Exception{
		
		ModelAndView mav = new ModelAndView("android/index");
		
		Gson gson = new Gson();
		
		List<PostVO> list = new ArrayList<PostVO>();

		PostVO searchVO = new PostVO();
		searchVO.setPage(1);
		searchVO.setPageSize(100);
		list = postService.selectPostList(searchVO);
		
		String json = gson.toJson(list);
		
		mav.addObject("testJson",json);
		
		return mav;
	}
	
	@RequestMapping("/android/dbtest")
	public ModelAndView androidDBTest(@RequestParam(defaultValue="") String sendMsg) throws Exception{
		
		ModelAndView mav = new ModelAndView("android/dbtest");
		
		logger.info("sendMsg : "+sendMsg.toString());
		
		return mav;
	}
	
	
	//@ModelAttribute PostVO vo
	@RequestMapping("/android/write")
	public ModelAndView androidWrite(@ModelAttribute PostVO vo) throws Exception{
		
		//PostVO vo = new PostVO();
		
		ModelAndView mav = new ModelAndView("android/write");
		
		logger.info(getClass().getName());
		
		try{
			logger.info(vo.toString());
		}catch(Exception e){
			logger.info("vo -> null");
		}

		postService.insertPostInfo(vo,null);
		
		mav.addObject("testJson","성공");
		
		return mav;
	}
	
	@RequestMapping(value="/android/login",method=RequestMethod.POST)
	public ModelAndView androidLogin() throws Exception{
		
		ModelAndView mav = new ModelAndView("android/login");
		
		Gson gson = new Gson();
		
		
		
		String json = gson.toJson("");
		
		mav.addObject("testJson",json);
		
		return mav;
	}
}
