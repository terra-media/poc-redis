package br.com.uol.poc.redis.endpoint;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class LoginEndpoint {
  
    @RequestMapping(value="/", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public String index(HttpServletRequest request) {
      Principal userPrincipal = request.getUserPrincipal();
        return "Usuario Autenticado: " + userPrincipal.getName();
    }
    
    
    @RequestMapping(value="/session/add/{key}/{value}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public String add(@PathVariable("key") String key, @PathVariable("value") String value, HttpServletRequest request) {
        request.getSession().setAttribute(key, value);
        return "Chave "+key+" adicionada na sessão!";
    }
    
    @RequestMapping(value="/session/get/{key}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public String get(@PathVariable("key") String key, HttpServletRequest request) {
        return "Chave "+key+" com valor = "+ request.getSession().getAttribute(key);
    }
    
    @RequestMapping(value="/session/clearAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public String clearAll(HttpServletRequest request) {
        request.getSession().invalidate();
        return "Sessão foi limpa";
    }
}
