package com.jupo.user.service;

import java.util.List;

import com.jupo.user.vo.UserInfo;
import com.jupo.user.vo.UserVO;

public interface UserService {
	
	public List<UserVO> selectUser() throws Exception;
	
	public void create(UserVO vo) throws Exception;

	public UserVO login(UserInfo userInfo) throws Exception;

	public int idCheck(String userId) throws Exception;
}
