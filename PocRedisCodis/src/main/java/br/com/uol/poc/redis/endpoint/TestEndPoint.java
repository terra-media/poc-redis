package br.com.uol.poc.redis.endpoint;

import br.com.uol.poc.redis.model.Book;
import br.com.uol.poc.redis.repository.BookRepo;
import br.com.uol.poc.redis.service.RedisRepositoryCommunication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vrx_hora on 06/01/16.
 */
@RestController("/test")
public class TestEndPoint {

    private BookRepo bookRepo;

    @Autowired
    public TestEndPoint(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @RequestMapping(path = "/get/{key}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public Book get(@PathVariable("key") String key) {
        return bookRepo.get(key);
    }

    @RequestMapping(path = "/set" ,method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String set(@RequestBody Book book) {
        bookRepo.add(book);
        return "OK!";
    }

    @RequestMapping(path = "/up/{key}" ,method = RequestMethod.PUT, produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String up(@PathVariable("key") String key, @RequestBody Book book) {
        bookRepo.update(key, book);
        return "OK!";
    }

    @RequestMapping(path = "/del/{key}" ,method = RequestMethod.DELETE, produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public String del(@PathVariable("key") String key) {
        bookRepo.delete(key);
        return "OK!";
    }

    @RequestMapping(path = "/get/all" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Book> getAll() {
        return bookRepo.getAll();
    }
}