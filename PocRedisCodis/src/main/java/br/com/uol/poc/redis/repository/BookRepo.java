package br.com.uol.poc.redis.repository;

import br.com.uol.poc.redis.model.Book;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable()
    public List<Book> getAll() {
        logger.info(".........getAll()");
        return books;
    }

    @CacheEvict
    public boolean add(Book book) {
        logger.info(".........add()");
        return books.add(book);
    }

    @CacheEvict
    public boolean update(String id, Book book) {
        logger.info(".........update()");
        return delete(id) && books.add(book);
    }

    @CachePut(key="#id")
    public Book get(String id) {
        logger.info(".........get()");
        return books.stream().filter(current -> id.equals(current.getId())).findFirst().get();
    }

    @CacheEvict
    public boolean delete(String id) {
        logger.info(".........delete()");
        return books.removeIf(current -> id.equals(current.getId()));
    }
}