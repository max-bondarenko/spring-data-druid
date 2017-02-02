package druid.support;

import org.springframework.http.HttpMethod;

import java.util.Map;
import java.util.Set;

/**
 *
 * Glue druid-data with external template source & rest calling mechanism.
 * <br></br>
 * Created by Maksym_Bondarenko on 2/11/2016.
 */
public interface QueryBackend {
    /**
     * Base url for querying.
     *
     * @return
     */
    String getBaseUrl();

    void validateTemplate(String name, Set<String> placeholders);

    <T> T executeByUrl(String url, HttpMethod method, Class<T> responseType, Object[] parameters);

    <T> T execute(String templateName, Map<String, String> placeholders, Class<T> responseType);
}
