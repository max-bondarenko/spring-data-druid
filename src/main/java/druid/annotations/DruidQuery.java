package druid.annotations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;
import org.springframework.data.annotation.QueryAnnotation;

import java.lang.annotation.*;

/**
 * Represents repository or method to access Druid using query template.
 * <br/>
 * DataSource value should be set.
 * If annotated method has no dataSource value than DataSource value from Repository annotation wil be used.
 * If not presented method name will be used as dataSource name;
 *
 *
 *
 *
 * Created by Maksym_Bondarenko on 2/12/2016.
 */
@QueryAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@ComponentScan("druid.support")
public @interface DruidQuery {

    @AliasFor("templateName") String value() default "";

    @AliasFor("value") String templateName() default "";

    String dataSource() default "";
}
