package com.example.OneToMany.model.dto;

import com.example.OneToMany.model.Item;
import lombok.Data;

import java.util.Objects;

@Data
public class ItemDto {
    private Long id;
    private String serialNumber;
    private PlainCartDto plainCartDto;
    //takes the cart input and creates a new itemDTO and sets name, id, and items to the itemDTO
    public static ItemDto from(Item item){
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setSerialNumber(item.getSerialNumber());
        //If the object IS NOT null it takes itemDto and sets it to plain cartDto
        if(Objects.nonNull(item.getCart())){
            itemDto.setPlainCartDto(PlainCartDto.from(item.getCart()));
        }
        return itemDto;
    }

}
