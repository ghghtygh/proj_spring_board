package com.jupo.board.post.service;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={
        "file:src/main/resources/spring/context-*.xml",
        "file:src/main/resources/spring/root-context.xml",
        "file:src/main/resources/spring/appServlet/servlet-context.xml"
})
@Log4j2
public class PostServiceTest {

    @Test
    public void unitTest() {
        assertEquals("1", "1");
        assertNotNull(new ArrayList<String>());
    }

}
