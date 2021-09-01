package com.example.OneToMany.exception;

import java.text.MessageFormat;
//Custom exception class that returns a specified message
public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(final Long id){
        super(MessageFormat.format("Could not find cart with id: ", id));
    }
}
