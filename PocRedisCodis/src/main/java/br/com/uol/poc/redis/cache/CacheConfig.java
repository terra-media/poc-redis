package br.com.uol.poc.redis.cache;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by vrx_hora on 20/01/16.
 */
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    @Override
    public KeyGenerator keyGenerator() {
        return (o, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName()+":");
            sb.append(method.getName()+"(");
            Arrays.asList(params)
                    .stream()
                    .forEach(param -> sb.append(param.getClass().getSimpleName()+":"+param.toString()+","));
            if(sb.reverse().charAt(0) == ','){
                sb.deleteCharAt(0);
            }
            return sb.reverse().append(")").toString();
        };
    }
}
