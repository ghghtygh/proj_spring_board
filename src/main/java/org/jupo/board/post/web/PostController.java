package org.jupo.board.post.web;


import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jupo.board.post.service.PostService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.jupo.board.post.vo.PostVO;
import org.jupo.board.user.vo.UserVO;
import org.jupo.board.common.util.PostPager;


@Controller
public class PostController {
    
    
    @Autowired
    private PostService postService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());    

    private final String JSP_DIR = "/post/";

	/***
	 * 게시판 메인화면
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/")
    public String home(@ModelAttribute("searchVO") PostVO searchVO, Model model) throws Exception{
 
    	// 전체 포스트 개수 (searchOption: 검색옵션, keyword: 검색어)
    	int count = postService.selectPostListCnt(searchVO);

    	int page = searchVO.getPage();

    	// 페이징 위한 객체 (count: 전체포스트개수, page: 요청페이지)
    	PostPager pager = new PostPager(count, page);

    	// 제일 상위 게시물 번호
    	int start = pager.getStartIndex();

    	// 게시물 개수
    	int pageSize = pager.getPageSize();

    	searchVO.setStart(start);
    	searchVO.setPageSize(pageSize);

        List<PostVO> postList = postService.selectPostList(searchVO);

        model.addAttribute("postList", postList);
        model.addAttribute("cnt", count);
        model.addAttribute("pager", pager);
        model.addAttribute("searchVO", searchVO);
        
        return JSP_DIR + "home";
    }

	/***
	 * 게시글 리스트 삭제
	 * @param postNoList
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/deletePostList")
	@ResponseBody
    public Map<String, Object> deletePostList(@RequestParam(defaultValue="")List<String> postNoList) throws Exception{

		Map<String, Object> resultMap = new HashMap<String, Object>();
    	//logger.info("===========");
    	//logger.info(deletePostNo);
    	//logger.info("===========");

		if(postNoList == null || postNoList.size()<0){
			resultMap.put("result", "fail");
		}else{
			postService.deletePosts(postNoList);
			resultMap.put("result", "success");
		}

		return resultMap;
    }

	/***
	 * 게시글 단건 삭제
	 * @param postNo
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/deletePost", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteGET(@RequestParam("num")String postNo) throws Exception{

    	Map<String, Object> resultMap = new HashMap<String, Object>();
		postService.deletePost(postNo);
		resultMap.put("result", "success");

		return resultMap;
    }
    
    // 리다이렉트 방지
    @RequestMapping(value="/doModify", method=RequestMethod.GET) 
    public String doModifyGET() throws Exception{
    	
    	
    	
    	return "redirect:/";
    }
    
    // 게시글 수정페이지로 전환
    @RequestMapping(value="/doModify", method=RequestMethod.POST) 
    public String doModifyPost(@RequestParam("num")String num,@RequestParam("page")int page,
    		@RequestParam(defaultValue="all") String searchOption,
    		@RequestParam(defaultValue="") String keyword, Model model) throws Exception{
    	
    	PostVO vo = postService.selectPostDetail(num);
    	vo.setFileNames(postService.selectFileList(vo.getPostNum()));
    	
    	// 해당 게시글을 읽어들임
    	model.addAttribute("postVO",vo);
    	
    	// 다시 목록으로 갈 때, 페이지
    	model.addAttribute("page",page);
    	model.addAttribute("searchOption",searchOption);
        model.addAttribute("keyword",keyword);
        
    	return JSP_DIR + "modify";
    }
    
    // 리디렉트 방지
    @RequestMapping(value="/modify", method=RequestMethod.GET) 
    public String modifyGET() throws Exception{
    	
    	
    	return "redirect:/";
    }
    
    // 게시글 수정
    @RequestMapping(value="/modify", method=RequestMethod.POST) 
    
    
    
    public String modifyPOST(@RequestParam(defaultValue="")List<String> deleteFileNo ,PostVO post, HttpServletRequest request) throws Exception{
    	
    	logger.info("===============");
    	
    	
    	if(!deleteFileNo.isEmpty()){
    		logger.info("deleteFileNo: "+deleteFileNo);
    		for (String dfn:deleteFileNo){
        		
        		
        		postService.deleteFile(dfn);
        		
        		logger.info(dfn);
        		
        	}
    	}else{
    		logger.info("빈 deleteFileNo: "+deleteFileNo);
    	}
    	//logger.info(request);
    	logger.info("===============");
    	
    	postService.modifyPost(post, request);
    	//service.modifyPost(post);
    	
    	return "redirect:/read?num="+post.getPostNum();
    }
    

    
    // 게시글 상세보기
    @RequestMapping(value="/read", method=RequestMethod.GET)
    public String read(@RequestParam("num") String postNum,
    		@RequestParam(defaultValue="1") int page,
    		@RequestParam(defaultValue="all") String searchOption,
    		@RequestParam(defaultValue="") String keyword, Model model) throws Exception{
    	
    	
    	// 게시글 불러오기
    	PostVO vo = postService.selectPostDetail(postNum);
    	
    	// 첨부파일 세팅
    	vo.setFileNames(postService.selectFileList(postNum));
    	
    	// 조회수 증가시킴
    	postService.viewCntPost(postNum);
    	
    	
    	
    	model.addAttribute("searchOption",searchOption);
        model.addAttribute("keyword",keyword);
    	model.addAttribute("postVO",vo);
    	model.addAttribute("page",page);

    	return JSP_DIR + "read";
    }
    @RequestMapping(value="/downloadFile", method=RequestMethod.GET)
    public String downloadFileGET()throws Exception{
    	
    	return "redirect:/";
    }
    // 파일 다운로드
    @RequestMapping(value="/downloadFile", method=RequestMethod.POST)
    public void downloadFile(@RequestParam("fileNo")String fileNo,HttpServletResponse response)throws Exception{
    	
    	
    	//logger.info("=================");
    	//logger.info(fileNo);
    	//logger.info("=================");
    	
    	
    	Map<String,Object> map = postService.selectFile(fileNo);

    	String storedFileName = (String)map.get("STORED_NAME");
    	String originalFileName = (String)map.get("ORIGINAL_NAME");
    	
    	byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\Users\\vinea\\Desktop\\files\\"+storedFileName));
    	
    	response.setContentType("application/octet-stream");
    	response.setContentLength(fileByte.length);
    	response.setHeader("Content-Disposition", "attachment;fileName=\""+URLEncoder.encode(originalFileName,"UTF-8")+"\";");
    	response.setHeader("Content-Transfer-Encoding","binary");
    	response.getOutputStream().write(fileByte);
    	
    	response.getOutputStream().flush();
    	response.getOutputStream().close();
    }
    
    // 게시글 작성 페이지 이동
    @RequestMapping(value="/write", method=RequestMethod.GET)
    public String writeGET(HttpSession session) throws Exception{
    	
    	UserVO user = (UserVO) session.getAttribute("user");
    	
    	if (user==null) {
    		return "signin";
    	}else {
    		return JSP_DIR + "write";
    	}
    }

    // 게시글 작성
    @RequestMapping(value="/write", method=RequestMethod.POST)
    public String writePOST(PostVO post, HttpSession session, HttpServletRequest request) throws Exception{
		UserVO user = (UserVO) session.getAttribute("user");
		if(user == null){
			return "signin";
		}
		post.setWrtNo(String.valueOf(user.getUserNum()));
    	postService.insertPostInfo(post, request);
    	
    	return "redirect:/";
    }
    

}