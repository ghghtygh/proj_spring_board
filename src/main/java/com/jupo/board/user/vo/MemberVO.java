package com.jupo.board.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO extends UserVO {

    // 멤버번호
    private String memberNo;
    // 회원등급
    private String levelCd;
    // 회원등급명
    private String levelCdNm;
    // 닉네임
    private String nickname;
    // 카페번호
    private String cafeNo;
}
