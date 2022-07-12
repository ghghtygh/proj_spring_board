package com.jupo.board.user.service.impl;

import com.jupo.board.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import com.jupo.board.user.vo.UserInfo;

@Mapper
public interface UserDAO{

	public int insertUserInfo(UserVO vo) throws Exception;

	public int getLoginIdCnt(String userId) throws Exception;

	public UserVO selectUserInfo(UserInfo userInfo) throws Exception;

}
