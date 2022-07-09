package com.jupo.board.user.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.jupo.board.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jupo.board.user.vo.UserInfo;

@Mapper
public interface UserDAO{


	public List<UserVO> selectUser() throws Exception;

	public void insertUserInfo(UserVO vo) throws Exception;

	public int getLoginIdCnt(String userId) throws Exception;

	public int getNicknameCnt(String userId) throws Exception;

	public UserVO selectUserInfo(UserInfo userInfo) throws Exception;

}
