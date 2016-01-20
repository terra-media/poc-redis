package br.com.redis.cluster.endpoint;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.redis.cluster.model.Cart;
import br.com.redis.cluster.model.Item;

@RestController
@RequestMapping("/cache")
public class CartEndpoint {
  
  @Autowired
  private Cart cart;
  
  @RequestMapping(value="/add", method=RequestMethod.POST)
  public void add(@RequestBody Item item){
    cart.add(item);
  }
  
  @RequestMapping("/getItem/{id}")
  public Item getItem(@PathVariable("id") Long id){
    return cart.getItem(id);
  }
  
  @RequestMapping("/getItens")
  public List<Item> getItens(){
    return cart.getItens();
  }
  
  @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
  public void delete(@PathVariable("id") Long id) throws IOException{
    cart.delete(new Item(id));
  }
  
  @RequestMapping(value="/update", method=RequestMethod.POST)
  public void update(@RequestBody Item item) throws IOException{
    cart.update(item);
  }

}
