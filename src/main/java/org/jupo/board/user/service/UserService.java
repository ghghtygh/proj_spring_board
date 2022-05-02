package org.jupo.board.user.service;

import java.util.List;

import org.jupo.board.user.vo.UserInfo;
import org.jupo.board.user.vo.UserVO;

public interface UserService {
	
	public List<UserVO> selectUser() throws Exception;
	
	public void create(UserVO vo) throws Exception;

	public UserVO login(UserInfo userInfo) throws Exception;

	public int idCheck(String userId) throws Exception;
}
