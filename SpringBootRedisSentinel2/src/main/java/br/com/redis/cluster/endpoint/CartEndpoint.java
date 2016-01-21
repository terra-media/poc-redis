package br.com.redis.cluster.endpoint;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.redis.cluster.model.Cart;
import br.com.redis.cluster.model.Item;

@RestController
@RequestMapping("/cache/cart")
public class CartEndpoint {
  
  private static final String OK = "OK";
  
  @Autowired
  private Cart cart;
  
  @RequestMapping(value="/add/{id}/{name}")
  public String add(@PathVariable("id") Long id, @PathVariable("name") String name){
    cart.add(new Item(id, name));
    return OK;
  }
  
  @RequestMapping(value="/get/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
  public Item getItem(@PathVariable("id") Long id){
    return cart.getItem(id);
  }
  
  @RequestMapping("/getAll")
  public List<Item> getItens(){
    return cart.getItens();
  }
  
  @RequestMapping(value="/delete/{id}")
  public String delete(@PathVariable("id") Long id) throws IOException{
    cart.delete(new Item(id));
    return OK;
  }
  
  @RequestMapping(value="/update/{id}/{name}")
  public String update(@PathVariable("id") Long id, @PathVariable("name") String name) throws IOException{
    cart.update(new Item(id, name));
    return OK;
  }

}
