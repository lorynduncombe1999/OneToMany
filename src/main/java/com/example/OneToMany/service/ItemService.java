package com.example.OneToMany.service;

import com.example.OneToMany.exception.ItemNotFoundException;
import com.example.OneToMany.model.Item;
import com.example.OneToMany.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    //Dependency injection
    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item addItem(Item item){
        return itemRepository.save(item);
    }
//required return type is Item, however "findall" returns iterable, so now we have to convert iterable to a list
    public List<Item> getItems(){
        return StreamSupport.stream(itemRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public Item getItem(Long id){
        return itemRepository.findById(id).orElseThrow(()-> new ItemNotFoundException(id));
    }

    public Item deleteItem(Long id){
        Item item = getItem(id);
        itemRepository.delete(item);
        return item;
    }
@Transactional
    public Item editItem(Long id, Item item){
    Item ItemToEdit = getItem(id);
    ItemToEdit.setSerialNumber(item.getSerialNumber());
    return ItemToEdit;
    }
}
