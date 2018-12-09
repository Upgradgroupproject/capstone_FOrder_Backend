package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Item;

import java.util.List;


@Repository
public interface ItemRepository extends CrudRepository<Item,Integer>{

    @Query(nativeQuery = true,value="SELECT * from ITEM WHERE ID=?1")
    Item findItemById(int id);

    // tobe checked with mapping of restaurant - popularity
    @Query(nativeQuery = true,value="SELECT * from ITEM WHERE ID=?1 ")
    List<Item> findTop5ItemsByPopularity(int id);


}
