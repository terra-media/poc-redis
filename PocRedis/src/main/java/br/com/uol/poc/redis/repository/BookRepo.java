package br.com.uol.poc.redis.repository;

import br.com.uol.poc.redis.model.Book;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrx_hora on 20/01/16.
 */
@Component
@CacheConfig(cacheNames = "books")
public class BookRepo {

    List<Book> books = new ArrayList<>();
    Logger logger = Logger.getLogger(this.getClass());

    @Cacheable(key = "'all'")
    public List<Book> getAll() {
        logger.info(".........getAll()");
        return books;
    }

    @Cacheable(key="'book:'.concat(#id)")
    public Book get(Long id) {
        logger.info(".........get("+id+")");
        return books.stream().filter(current -> id.equals(current.getId())).findAny().orElse(null);
    }

    @CacheEvict(key = "'all'")
    public boolean add(Book book) {
        logger.info(".........add("+book+")");
        return books.add(book);
    }

    public boolean update(Long id, Book book) {
        logger.info(".........update("+id+")");
        return delete(id) && books.add(book);
    }

    @Caching(evict = {
        @CacheEvict(key = "'all'"),
        @CacheEvict(key = "'book:'.concat(#id)")
    })
    public boolean delete(Long id) {
        logger.info(".........delete("+id+")");
        return books.removeIf(current -> id.equals(current.getId()));
    }
}