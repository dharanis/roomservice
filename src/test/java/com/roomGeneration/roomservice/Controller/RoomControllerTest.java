package com.roomGeneration.roomservice.Controller;


import com.roomGeneration.roomservice.Entity.Room;
import com.roomGeneration.roomservice.Repository.RoomRepo;
import com.roomGeneration.roomservice.Service.RoomService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class RoomControllerTest {

    @MockBean
    RoomRepo room_repo;

    @MockBean
    RoomService room_service;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setup(){}


    @Test
    public void createRoomTest() throws Exception {
        Room room = new Room();
        when(room_service.saveRoom(anyString())).thenReturn(room);
        mockMvc.perform(MockMvcRequestBuilders
        .post("/api/create/{name}","bheem")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        verify(room_service,times(1)).saveRoom(anyString());
        verifyNoMoreInteractions(room_service);
    }

    @Test
    public void updateCharLocationTest() throws Exception {
        Room room = new Room();
        when(room_service.updateRoom(anyLong(),anyInt())).thenReturn(room);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/move/{char_id}/to/{location}",2,7)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        verify(room_service,times(1)).updateRoom(anyLong(),anyInt());
        verifyNoMoreInteractions(room_service);
    }


}
