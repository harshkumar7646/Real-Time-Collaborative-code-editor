package com.example.learningSpring.service;


import com.example.learningSpring.Repository.roomRepo;
import com.example.learningSpring.configure.jwtAuthFilter;
import com.example.learningSpring.entity.Room;
import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class RoomService {
    @Autowired
    private roomRepo roomRepo;
    public String createRoom(String username){
        String rid= UUID.randomUUID().toString();

        Room room =new Room();
        room.setCreatedBy(username);
        room.setRoomID(rid);
        room.getMembers().add(username);
        roomRepo.save(room);

        return rid;//here randomly genearted roomID
    }

    public void joinRoom(String roomId, String username) {
        Room room = roomRepo.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        room.getMembers().add(username);
        roomRepo.save(room);
    }
}
