package com.jupo.board.user.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.jupo.board.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jupo.board.user.vo.UserInfo;
import com.jupo.board.user.vo.UserVO;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Inject
	PasswordEncoder pwEncoder;
	
	@Override
	public void insertUserInfo(UserVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		String encPw = pwEncoder.encode(vo.getUserPw());
		vo.setUserPw(encPw);

		userDAO.insertUserInfo(vo);
	}
	@Override
	public int idCheck(String userId) throws Exception{
		
		return userDAO.idCheck(userId);
	}
	
	@Override
	public UserVO selectUserInfo(UserInfo userInfo) throws Exception{
		
		UserVO vo = userDAO.selectUserInfo(userInfo);
		
		if(vo == null){
			return null;
		}

		if(pwEncoder.matches(userInfo.getUserPw(), vo.getUserPw())){
			return vo;
		}

		return null;
	}
	
}


