package org.upgrad.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


/***
 * Restaurant model as per the relation to Restaurants
 *             includes category table and needed column as per schema, including java persistence bindings,
 *                          restaurant_name,poto_url,user_rating,avg_price,user_rating,address,category
 *                      default constructor,
 *                      getter and setter
 * **/


@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;

    @Column(name="photo_url")
    private String photoUrl;

    @Column(name="user_rating")
    private Double userRating;

    @Column(name = "average_price_for_two")
    private Integer avgPrice;

    @Column(name = "number_of_users_rated")
    private Integer numberUsersRated;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", nullable = false)
    private Address address;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "restaurant_category",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> restaurantCategories ;



    public Restaurant() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Double getUserRating() {
        return userRating;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }

    public Integer getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Integer avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Integer getNumberUsersRated() {
        return numberUsersRated;
    }

    public void setNumberUsersRated(Integer numberUsersRated) {
        this.numberUsersRated = numberUsersRated;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Category> getRestaurantCategories() {
        return restaurantCategories;
    }

    public void setRestaurantCategories(List<Category> restaurantCategories) {
        this.restaurantCategories = restaurantCategories;
    }
}
