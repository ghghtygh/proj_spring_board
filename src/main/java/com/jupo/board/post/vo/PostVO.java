package com.jupo.board.post.vo;

import java.util.List;
import java.util.Map;

import lombok.*;
import com.jupo.board.common.vo.DefaultVO;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostVO extends DefaultVO {
	 
	// 게시판 번호
    private String boardNo;
	// 게시글 번호
	private String postNo;
	// 게시글 제목
    private String title;
    // 게시글 내용
    private String content;
    // 최초등록유저번호
    private String frstRegtNo;
    // 최초등록아이디
    private String loginId;
    // 최초등록닉네임
    private String nickname;
    // 최초작성일시
    private String frstRegtDt;
    // 최종수정유저번호
    private String lastUpdtNo;
    // 최종수정일시
    private String lastUpdtDt;
    // 첨부파일명
    private List<Map<String, Object>> fileNames;
    // 조회 수
    private String viewCnt;
    // 첨부파일 여부
    private String fileYn;
    // 썸네일
    private String thumbnail;
    // 삭제 여부
    private String delYn;
    // 위도
    private double latitude;
    // 경도
    private double longitude;
}
