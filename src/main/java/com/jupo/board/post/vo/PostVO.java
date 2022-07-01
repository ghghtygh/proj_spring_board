package com.jupo.board.post.vo;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jupo.board.common.vo.DefaultVO;

@Getter
@Setter
@ToString
public class PostVO extends DefaultVO {
	 
	
	// 게시글 번호
	private String postNum;
	// 게시글 제목
    private String title;
    // 게시글 내용
    private String content;
    // 작성 유저 번호
    private String wrtNo;
    // 작성일
    private String wrtDt;
    // 수정일
    private String reDt;
    // 첨부파일명
    private List<Map<String,Object>> fileNames;
    // 조회 수
    private String viewCnt;
    // 유저 아이디
    private String wrtId;
    // 첨부파일 여부
    private String countFiles;

}
