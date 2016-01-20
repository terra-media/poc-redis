package br.com.redis.cluster.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@CacheConfig(cacheNames=Cart.CACHE_NAME)
public class Cart implements Serializable{

  private static final long serialVersionUID = 1L;
  private static final Logger log = LoggerFactory.getLogger(Cart.class);
  public static final String CACHE_NAME = "CRUD_CACHE";
 
  private final List<Item> itens = new ArrayList<Item>();
  
  @CacheEvict(allEntries=true)
  public void add(final Item item){
    this.itens.add(item);
    log.info("Adicionado "+item.getName()+" no carrinho!");
  }
  
  @CacheEvict(allEntries=true)
  public void delete(final Item item){
    final Item itemRemove = getItem(item.getId());
    this.itens.remove(itemRemove);
    log.info("Deletado "+itemRemove.getName()+" do carrinho!");
  }
  
  @CacheEvict(allEntries=true)
  public boolean update(final Item item){
    final Item itemLoad = getItem(item.getId());
    if(itemLoad != null){
      itens.remove(itemLoad);
      itens.add(item);
      log.info("Item "+itemLoad.getName()+" atualizado para "+item.getName()+" no carrinho!");
      return true;
    }
    return false;
  }
  
  @Cacheable(key="#idItem")
  public Item getItem(final Long idItem){
    for (Item item : itens) {
      if(item.getId().equals(idItem)){
        log.info("Obtido item "+item.getName()+" do carrinho!");
        return item;
      }
    }
    return null;
  }
  
  @Cacheable(value=CACHE_NAME)
  public List<Item> getItens(){
    log.info("Obtendo todos os itens do carrinho!");
    return this.itens;
  }
  
}