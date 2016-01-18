package br.com.uol.poc.redis.endpoint;

import br.com.uol.poc.redis.service.RedisRepositoryCommunication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vrx_hora on 06/01/16.
 */
@RestController("/test")
public class TestEndPoint {

    private RedisRepositoryCommunication service;

     @Autowired
    public TestEndPoint(RedisRepositoryCommunication service) {
        this.service = service;
    }

    @RequestMapping(path = "/get/{key}" ,method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public String get(@PathVariable("key") String key) {
        return service.get(key);
    }

    @RequestMapping(path = "/set/{key}/{value}" ,method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public String set(@PathVariable("key") String key, @PathVariable("value") String value) {
        service.set(key, value);
        return "OK!";
    }
}
