package com.roomGeneration.roomservice.Entity;


import javax.persistence.Entity;

//@Entity
public class RoomExits {

    Long exitId;

    public RoomExits(){};
    public RoomExits(Long exitId) {
        this.exitId = exitId;
    }

    public Long getExitId() {
        return exitId;
    }

    public void setExitId(Long exitId) {
        this.exitId = exitId;
    }
}
