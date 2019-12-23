package com.vinea.myapp;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vinea.dto.UserInfo;
import com.vinea.dto.UserVO;
import com.vinea.service.UserService;


@Controller
public class UserController {

	private final static Logger logger = Logger.getLogger(UserController.class);
	
	@Inject
    private UserService service;
	
	
	@RequestMapping(value="/doSignup",method=RequestMethod.GET)
	public void doSignupGET() {
		
	}
	@RequestMapping(value="/doSignup",method=RequestMethod.POST)
	public String doSignupPOST(UserInfo userInfo) {
		return "signup";
	}
	
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public void signupGET() {
		
	}
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public String signupPOST(UserVO vo) throws Exception {
		
		service.create(vo);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/idCheck", method=RequestMethod.POST)
	
		@ResponseBody
		public Map<Object, Object> idcheck( @RequestParam("userId") String userId) throws Exception {
		         
	        int count = 0;
	        
	        Map<Object, Object> map = new HashMap<Object, Object>();
	        
	        //logger.info("\n\n>>>>>>>>> 유저 아이디 : "+userId);
	        
	        count = service.idCheck(userId);
	        
	        map.put("cnt", count);
	        
	        return map;
	    }
	
	@RequestMapping(value="/signin",method=RequestMethod.GET)
	public void signinGET(@RequestParam(defaultValue="")String userId, Model model) throws Exception {
		model.addAttribute("userId",userId);
	}
	
	@RequestMapping(value="/signin",method=RequestMethod.POST)
	public String signinPOST(UserInfo userInfo,HttpSession session,Model model) throws Exception {
		
		
		UserVO vo = service.login(userInfo);
		
		if(vo==null) {
			model.addAttribute("userId",userInfo.getUserId());
			return "signin";
		}
		
		session.setAttribute("user",vo);
	
		return "redirect:/";
		
		/*
		 * 이전페이지
		 * 
		 * public String signinPOST(UserInfo userInfo,HttpSession session,HttpServletRequest request,Model model) throws Exception {
		 * 
		 * String referer = request.getHeader("Referer");
		 * 
		 * return "redirect:/"+referer;
		 * 
		 */
	
	}
	@RequestMapping("/signout")
	public String signout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/";
	}
	

	
}
