package org.upgrad.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/***
 * Category model as per the relation to Restaurants
 *             includes category table and needed column as per schema, including java persistence bindings
 *                      default constructor,
 *                      getter and setter
 * **/


@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String paymentName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public Payment() {
    }

    public Payment(int id, String paymentName) {

        this.id = id;
        this.paymentName = paymentName;
    }
}
