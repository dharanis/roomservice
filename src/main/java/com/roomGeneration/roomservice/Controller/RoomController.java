package com.roomGeneration.roomservice.Controller;


import com.roomGeneration.roomservice.Entity.Room;
import com.roomGeneration.roomservice.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoomController {

    private final RoomService room_service;

    @Autowired
    public RoomController(RoomService room_service){this.room_service = room_service;}

    @GetMapping("/ping")
    public String ping(){return "This is room generetion controller";}

    @PostMapping("/create/{char_name}")
    public Room createRoom(@PathVariable String char_name){
       return  room_service.saveRoom(char_name);
    }

    @GetMapping("/move/{char_id}/to/{location}")
    public Room updateRoom(@PathVariable Long char_id,@PathVariable int location){
        return room_service.updateRoom(char_id,location);
    }



}
