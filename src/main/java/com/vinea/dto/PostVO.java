package com.vinea.dto;

public class PostVO {
	 
	
	// 게시글 번호
	private int postNum;
	
	// 게시글 제목
    private String title;
    
    // 게시글 내용
    private String content;
    
    // 작성 유저 번호
    private int writer;
    
    // 작성 일자 ex)20191210
    private String date;
    
    // 작성 시간 ex)1835
    private String time;
    
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
