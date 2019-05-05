package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Friendship")
public class Friendship implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_friend;

    @ManyToOne
    @JoinColumn(name="owner") // Campo de la tabla friendship que es fk de User
    @JsonManagedReference
    private User owner;

    @ManyToOne
    @JoinColumn(name="friend") // Campo de la tabla friendship que es fk de User
    private User friend;

    public Friendship() {

    }

    public long getId_friend() {
        return id_friend;
    }

    public void setId_friend(long id_friend) {
        this.id_friend = id_friend;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
}