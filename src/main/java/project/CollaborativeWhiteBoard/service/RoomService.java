package project.CollaborativeWhiteBoard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.CollaborativeWhiteBoard.model.Room;
import project.CollaborativeWhiteBoard.model.User;
import project.CollaborativeWhiteBoard.repository.DrawingEventRepository;
import project.CollaborativeWhiteBoard.repository.RoomRepository;
import project.CollaborativeWhiteBoard.repository.UserRepository;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;

    public Room getRoom(int roomID) {
        Room room =  roomRepository.findById(roomID);
        if (room == null)
            throw new RuntimeException("Room doesn't exist");
        return room;
    }

    public Room addNewRoom (int creatorID) {
        return roomRepository.addNewRoom(new Room(-1, creatorID, true, 0));
    }

    public void updateLastActivity(int roomID) {
        Room room = getRoom(roomID);
        room.setLastActivity(System.currentTimeMillis());
        roomRepository.addNewRoom(room);
    }

    public User joinRoom(int roomID) {
        if(roomRepository.existsById(roomID))
            throw new RuntimeException("Room doesn't exist");

        User newUser = userRepository.addNewUser(new User());
        roomRepository.addUserToRoom(newUser.getUserID(), roomID);
        return newUser;
    }

    public void leaveRoom(int userID, int roomID) {
        if(roomRepository.existsById(roomID))
            throw new RuntimeException("Room doesn't exist");

        if(userRepository.existsById(userID))
            throw new RuntimeException("User doesn't exist");

        roomRepository.removeUserFromRoom(userID, roomID);
        userRepository.deleteById(userID);
    }

}
