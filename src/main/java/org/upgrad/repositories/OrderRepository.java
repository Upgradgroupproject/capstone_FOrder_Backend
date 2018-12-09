package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository <Order,Integer>{

    @Query(nativeQuery = true, value = "SELECT * FROM orders WHERE user_id = ?1 ORDER BY date DESC")
    List<Order> findOrdersByUser(Integer userId);

    @Query(nativeQuery = true, value = "SELECT id FROM orders ORDER BY id DESC limit 1")
    Integer findLatestOrderId();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT into orders (coupon_id, discount, bill,DATE ,user_id,payment_id, address_id) VALUES(?1, ?2, ?3, now(), ?4, ?5, ?6)")
    void saveOrder(Integer couponId, double discount, double bill, Integer userId, Integer paymentId,  Integer addressId);


    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO order_item(order_id, item_id, quantity, price) VALUES(?1, ?2, ?3, ?4);")
    void saveOrderItem(Integer orderId, Integer itemId, Integer quantity, Double price);
}
