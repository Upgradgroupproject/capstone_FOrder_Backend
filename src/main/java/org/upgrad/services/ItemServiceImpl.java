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

    @Override
    public List<Item> getItemByPopularity(int restaurantId) {

        // depends on restaurant ID
        return null;
    }

    @Override
    public Item getItemById(int id) {
        return itemRepository.findItemById(id);
    }
}
