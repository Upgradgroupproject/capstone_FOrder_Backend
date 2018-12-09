package org.upgrad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {


    
    @Query(nativeQuery = true, value="select * from restaurant")
    List<Restaurant> getAllRestaurant();
    
    @Query(nativeQuery = true,value="select * from restaurant where name=?")
    Restaurant getRestaurantByName(String RestaurantName);

    @Query(nativeQuery = true,value="select * from restaurant where categoryname=?")
    Restaurant getRestaurantByCategory(String CategoryName);

    @Query(nativeQuery = true,value="select * from restaurant where id=?1")
    Restaurant getRestaurantByRestaurantId(int restaurantId);
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="update Details set detail=?2,modifiedon=now() where id=?1")
    @Query(nativeQuery = true, value="select max(id)  from restaurant")
    @Query(nativeQuery = true,value ="select DETAILS.det,DETAILS.date,DETAILS.user_id,DETAILS.restaurantId,DETAILS.modifiedOn,count(*) from RESTAURANT,LIKES where RESTAURANT.ID=LIKES.restaurantId and LIKES.USER_ID=(?2) group by restaurantId order by count(*) desc")
    void updateRestaurantDetails(int restaurantId , String DetailsBody);
   
  




}



