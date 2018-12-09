package org.upgrad.models;

import javax.persistence.*;

@Entity
@Table(name = "user_address")
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="type")
    private String type;

    @Column(name="user_id")
    private int user_id;

    public UserAddress(String type, int user_id, int address_id) {
        this.type = type;
        this.user_id = user_id;
        this.address_id = address_id;
    }
    public UserAddress()
    {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    @Column(name="address_id")
    private int address_id;



}
