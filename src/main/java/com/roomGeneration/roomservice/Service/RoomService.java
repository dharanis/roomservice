package com.roomGeneration.roomservice.Service;


import com.roomGeneration.roomservice.Entity.Room;
import com.roomGeneration.roomservice.Repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Service
public class RoomService {

    RoomRepo room_repo;

    @Autowired
    public RoomService(RoomRepo room_repo){this.room_repo = room_repo;}

    public Object createRoom(int char_id, int room_id) {

        return new Object();
    }


    public Room saveRoom(String name){

        return room_repo.save(createRoomObj(name));
    }


    public Room createRoomObj(String name){
        Room room = new Room();
        room.setName(name);
        room.setDesc(" dark room");
        int[] exits = new int[]{1,3,8};
        room.setExits(exits);
        return room;
    }

    public Room updateRoom(Long char_id, int location) {
        Room room = new Room();
      room = getRoom(char_id);
      if(isMoveValid(room,location)){

          if(isAbleToMove(room,getHitPoints(char_id))) {
              final String uri = "http://localhost:8080/api/update/" + char_id + "/" + location;
              RestTemplate restTemplate = new RestTemplate();
              String result = restTemplate.getForObject(uri, String.class);
          }
          else {
              throw new NoEnoughHitPoints();
          }
      }
      else{
          throw new NotAValidMove();
      }
        return room;
    }

    public Room getRoom(Long char_id){
        final String uri = "http://localhost:8080/api/get/location/" + char_id;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        Optional<Room> roomOptional = room_repo.findById(Integer.parseInt(result));
        return roomOptional.get();
    }

    public Integer getHitPoints(Long char_id){
        final String uri = "http://localhost:8080/api/get/hitpoints/" + char_id;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return Integer.parseInt(result);
    }

    public boolean isMoveValid(Room room, int location){

        int[] exits = room.getExits();
        for(int i=0;i <exits.length;i++){
            if(location == exits[i])
                return true;
        }
        return false;
    }
    public boolean isAbleToMove(Room room, int hitpoints){
        int[] exits = room.getExits();
        for(int i=0;i <exits.length;i++){
            if(hitpoints >= exits[i])
                return true;
            else if((exits[i] - hitpoints) == 1)
                return true;
        }

        return false;
    }

}

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "No exits found for the given value")
class NotAValidMove extends RuntimeException{

}

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason ="No enough hit points to move")
class NoEnoughHitPoints extends RuntimeException{

}
