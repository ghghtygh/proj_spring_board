package com.jupo.board.user.service;

import java.util.List;

import com.jupo.board.user.vo.UserInfo;
import com.jupo.board.user.vo.UserVO;

public interface UserService {
	
	public List<UserVO> selectUser() throws Exception;
	
	public void insertUserInfo(UserVO vo) throws Exception;

	public UserVO selectUserInfo(UserInfo userInfo) throws Exception;

	public int idCheck(String userId) throws Exception;
}
