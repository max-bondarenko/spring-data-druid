package druid.support;

import com.google.common.collect.Sets;
import com.google.common.io.CharStreams;
import org.slf4j.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by Maksym_Bondarenko on 2/8/2016.
 */

@Component
public class DruidTemplateRegistry implements QueryBackend, InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(DruidTemplateRegistry.class);
    @Value("${local.url}") //check later: change to switch mechanism
    private String localUrl;
    @Autowired
    private ApplicationContext ctx;
    @Autowired(required = false)
    private RestTemplate template = new RestTemplate();

    private PropertyPlaceholderHelper pc = new PropertyPlaceholderHelper("${", "}");

    private Map<String, String> cache = new ConcurrentReferenceHashMap<>();

    /**
     * find all templates in classpath and add to cache
     *
     * @throws IOException
     */
    @Override
    public void afterPropertiesSet() throws IOException {
        Resource[] resources = ctx.getResources("classpath*:**/*.tpl");
        log.debug("Registry got {} resources", resources == null ? 0 : resources.length);
        if (resources != null) {
            for (Resource resource : resources) {
                process(resource);
            }
        }
    }

    /**
     * Are all template plaseholders matches given.
     *
     * @param templateName
     * @param placeholders
     */
    @Override
    public void validateTemplate(String templateName, final Set<String> placeholders) {
        Assert.notNull(templateName, "null template name");
        Assert.notNull(placeholders, "null template placeholders name");

        final HashSet<String> called = new HashSet<>();
        String tpl = cache.get(templateName);
        if (tpl == null) {
            throw new IllegalArgumentException("There is no template with name: " + templateName);
        }
        // add all called placeholder name
        pc.replacePlaceholders(tpl, new PropertyPlaceholderHelper.PlaceholderResolver() {
            @Override
            public String resolvePlaceholder(String placeholderName) {
                called.add(placeholderName);
                return "";
            }
        });
        //check is all need presents in given
        Sets.SetView<String> difference = Sets.difference(called, placeholders);
        if (!difference.isEmpty()) {
            throw new IllegalArgumentException("template has unresolved plaseholders:" + difference.toString());
        }
    }

    /**
     * Returns template by it name.
     * <br/>
     * Template is &lt;template_name&gtl.tpl file in resources.
     *
     * @param templateName template file name
     * @param prop         map of placeholders
     * @return ready to get JSON with resolved names
     */
    public String getTemplate(String templateName, final Map<String, String> prop) {
        String o = cache.get(templateName);
        if (o == null) {
            throw new IllegalArgumentException("No template with name " + templateName);
        }
        return pc.replacePlaceholders(o, new PropertyPlaceholderHelper.PlaceholderResolver() {
            @Override
            public String resolvePlaceholder(String placeholderName) {
                return prop.get(placeholderName);
            }
        });
    }

    private void process(Resource resource) throws IOException {
        String filename = resource.getFilename();
        String key = filename.substring(0, filename.lastIndexOf('.'));
        if (log.isTraceEnabled()) {
            log.trace("Process {} ,put as {} cache entry.", filename, key);
        }
        cache.put(key,
                  CharStreams.toString(new InputStreamReader(resource.getInputStream(), Charset.forName("UTF-8")))); //todo change to remove Guava
    }

    @Override
    public String getBaseUrl() {
        return localUrl;
    }

    @Override
    public <T> T executeByUrl(String url, HttpMethod method, Class<T> responseType, Object[] parameters) {
//        return template.exchange(getBaseUrl(),method, responseType);
        return null;
    }

    @Override
    public <T> T execute(String templateName, final Map<String, String> placeholders, Class<T> responseType) {
        String tpl = cache.get(templateName);
        if (tpl == null) {
            throw new IllegalArgumentException("There is no template with name: " + templateName);
        }
        String processedTemplate = pc.replacePlaceholders(tpl, new PropertyPlaceholderHelper.PlaceholderResolver() {
            @Override
            public String resolvePlaceholder(String placeholderName) {
                return placeholders.get(placeholderName);
            }
        });
        return template.postForObject(getBaseUrl(), processedTemplate, responseType);
    }

}
