package com.vinea.dto;

import java.util.Date;

public class PostVO {
	 
	
	// 게시글 번호
	private int postNum;
	
	// 게시글 제목
    private String title;
    
    // 게시글 내용
    private String content;
    
    // 작성 유저 번호
    private int writer;
    
    // 작성일
    private Date wrtDt;
    
    // 수정일
    private Date reDt;
    
    // 첨부파일명
    private String fileName;

    // 첨부파일 경로
    private String fileDir;
    
    // 조회 수
    private int viewCnt;
    
    // 유저 아이디
    private String wrtId;
    
    
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
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
	public int getWriter() {
		return writer;
	}
	public void setWriter(int writer) {
		this.writer = writer;
	}
	public Date getWrtDt() {
		return wrtDt;
	}
	public void setWrtDt(Date wrtDt) {
		this.wrtDt = wrtDt;
	}
	public Date getReDt() {
		return reDt;
	}
	public void setReDt(Date reDt) {
		this.reDt = reDt;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileDir() {
		return fileDir;
	}
	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}
	public long getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getWrtId() {
		return wrtId;
	}
	public void setWrtId(String wrtId) {
		this.wrtId = wrtId;
	}
    
    
    
 
}
