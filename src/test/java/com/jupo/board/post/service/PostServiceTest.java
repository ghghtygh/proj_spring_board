package com.jupo.board.post.service;

import com.jupo.board.ServiceTest;
import com.jupo.board.common.util.FileUtils;
import com.jupo.board.post.service.impl.PostDAO;
import com.jupo.board.post.service.impl.PostServiceImpl;
import com.jupo.board.post.vo.PostVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@Log4j2
@DisplayName("Post Service Test")
public class PostServiceTest extends ServiceTest {

    @InjectMocks
    PostServiceImpl postService;

    @Mock
    PostDAO postDAO;

    @Mock
    FileUtils fileUtils;

    private PostVO postVO;

    @BeforeEach
    public void createPost(){
        postVO = PostVO.builder()
                .boardNo("1")
                .postNo("1")
                .title("제목테스트")
                .content("내용테스트")
                .loginId("1")
                .nickname("닉네임테스트")
                .viewCnt("0")
                .delYn("N")
                .build();
    }


    @Test
    @DisplayName("")
    public void unitTest() {
        log.info(postVO.toString());
    }

}
