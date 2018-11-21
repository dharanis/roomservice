package com.roomGeneration.roomservice.Repository;


import com.roomGeneration.roomservice.Entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends CrudRepository<Room, Integer> {
}
