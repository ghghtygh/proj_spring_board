package com.vinea.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vinea.dao.UserDAO;
import com.vinea.dto.UserInfo;
import com.vinea.dto.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO dao;
	
	@Override
	public List<UserVO> selectUser() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectUser();
	}

	@Override
	public void create(UserVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		dao.create(vo);
	}
	@Override
	public int idCheck(String userId) throws Exception{
		
		return dao.idCheck(userId);
	}
	
	@Override
	public UserVO login(UserInfo userInfo) throws Exception{
		
		
		return dao.login(userInfo);
	}
	
}


