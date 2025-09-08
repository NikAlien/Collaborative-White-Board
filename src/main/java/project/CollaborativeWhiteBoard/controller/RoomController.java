package project.CollaborativeWhiteBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.CollaborativeWhiteBoard.model.Room;
import project.CollaborativeWhiteBoard.service.RoomService;
import java.util.Map;

@Controller
@CrossOrigin
public class RoomController {
    @Autowired
    RoomService roomService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createRoom(@RequestParam int creatorID) {
        Room room = roomService.addNewRoom(creatorID);
        return ResponseEntity.ok(Map.of(
                "roomID", room.getRoomID(),
                "url", "http://localhost:3000/whiteboard/" + room.getRoomID()
        ));
    }

    @GetMapping("/{roomID}")
    public ResponseEntity<Room> validateRoom(@PathVariable int roomID) {
        Room room = roomService.getRoom(roomID);
        return ResponseEntity.ok(room);
    }
}
