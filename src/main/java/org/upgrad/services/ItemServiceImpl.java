package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Item;
import org.upgrad.repositories.ItemRepository;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getItemByPopularity(int restaurantId) {
        return null;//from restaurant
    }

    @Override
    public Item getItemById(int id) {
        return itemRepository.findItemById(id);
    }

    @Override
    public List<Item> getTop5ItemsByPopularity(int restaurantId) {
        return itemRepository.findTop5ItemsByPopularity(restaurantId);
    }


}
