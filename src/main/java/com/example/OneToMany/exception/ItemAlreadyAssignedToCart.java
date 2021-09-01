package com.example.OneToMany.exception;

import java.text.MessageFormat;

public class ItemAlreadyAssignedToCart extends RuntimeException{
    public ItemAlreadyAssignedToCart(final Long itemId, final Long cartId){
        super(MessageFormat.format("Item: {0} is already assigned to cart: {1}", itemId, cartId));
    }
}
