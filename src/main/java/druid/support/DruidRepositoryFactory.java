package druid.support;

import druid.annotations.DruidQuery;
import druid.query.template.*;
import druid.repository.DefaultRepository;
import org.slf4j.*;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.*;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.*;
import org.springframework.util.*;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Factory that actually responsible for creation of Repo or really backs Repo on behind.
 *
 * Created by Maksym_Bondarenko on 2/11/2016.
 */
public class DruidRepositoryFactory extends RepositoryFactorySupport implements QueryLookupStrategy {

    private static final Logger log = LoggerFactory.getLogger(DruidRepositoryFactory.class);
    // may get from ctx by narrower types
    private QueryBackend backend;

    @Override
    protected void validate(RepositoryMetadata repositoryMetadata) {
        log.info("validate it: {}", repositoryMetadata);
    }

    @Override
    protected QueryLookupStrategy getQueryLookupStrategy(QueryLookupStrategy.Key key) {
        return this;
    }

    @Override
    public <T, ID extends Serializable> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
        throw new UnsupportedOperationException("getEntityInfo not supported for this type of Repo");
    }

    @Override
    protected Object getTargetRepository(RepositoryInformation md) {
        Assert.notNull(md);
        Class<?> domainType = md.getDomainType();
        DefaultRepository df = new DefaultRepository(domainType);
        df.setBackend(backend);
        return df;
    }

    @Override
    public <T> T getRepository(Class<T> repositoryInterface) {
        T repository = super.getRepository(repositoryInterface);


        return repository;
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return DefaultRepository.class;
    }

    public void setBackend(QueryBackend backend) {
        this.backend = backend;
    }


    private String getDataSource(DruidQuery methodAnn, Method m, RepositoryMetadata md) {
        String dataSource = methodAnn.dataSource();
        if (StringUtils.isEmpty(dataSource)) {
            DruidQuery classAnn = AnnotationUtils.findAnnotation(md.getRepositoryInterface(), DruidQuery.class);
            if (classAnn == null) {
                throw new IllegalArgumentException("For empty dataSource on method " + m.getName()
                                                           + " must be DruidQuery annotation with dataSource set");
            }
            dataSource = classAnn.dataSource();
        }
        if (StringUtils.isEmpty(dataSource)) {
            throw new IllegalArgumentException("Empty dataSource name");
        }
        return dataSource;
    }

    @Override
    public RepositoryQuery resolveQuery(Method method,
                                        RepositoryMetadata metadata,
                                        ProjectionFactory projectionFactory,
                                        NamedQueries namedQueries) {
        DruidQuery annotation = AnnotationUtils.findAnnotation(method, DruidQuery.class);
        if (annotation != null) {
            TemplatePartTree tree = new TemplatePartTree(method.getName());
            DruidQuery methodAnn = AnnotationUtils.findAnnotation(method, DruidQuery.class);
            //get data source from parts
            String dataSource = tree.getDataSource();
            if (StringUtils.isEmpty(dataSource)) {
                dataSource = getDataSource(methodAnn, method, metadata);
            }
            String templateName = methodAnn.templateName();
            if (StringUtils.isEmpty(templateName)) {
                templateName = method.getName();
            }

            return StringUtils.isEmpty(dataSource)
                    ? new TemplateQuery(backend, tree, templateName, method.getReturnType())
                    : new TemplateQuery(backend, tree, templateName, dataSource, method.getReturnType());
        }
        throw new IllegalArgumentException("Method must be annotated with DruidQuery annotation");
    }
}
