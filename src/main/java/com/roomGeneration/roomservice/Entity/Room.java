package com.roomGeneration.roomservice.Entity;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Room {


@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

String name;

String description;

int[] exits;

//List<RoomExits> list = new ArrayList<>();


@Autowired
public Room(){}

    public Room(String name, String desc, int[] exits) {
        this.name = name;
        this.description = desc;
        this.exits = exits;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public int[] getExits() {
        return exits;
    }

    public void setExits(int[] exits) {
        this.exits = exits;
    }
}
