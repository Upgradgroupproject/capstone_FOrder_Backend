package org.upgrad.models;
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
