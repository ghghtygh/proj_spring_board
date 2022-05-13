package org.jupo.board.post.vo;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jupo.board.common.vo.DefaultVO;

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
    
	public String getPostNum() {
		return postNum;
	}
	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWrtNo() {
		return wrtNo;
	}
	public void setWrtNo(String writer) {
		this.wrtNo = writer;
	}
	public String getWrtDt() {
		return wrtDt;
	}
	public void setWrtDt(String wrtDt) {
		this.wrtDt = wrtDt;
	}
	public String getReDt() {
		return reDt;
	}
	public void setReDt(String reDt) {
		this.reDt = reDt;
	}
	public List<Map<String,Object>> getFileNames() {
		return fileNames;
	}
	public void setFileNames(List<Map<String,Object>> fileNames) {
		this.fileNames = fileNames;
	}
	public String getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(String viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getWrtId() {
		return wrtId;
	}
	public void setWrtId(String wrtId) {
		this.wrtId = wrtId;
	}
	public String getCountFiles() {
		return countFiles;
	}
	public void setCountFiles(String countFiles) {
		this.countFiles = countFiles;
	}
	
	public String toStringMultiline() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
 
}
