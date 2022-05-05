package org.jupo.board.post;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jupo.board.post.service.PostService;
import org.jupo.board.post.web.PostController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={
        "file:src/main/resources/spring/spring-security.xml",
        "file:src/main/resources/spring/root-context.xml",
        "file:src/main/resources/spring/appServlet/servlet-context.xml"})
@Log4j2
public class PostTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Inject
    @Setter(onMethod_ = @Autowired)
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Autowired
    private PostController postController;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        logger.info("mMvc setup : '{}'", this.mockMvc);
    }

    @Test
    public void selectPostTest() throws Exception {

        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andReturn()
                .getModelAndView()
                .getModelMap());

    }


}
