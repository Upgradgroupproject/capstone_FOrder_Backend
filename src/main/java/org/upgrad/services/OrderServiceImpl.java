package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Coupon;
import org.upgrad.models.Item;
import org.upgrad.models.Order;
import org.upgrad.repositories.*;
import org.upgrad.requestResponseEntity.ItemQuantity;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private CouponRepository couponRepository;

    //private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    public OrderServiceImpl(CouponRepository couponRepository, OrderRepository orderRepository, ItemRepository itemRepository) {
        this.couponRepository = couponRepository;
       // this.addressRepository = addressRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Coupon getCoupon(String couponName) {
        return couponRepository.findCouponByName(couponName);
    }



    @Override
    public List<Order> getOrdersByUser(Integer userId) {
        return orderRepository.findOrdersByUser(userId);

    }

    @Override
    public Integer addOrderWithPermAddress(Integer addressId, Integer paymentId, Integer userId, ArrayList<ItemQuantity> itemQuantities,
                                           double bill, Integer couponId, double discount) {


        orderRepository.saveOrder(couponId, discount, bill, userId, paymentId,  addressId);
        Integer orderId = orderRepository.findLatestOrderId();

        for (ItemQuantity itemQuantity: itemQuantities) {

            Item item = itemRepository.findItemById (itemQuantity.getItemId());
            double totalPrice = item.getPrice() * itemQuantity.getQuantity();
            orderRepository.saveOrderItem(orderId, itemQuantity.getItemId(), itemQuantity.getQuantity(), totalPrice);
        }

        return orderId;
    }

    @Override
    public Integer addOrder(String flatBuilNo, String locality, String city, String zipCode, Integer stateId, String type,
                            Integer paymentId, Integer userId, List<ItemQuantity> itemQuantities, double bill, Integer couponId,
                            double discount) {

        return 1;
    }
}
