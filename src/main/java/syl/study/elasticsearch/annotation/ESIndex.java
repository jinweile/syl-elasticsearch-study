package syl.study.elasticsearch.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Mtime on 2016/10/17.
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface ESIndex {

    /**
     * ElasticSearch index 的名称
     * @return
     */
    String value();



}
