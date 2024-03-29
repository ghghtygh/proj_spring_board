package com.jupo.board.post.web;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import com.jupo.board.post.service.PostService;
import com.jupo.board.post.vo.PostVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
public class PostControllerTest {


    //@Inject
    @Setter(onMethod_ = @Autowired)
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Autowired
    private PostController postController;

    @Autowired
    private PostService postService;

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        log.info("mMvc setup : '{}'", this.mockMvc);
    }

    /***
     * 게시글 목록 조회 테스트
     * @throws Exception
     */
    @Test
    public void selectPostTest() throws Exception {

        // 메인화면 조회
        ModelMap mainModelMap = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andReturn()
                .getModelAndView()
                .getModelMap();

        List<PostVO> postList = (List<PostVO>) mainModelMap.get("postList");

        // 게시글 목록 조회여부
        assertNotNull(postList);

        PostVO postVO = postList.get(0);

        ModelMap detailModelMap = mockMvc.perform(MockMvcRequestBuilders.get("/read?num="+postVO.getPostNo()))
                .andReturn()
                .getModelAndView()
                .getModelMap();

        PostVO detailPostVO = (PostVO) detailModelMap.get("postVO");

        // 파라미터로 넘긴 게시글 번호와 조회한 게시글 번호가 같은지
        assertEquals(postVO.getPostNo(), detailPostVO.getPostNo());
    }

    /***
     * 게시글 작성 테스트
     * @throws Exception
     */
    @Test
    public void insertPostTest() throws Exception {

        

    }

    /***
     * 게시글 수정 테스트
     * @throws Exception
     */
    @Test
    public void updatePostTest() throws Exception {

    }

    /***
     * 게시글 삭제 테스트
     */
    @Test
    public void deletePostTest() throws Exception {

    }
}
