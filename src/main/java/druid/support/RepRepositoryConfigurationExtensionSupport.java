package druid.support;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.*;
import org.springframework.data.repository.config.*;

/**
 * Created by Maksym_Bondarenko on 2/11/2016.
 */
public class RepRepositoryConfigurationExtensionSupport extends RepositoryConfigurationExtensionSupport {

    private static final String QUERY_SUPPORT = QueryBackend.class.getName();
    @Override
    protected String getModulePrefix() {
        return "druid";
    }

    @Override
    public String getRepositoryFactoryClassName() {
        return "Cant be defined in Annotation only";
    }

    @Override
    public void registerBeansForRoot(BeanDefinitionRegistry registry,
                                     RepositoryConfigurationSource configurationSource) {
        super.registerBeansForRoot(registry, configurationSource);

//        if(!registry.containsBeanDefinition(QUERY_SUPPORT)){
//            AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.
//
//                    rootBeanDefinition(QUERY_SUPPORT)
//                    .getBeanDefinition();
//
//            registry.registerBeanDefinition(QUERY_SUPPORT,beanDefinition);
//        }
    }
}
