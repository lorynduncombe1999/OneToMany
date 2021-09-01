package com.example.OneToMany.exception;

import org.aspectj.bridge.Message;

import java.text.MessageFormat;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(final Long id){
        super(MessageFormat.format("Could not find cart with id: ", id));
    }
}
