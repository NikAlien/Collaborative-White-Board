package project.CollaborativeWhiteBoard.repository;

import org.springframework.stereotype.Repository;
import project.CollaborativeWhiteBoard.model.DrawEvent;
import project.CollaborativeWhiteBoard.model.Room;
import project.CollaborativeWhiteBoard.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class RoomRepository {
    private int idGenerator = 1;
    private final Map<Integer, Room> rooms = new ConcurrentHashMap<>();
    private final Map<Integer, List<User>> userRoomMap = new ConcurrentHashMap<>();  // for userId -> roomId

    public Room findById(int id) {
        return rooms.get(id);
    }

    public List<Room> findAll() {return new ArrayList<>(rooms.values());}

    public List<User> findAllUsers(int roomID) {return userRoomMap.get(roomID);}

    public Room addNewRoom(Room room) {
        room.setRoomID(idGenerator);
        idGenerator++;
        rooms.put(room.getRoomID(), room);
        return room;
    }

    public void deleteById(int id) {
        userRoomMap.remove(id);
        rooms.remove(id);
    }

    public void addUserToRoom(int userID, int roomID) {
        List<User> users = userRoomMap.getOrDefault(roomID, new ArrayList<>());
        users.add(new User(userID));
        userRoomMap.put(roomID, users);
    }

    public void removeUserFromRoom(int userID, int roomID) {
        List<User> users = userRoomMap.getOrDefault(roomID, new ArrayList<>());
        users.removeIf(user -> user.getUserID() == userID);
        userRoomMap.put(roomID, users);
    }

    public boolean existsById(int roomID) {
        return rooms.containsKey(roomID);
    }
}
