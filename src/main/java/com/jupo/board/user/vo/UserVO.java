package com.jupo.board.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Setter
@ToString
public class UserVO {

	// 유저 번호
	private int userNum;
	
	// 유저 아이디
	private String userId;
	
	// 유저 패스워드
	private String userPw;
	
	// 유저 이메일
	private String userEmail;

}
