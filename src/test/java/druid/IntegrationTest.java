package druid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Maksym_Bondarenko on 2/2/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntegrationTest.TestConfiguration.class)
public class IntegrationTest {


    @Configuration
    public static class TestConfiguration{

    }

    @Test
    public void justContextStart() throws Exception {


    }
}
