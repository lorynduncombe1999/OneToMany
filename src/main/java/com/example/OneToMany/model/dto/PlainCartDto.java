package com.example.OneToMany.model.dto;

import com.example.OneToMany.model.Cart;
import lombok.Data;

@Data
public class PlainCartDto {
    private Long id;
    private String name;
//Takes cart info and sets it to plain cart DTO
    public static PlainCartDto from(Cart cart){
        PlainCartDto plainCartDto = new PlainCartDto();
        plainCartDto.setId(cart.getId());
        plainCartDto.setName(cart.getName());
        return plainCartDto;
    }
}
