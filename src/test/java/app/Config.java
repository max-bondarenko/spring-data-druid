package app;

import druid.annotations.EnableDruidRepositories;
import druid.support.QueryBackend;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;

import java.util.*;

/**
 * Created by Maksym_Bondarenko on 2/2/2017.
 */
@Configuration
@EnableDruidRepositories("app")
public class Config {

    @Bean
    public QueryBackend getQueryBackand(){
        return  new QueryBackend() {
            @Override
            public String getBaseUrl() {
                return null;
            }

            @Override
            public void validateTemplate(String name, Set<String> placeholders) {

            }

            @Override
            public <T> T executeByUrl(String url, HttpMethod method, Class<T> responseType, Object[] parameters) {
                throw new NullPointerException("NO implementation");
            }

            @Override
            public <T> T execute(String templateName, Map<String, String> placeholders, Class<T> responseType) {
                return null;
            }
        };
    }

}
