package org.upgrad.models;


import javax.persistence.*;

@Entity
@Table(name = "state")
public class States {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "state_name")
    private String state_name;

    public States(String state_name) {
        this.state_name = state_name;
    }

    public States() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }



}
