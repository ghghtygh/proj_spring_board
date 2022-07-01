package com.jupo.board.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.jupo.board.post.vo.PostVO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtils")
public class FileUtils {

	private static final String filePath="C:\\Users\\vinea\\Desktop\\files\\";
	
	public List<Map<String,Object>> parseInsertFileInfo(PostVO vo, HttpServletRequest request )throws Exception{
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		// 파일 정보 저장할 리스트
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		// 파일 정보
		Map<String,Object> listMap = null;
		
		String postNum = vo.getPostNum();
		
		File file = new File(filePath);
		
		// 디렉토리 없으면 새로생성
		if(file.exists()==false){
			file.mkdirs();
		}

		while(iterator.hasNext()){
			
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			
			if(multipartFile.isEmpty()==false){
				/*
				 * postNum				게시글 번호
				 * originalFileName		원본 파일이름
				 * storedFileName		저장된 파일이름
				 * fileSize				파일 크기
				 */
				
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName= CommonUtils.getRandomString()+originalFileExtension;
				
				file = new File(filePath+storedFileName);
				multipartFile.transferTo(file);
				
				listMap = new HashMap<String, Object>();
				listMap.put("postNum", postNum);
				listMap.put("originalFileName", originalFileName);
				listMap.put("storedFileName", storedFileName);
				listMap.put("fileSize", multipartFile.getSize());
				list.add(listMap);
			}
		}
		return list;
	}
	
}
