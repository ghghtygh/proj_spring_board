package com.jupo.board.post.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.jupo.board.post.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostDAO{

    /***
     * 게시글 리스트를 조회한다.
     * @param vo
     * @return
     * @throws Exception
     */
    List<PostVO> selectPostList(PostVO vo) throws Exception;

    void insertPostInfo(PostVO vo) throws Exception;

    void insertFile(Map<String, Object> map) throws Exception;

    int selectPostListCnt(PostVO searchVO) throws Exception;

    int countFile(String postNo) throws Exception;

    void viewCntPost(String postNo) throws Exception;

    void modifyPost(PostVO vo) throws Exception;

    void deletePost(String postNo) throws Exception;

    Optional<PostVO> selectPostDetail(String postNo) throws Exception;

    List<Map<String,Object>> selectFileList(String postNo) throws Exception;

    Map<String,Object> selectFile(String fileNo) throws Exception;

    void deleteFiles(String postNo) throws Exception;

    void deleteFile(String fileNo) throws Exception;

}