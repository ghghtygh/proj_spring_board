package org.jupo.myapp;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:../../../../../main/resources/spring/**/root-context.xml"})
public class MySQLConnectionTest {
    
    @Inject
    private DataSource ds;
 
    @Test
    public void testConnection() throws Exception {
 
        Connection con = ds.getConnection();
 
        System.out.println("\n >>>>>>>>>> Connection 출력 : " + con + "\n");
 
    }
    
}
