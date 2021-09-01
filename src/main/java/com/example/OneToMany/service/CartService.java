package com.example.OneToMany.service;

import com.example.OneToMany.exception.CartNotFoundException;
import com.example.OneToMany.exception.ItemAlreadyAssignedToCart;
import com.example.OneToMany.model.Cart;
import com.example.OneToMany.model.Item;
import com.example.OneToMany.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
//Service classes contains the business logic behind the controller

/*
@Transactional method is called from client code, the TransactionInterceptor
gets invoked first from the proxy object, which begins the transaction and
eventually invokes the method on the target bean. When the invocation finishes,
the TransactionInterceptor commits/rolls back the transaction accordingly.
 */
@Service
public class CartService {

    //Cart needs access to cart and items because items need carts, however carts can be empty
    private final CartRepository cartRepository;
    private final ItemService itemService;

    @Autowired
    public CartService(CartRepository cartRepository, ItemService itemService) {
        this.cartRepository = cartRepository;
        this.itemService = itemService;
    }

    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }
//returns a list of carts that have been created
    public List<Cart> getCarts(){
        return StreamSupport.stream(cartRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }
// returns single cart by using cart id
    public Cart getCart(Long id){
        return cartRepository.findById(id).orElseThrow(()-> new CartNotFoundException(id));
    }
    public Cart deleteCart(Long id){
        Cart cart = getCart(id);
        cartRepository.delete(cart);
        return cart;
    }
    //Allows user to update cart information, name only
    @Transactional
    public Cart editCart(Long id, Cart cart){
        Cart cartToEdit = getCart(id);
        cartToEdit.setName(cart.getName());
        return cartToEdit;
    }
    //adds item to a specific cart
    @Transactional
    public Cart addItemToCart(Long cartId, Long itemId){
        Cart cart = getCart(cartId);
        Item item = itemService.getItem(itemId);
        //checks to ensure that the item is not already assigned to a cart
        if(Objects.nonNull(item.getCart())){
            throw new ItemAlreadyAssignedToCart(itemId,item.getCart().getId());
        }
        cart.addItem(item);
        return cart;
    }
    //removes items from a specific cart
    @Transactional
    public Cart removeItemFromCart(Long cartId, Long itemId){
        Cart cart = getCart(cartId);
        Item item = itemService.getItem(itemId);
        cart.removeItems(item);
        return cart;
    }
}
