package org.upgrad.models;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "flat_buil_number")
    private String flat_buil_number;

    @Column(name = "locality")
    private String locality;

    @Column(name = "city")
    private String city;

    @Column(name="zipcode")
    private  String zipcode;

    @Column(name="state_id")
    private int state_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Address(String flat_buil_number, String locality, String city, String zipcode, int state_id) {
        this.flat_buil_number = flat_buil_number;
        this.locality = locality;
        this.city = city;
        this.zipcode = zipcode;
        this.state_id = state_id;
    }
    public Address()
    {}

    public String getFlat_buil_number() {
        return flat_buil_number;
    }

    public void setFlat_buil_number(String flat_buil_number) {
        this.flat_buil_number = flat_buil_number;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }
}
