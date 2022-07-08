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

	// 사용자 번호
	private String userNo;
	// 사용자 닉네임
	private String nickname;
	// 로그인 아이디
	private String loginId;
	// 패스워드
	private String password;
	// 이메일
	private String email;
	// 이름
	private String userNm;
	// 최초등록일시
	private String frstRegtDt;
	// 최종수정 아이디
	private String lastUpdtNo;
	// 최종수정일시
	private String lastUpdtDt;
	// 회원등급
	private String userLevel;
	// 휴대폰번호
	private String phoneNo;
	// 주소
	private String userAddr;
	// 주소 상세
	private String userAddrDtl;
	// 우편번호
	private String zip;
	// 회원구분코드
	private String userCd;
	// 회원구분코드명
	private String userCdNm;
	// 탈퇴여부
	private String resignYn;
}
