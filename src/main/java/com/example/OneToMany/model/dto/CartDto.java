package com.example.OneToMany.model.dto;

import com.example.OneToMany.model.Cart;
import com.example.OneToMany.model.Item;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CartDto {
    private Long id;
    private String name;
    private List<ItemDto> itemsDto = new ArrayList<>();

    //takes the cart input and creates a new cartDTO and setts name, id, and items to the cartDTO
    public static CartDto from(Cart cart){
    CartDto cartDto = new CartDto();
    cartDto.setId(cart.getId());
    cartDto.setName(cart.getName());
    cartDto.setItemsDto(cart.getItems().stream().map(ItemDto::from).collect(Collectors.toList()));
    return cartDto;
    }
}
