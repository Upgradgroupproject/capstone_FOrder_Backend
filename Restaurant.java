package org.upgrad.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "Restaurant")
public class Restaurant {
    @Column(name = "Restaurantname", nullable = false)
    private String RestaurantName;

    @Column(name = "Categoryname")
    private String CategoryName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer RestaurantId;
}
public User(String RestaurantName, String CategoryName, Integer RestaurantId, String contactNumber, String password) {
    this.RestaurantName = RestauranttName;
    this.CategoryName = CategoryName;
    this.RestaurantId = RestaurantId;
  
}

public Integer getAllRestaurant() {
    return RestaurantName;
}

public void setAllRestaurant() {
    this.RestaurantName = RestaurantName;
}

public String getRestaurantName() {
    return RestaurantName;
}

public void setRestaurantName(String RestaurantName) {
    this.RestaurantName = RestaurantName;
}

public String getRestaurantbyCategoryName() {
    return CategoryName;
}

public void setRestaurantbyCategoryName(String CategoryName) {
    this.CategoryName = CategoryName;
}

public Integer getRestaurantByRestaurantId() {
    return RestaurantId;
}

public void setRestaurantByRestaurantId(Integer RestaurantId ) {
    this.RestaurantId = RestaurantId;
}

public String PutRestaurantDetails() {
    return RestaurantId;
}

}
