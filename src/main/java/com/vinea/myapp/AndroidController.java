package com.vinea.myapp;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.vinea.dto.PostVO;
import com.vinea.service.PostService;

@Controller
public class AndroidController {
	
	@Inject
    private PostService service;
    
    private Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping("/android")
	public ModelAndView androidTest() throws Exception{
		
		ModelAndView mav = new ModelAndView("android/index");
		
		Gson gson = new Gson();
		
		List<PostVO> list = new ArrayList<PostVO>();
		
		list = service.listPost(0,100,"all","");
		
		String json = gson.toJson(list);
		
		mav.addObject("testJson",json);
		
		return mav;
	}
}
