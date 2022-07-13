package com.jupo.board.post.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.jupo.board.post.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jupo.board.common.util.FileUtils;
import com.jupo.board.post.vo.PostVO;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDAO dao;

	@Autowired
	@Resource(name = "fileUtils")
	private FileUtils fileUtils;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void insertPostInfo(PostVO vo, HttpServletRequest request) throws Exception {

		vo.setTitle(checkTitle(vo.getTitle()));

		dao.insertPostInfo(vo);

		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(vo, request);

		for (int i = 0, size = list.size(); i < size; i++) {
			dao.insertFile(list.get(i));
		}

	}

	@Override
	public int selectPostListCnt(PostVO searchVO) throws Exception {

		return dao.selectPostListCnt(searchVO);
	}

	@Override
	public List<PostVO> selectPostList(PostVO searchVO) throws Exception {

		List<PostVO> list = dao.selectPostList(searchVO);

		return list;
	}

	@Override
	public void viewCntPost(String postNo) throws Exception {

		dao.viewCntPost(postNo);

	}

	@Override
	public void modifyPost(PostVO vo, HttpServletRequest request) throws Exception {

		vo.setTitle(checkTitle(vo.getTitle()));

		dao.modifyPost(vo);

		vo = selectPostDetail(vo.getPostNo());

		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(vo, request);

		for (int i = 0, size = list.size(); i < size; i++) {

			logger.info(String.valueOf(list.get(i)));

			dao.insertFile(list.get(i));
		}
	}

	@Override
	public void deletePost(String postNo) throws Exception {

		dao.deletePost(postNo);
		dao.deleteFiles(postNo);

	}

	@Override
	public void deletePosts(List<String> postNoList) throws Exception {

		for (int i = 0, size = postNoList.size(); i < size; i++) {

			String postNo = postNoList.get(i);

			dao.deletePost(postNo);

			dao.deleteFiles(postNo);
		}

	}

	@Override
	public PostVO selectPostDetail(String postNo) throws Exception {
		return dao.selectPostDetail(postNo)
				.orElseThrow(() -> new Exception("post not exist"));
	}

	@Override
	public List<Map<String, Object>> selectFileList(String postNo) throws Exception {

		return dao.selectFileList(postNo);
	}

	@Override
	public Map<String, Object> selectFile(String fileNo) throws Exception {

		return dao.selectFile(fileNo);
	}

	@Override
	public void deleteFile(String fileNo) throws Exception {

		dao.deleteFile(fileNo);
	}

	public String checkTitle(String title) throws Exception {
		if (title.equals("")) {
			return "제목 없음";
		}

		title = title.replace("<", "&lt;");
		title = title.replace(">", "&gt;");
		title = title.replace("  ", "&nbsp;&nbsp");

		return title;
	}

}
