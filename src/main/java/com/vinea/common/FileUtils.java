package com.vinea.common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.vinea.dto.PostVO;
import com.vinea.myapp.PostController;

@Component("fileUtils")
public class FileUtils {
	
	 private final static Logger logger = Logger.getLogger(FileUtils.class);

	private static final String filePath="C:\\Users\\vinea\\Desktop\\files\\";
	
	public List<Map<String,Object>> parseInsertFileInfo(PostVO vo,HttpServletRequest request )throws Exception{
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> listMap = null;
		
		int postNum = vo.getPostNum();
		
		File file = new File(filePath);
		
		if(file.exists()==false){
			file.mkdirs();
		}

		int count = 0;
		while(iterator.hasNext()){
			
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			
			if(multipartFile.isEmpty()==false){
				
				
				
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName=CommonUtils.getRandomString()+originalFileExtension;
				
				file = new File(filePath+storedFileName);
				multipartFile.transferTo(file);
				
				listMap = new HashMap<String, Object>();
				listMap.put("postNum", postNum);
				listMap.put("originalFileName", originalFileName);
				listMap.put("storedFileName", storedFileName);
				listMap.put("fileSize", multipartFile.getSize());
				list.add(listMap);
				
				logger.info("파일파일파일파일파일파일파일");
				logger.info(++count);
				logger.info(originalFileName);
				logger.info("파일파일파일파일파일파일파일");
			}
		}
		
		
		return list;
	}
	
}
