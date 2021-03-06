package org.upgrad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {



    @Query(nativeQuery = true, value="select * from restaurant")
    List<Restaurant> getAllRestaurant();

    @Query(nativeQuery = true,value="select * from restaurant where id=?1")
    Restaurant getRestaurantByRestaurantId(int restaurantId);

    /**
     * Query to retreve Restaurant
     *            category name is matched to available restaurant name, need not to be equal, by ILIKE
     *            if found returns respective Restaurant(s)
     * ***/

    @Query(nativeQuery = true, value = "select * from restaurant where restaurant_name ILIKE %?1% ORDER BY restaurant_name")
    List<Restaurant> getRestaurantByName(String restaurantName);

    /**
     * Query to retreve Restaurant
     *            category name is matched to available category, need not to be equal, by ILIKE
     *            if found restaurant_category is looked for categories and respective Restaurant
     * ***/

    @Query(nativeQuery = true, value = "select * from restaurant R inner join restaurant_category C on R.id = C.restaurant_id " +
            "inner join category N on  C.category_id = N.id where N.category_name ILIKE %?1% ORDER BY R.restaurant_name")
    List<Restaurant> getRestaurantByCategory(String categoryName);

    /*
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="update Details set detail=?2,modifiedon=now() where id=?1")
    @Query(nativeQuery = true, value="select max(id)  from restaurant")
    @Query(nativeQuery = true,value ="select DETAILS.det,DETAILS.date,DETAILS.user_id,DETAILS.restaurantId,DETAILS.modifiedOn,count(*) from RESTAURANT,LIKES where RESTAURANT.ID=LIKES.restaurantId and LIKES.USER_ID=(?2) group by restaurantId order by count(*) desc")
    void updateRestaurantDetails(int restaurantId , String DetailsBody);
    */

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE RESTAURANT SET user_rating = ?1, number_of_users_rated= ?3 WHERE id=?2")
    int updateRating(int rating, int restaurantId , int numOfUsersRated);

}


