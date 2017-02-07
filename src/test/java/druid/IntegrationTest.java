package druid;

import app.*;
import druid.repository.DefaultRepository;
import druid.support.*;
import org.junit.Test;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.beans.factory.support.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.lang.reflect.Proxy;
import java.sql.Time;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Maksym_Bondarenko on 2/2/2017.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
public class IntegrationTest {

    @Test
    public void justContextStart() throws Exception {
        new AnnotationConfigApplicationContext(Config.class);
    }

    @Test(expected = NullPointerException.class)
    public void repositoryStarts() throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        TestRepo bean = ctx.getBean(TestRepo.class);
        assertNotNull(bean);
        boolean proxyClass = Proxy.isProxyClass(bean.getClass());
        assertTrue(proxyClass);
        bean.byId(Time.valueOf("00:01:00"));
    }

    @Test
    public void definition() throws Exception {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(DruidTemplateRegistry.class)
                .setScope("singleton")
                .getBeanDefinition();


        beanFactory.registerBeanDefinition("q",beanDefinition);

        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(beanFactory);

        ctx.refresh();
        QueryBackend bean = ctx.getBean(QueryBackend.class);
        assertNotNull(bean);

    }
}
