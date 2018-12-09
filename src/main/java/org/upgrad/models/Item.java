package org.upgrad.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "item")
public class Item {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     public Item(){
    }
public Item(int restaurantId){
    this.restaurantId = restaurantId;


    public Integer getTop5ItemsbyPopularity(integer id ){
        return retaurantId; 
    }
    public void setTop5ItemsbyPopularity(Integer id){
        this.id=retaurantId;
    }

        private String itemName;

        private int price;

        private String type;

//        @JsonIgnore
//        @ManyToMany(mappedBy = "items")
//        private List<Category> categories;

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public List<Category> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(List<Category> categories) {
//        this.categories = categories;
//    }
}

