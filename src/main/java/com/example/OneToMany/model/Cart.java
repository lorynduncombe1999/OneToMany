package com.example.OneToMany.model;

import com.example.OneToMany.model.dto.CartDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//Data is a lombok annotation that automatically generates getters/setters/constructors
//Model Classes indicate what attributes should be a part of the class
@Data
@Entity
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    //Cascade type all means that things enacted on list will cary through to item
    //ex: if you delete cart, every item in the cart will be deleted as well
    @OneToMany(cascade = CascadeType.ALL)
    //this will give us the cart id that the item is assigned to
    @JoinColumn(name = "cart_id")
    private List<Item> items = new ArrayList<>();

    public Cart() {

    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItems(Item item){
        items.remove(item);
    }
    //Takes the DTO and transfers it to cart
    public static Cart from(CartDto cartDto){
      Cart cart = new Cart();
      cart.setName(cartDto.getName());
      return cart;
    }
}

