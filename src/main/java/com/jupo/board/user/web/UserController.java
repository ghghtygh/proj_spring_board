package com.jupo.board.user.web;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jupo.board.user.vo.UserInfo;
import com.jupo.board.user.vo.UserVO;
import com.jupo.board.user.service.UserService;

@Log4j2
@Controller
public class UserController {

	@Autowired
    private UserService userService;
	
	private final String JSP_DIR = "user/";

	/***
	 * 회원가입 페이지 이동
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public String signupGet() throws Exception {
		return JSP_DIR + "signup.user";
	}

	/***
	 * 회원 등록
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public String signupPOST(UserVO userVO) throws Exception {
		
		userService.insertUserInfo(userVO);
		
		return "redirect:/";
	}

	/***
	 * 아이디 중복확인
	 * @param loginId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/idCheck", method=RequestMethod.POST)
	public Map<Object, Object> idcheck( @RequestParam("loginId") String loginId) throws Exception {
		Map<Object, Object> map = new HashMap<Object, Object>();
		int count = userService.idCheck(loginId);
		map.put("cnt", count);
		return map;
	}
	
	// 로그인 페이지 이동 
	@RequestMapping(value="/signin",method=RequestMethod.GET)
	public String signinGET(@RequestParam(defaultValue="")String userId,HttpSession session, Model model) throws Exception {
		
		// 로그인 되어있을 경우 메인화면 이동
		if (session.getAttribute("user")!=null){
			
			 return "redirect:/";
			 
		}else{
			
			model.addAttribute("userId",userId);
			return JSP_DIR + "signin.user";
			
		}
	}
	
	// 로그인 기능 수행
	@RequestMapping(value="/signin",method=RequestMethod.POST)
	public String signinPOST(UserInfo userInfo,HttpSession session,HttpServletRequest request,Model model) throws Exception {
		
		
		UserVO loginVO = userService.selectUserInfo(userInfo);
		
		if(loginVO==null) {
			
			model.addAttribute("userNo",userInfo.getUserNo());
			return JSP_DIR + "signin.user";
			
		}else{
			session.setAttribute("loginVO", loginVO);
			return "redirect:/";
		}
	
	}
	
	// 로그아웃 기능 수행
	@RequestMapping("/signout")
	public String signout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/";
	}
	

	
}
