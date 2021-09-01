package com.example.OneToMany.controller;

import com.example.OneToMany.model.Cart;
import com.example.OneToMany.model.dto.CartDto;
import com.example.OneToMany.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
//Request mapping indicates the path to get to the desired method
//This class takes a cart DTO information and transfers it to cart information
@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
// Gathers the Cart DTO input and creates a new Cart and adds it to the cart list. This returns the cartDto
    @PostMapping
    public ResponseEntity<CartDto> addCart(@RequestBody final CartDto cartDto){
Cart cart = cartService.addCart(Cart.from(cartDto));
return new ResponseEntity<>(CartDto.from(cart), HttpStatus.OK);
    }
//Returns the list of carts
    @GetMapping
    public ResponseEntity<List<CartDto>> getCarts(){
        List<Cart> carts = cartService.getCarts();
        List<CartDto> cartsDto = carts.stream().map(CartDto::from).collect(Collectors.toList());
    return new ResponseEntity<>(cartsDto,HttpStatus.OK);
    }
//returns a specified cart by using the id
    @GetMapping(value = "{id}")
    public ResponseEntity<CartDto> getCart(@PathVariable final Long id){
        Cart cart = cartService.getCart(id);
        return new ResponseEntity<>(CartDto.from(cart), HttpStatus.OK);
    }
//Deletes a cart using cart Id
    @DeleteMapping(value = "{id}")
    public ResponseEntity<CartDto> deleteCart(@PathVariable final Long id){
        Cart cart = cartService.deleteCart(id);
        return new ResponseEntity<>(CartDto.from(cart), HttpStatus.OK);
    }

//Updates a cart by using the cart id
    @PutMapping(value = "{id}")
    public ResponseEntity<CartDto> editCart(@PathVariable final Long id, @RequestBody final CartDto cartDto){
        Cart cart = cartService.editCart(id, Cart.from(cartDto));
        return new ResponseEntity<>(CartDto.from(cart), HttpStatus.OK);
    }
//This posts refers to adding a new item to the cart
    @PostMapping(value = "{cartId}/items/{itemId}/add")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable final Long cartId, @PathVariable final Long itemId){
        Cart cart = cartService.addItemToCart(cartId,itemId);
        return new ResponseEntity<>(CartDto.from(cart), HttpStatus.OK);

    }
    //This delete refers to deleting a new item to the cart
    @DeleteMapping(value = "{cartId}/items/{itemId}/remove")
    public ResponseEntity<CartDto> removeItemToCart(@PathVariable final Long cartId, @PathVariable final Long itemId){
        Cart cart = cartService.removeItemFromCart(cartId,itemId);
        return new ResponseEntity<>(CartDto.from(cart), HttpStatus.OK);

    }
}
