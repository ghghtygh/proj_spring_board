package com.jupo.board.user.service;

import java.util.List;

import com.jupo.board.user.vo.UserInfo;
import com.jupo.board.user.vo.UserVO;

public interface UserService {
	
	void insertUserInfo(UserVO vo) throws Exception;

	UserVO selectUserInfo(UserInfo userInfo) throws Exception;

	int idCheck(String userId) throws Exception;
}
