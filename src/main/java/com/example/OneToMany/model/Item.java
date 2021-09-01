package com.example.OneToMany.model;

import com.example.OneToMany.model.dto.ItemDto;
import lombok.Data;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    private String serialNumber;
    @ManyToOne
    private Cart cart;
    public Item() {
    }
//sets DTO to a new Item
    public static Item from(ItemDto itemDto){
        Item item = new Item();
        item.setSerialNumber(itemDto.getSerialNumber());
    return item;
    }
}
