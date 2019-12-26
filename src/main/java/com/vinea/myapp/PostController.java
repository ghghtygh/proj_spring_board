package com.vinea.myapp;


import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vinea.dto.PostVO;
import com.vinea.dto.UserVO;
import com.vinea.service.PostPager;
import com.vinea.service.PostService;


@Controller
public class PostController {
    
    
    @Inject
    private PostService service;
    
    private final static Logger logger = Logger.getLogger(PostController.class);
    
    
    
    @RequestMapping(value = "/")
    public String home(@RequestParam(defaultValue="1") int page,
    		@RequestParam(defaultValue="all") String searchOption,
    		@RequestParam(defaultValue="") String keyword, Model model) throws Exception{
 
    	// 전체 포스트 개수 (searchOption: 검색옵션, keyword: 검색어)
    	int count = service.countPost(searchOption,keyword);
        
    	// 페이징 위한 객체 (count: 전체포스트개수, page: 요청페이지)
    	PostPager pager = new PostPager(count,page);
    	
    	// 제일 상위 게시물 번호
    	int start = pager.getStartIndex();
    	
    	// 게시물 개수
    	int pagesize = pager.getPageSize();
    	
        List<PostVO> postList = service.listPost(start,pagesize,searchOption,keyword);
        
        
    	//List<PostVO> postList = service.selectPost();
    	
    	
        model.addAttribute("postList", postList);
        model.addAttribute("cnt",count);
        model.addAttribute("pager", pager);
        model.addAttribute("searchOption",searchOption);
        model.addAttribute("keyword",keyword);
        
        return "home";
    }
    
    // 리다이렉트 방지
    @RequestMapping(value="/doModify", method=RequestMethod.GET) 
    public String doModifyGET() throws Exception{
    	
    	
    	
    	return "redirect:/";
    }
    
    // 게시글 수정페이지로 전환
    @RequestMapping(value="/doModify", method=RequestMethod.POST) 
    public String doModifyPost(@RequestParam("num")int num,@RequestParam("page")int page,
    		@RequestParam(defaultValue="all") String searchOption,
    		@RequestParam(defaultValue="") String keyword, Model model) throws Exception{
    	
    	PostVO vo = service.read(num);
    	vo.setFileNames(service.selectFileList(vo.getPostNum()));
    	
    	// 해당 게시글을 읽어들임
    	model.addAttribute("postVO",vo);
    	
    	// 다시 목록으로 갈 때, 페이지
    	model.addAttribute("page",page);
    	model.addAttribute("searchOption",searchOption);
        model.addAttribute("keyword",keyword);
    	return "modify";
    }
    
    // 리디렉트 방지
    @RequestMapping(value="/modify", method=RequestMethod.GET) 
    public String modifyGET() throws Exception{
    	
    	
    	return "redirect:/";
    }
    
    // 게시글 수정
    @RequestMapping(value="/modify", method=RequestMethod.POST) 
    public String modifyPOST(PostVO post) throws Exception{
    	
    	service.modifyPost(post);
    	
    	return "redirect:/read?num="+post.getPostNum();
    }
    
    // 게시글 삭제
    @RequestMapping(value="/delete", method=RequestMethod.POST) 
    public String deleteGET(@RequestParam("num")int num) throws Exception{
    	
    	service.deletePost(num);
    	
    	return "redirect:/";
    }
    
    // 게시글 상세보기
    @RequestMapping(value="/read", method=RequestMethod.GET)
    public void read(@RequestParam("num")int postNum,
    		@RequestParam(defaultValue="1") int page,
    		@RequestParam(defaultValue="all") String searchOption,
    		@RequestParam(defaultValue="") String keyword, Model model) throws Exception{
    	
    	service.viewCntPost(postNum);
    	PostVO vo = service.read(postNum);
    	vo.setFileNames(service.selectFileList(postNum));
    	
    	model.addAttribute("searchOption",searchOption);
        model.addAttribute("keyword",keyword);
    	model.addAttribute("postVO",vo);
    	model.addAttribute("page",page);
    	
    }
    
    // 파일 다운로드
    @RequestMapping(value="/downloadFile", method=RequestMethod.POST)
    public void downloadFile(@RequestParam("postNum")int postNum,HttpServletResponse response)throws Exception{
    	Map<String,Object> map = service.selectFile(postNum);
    	logger.info("=================");
    	logger.info(map.values());
    	logger.info("=================");
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
    		return "write";
    	}
    }
    
    // 게시글 작성
    @RequestMapping(value="/write", method=RequestMethod.POST)
    public String writePOST(PostVO post, HttpServletRequest request) throws Exception{
    	
    	service.create(post, request);
    	
    	return "redirect:/";
    }
    
        
    //이미지 업로드
    @RequestMapping(value="/image", method=RequestMethod.POST)
    public void imgPOST(@RequestParam("file") MultipartFile file) throws Exception{
    	
    	logger.info("imageUploadimageUploadimageUploadimageUploadimageUpload");
    	logger.info("imageUploadimageUploadimageUploadimageUploadimageUpload");
    	logger.info("imageUploadimageUploadimageUploadimageUploadimageUpload");
    	logger.info("imageUploadimageUploadimageUploadimageUploadimageUpload");
    	
    	
    }

}