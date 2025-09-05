package project.CollaborativeWhiteBoard.repository;

import org.springframework.stereotype.Repository;
import project.CollaborativeWhiteBoard.model.Room;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RoomRepository {
    private int idGenerator = 1;
    private final Map<Integer, Room> rooms = new ConcurrentHashMap<>();
    private final Map<Integer, Integer> userRoomMap = new ConcurrentHashMap<>();  // for userId -> roomId

    public Room findById(int id) {
        return rooms.get(id);
    }

    public Room addNewRoom(Room room) {
        room.setRoomID(idGenerator);
        idGenerator++;
        rooms.put(room.getRoomID(), room);
        return room;
    }

    public void deleteById(int id) {
        rooms.remove(id);
    }

    public void addUserToRoom(int userID, int roomID) {
        userRoomMap.put(userID, roomID);
    }

    public void removeUserFromRoom(int userID, int roomID) {
        userRoomMap.remove(userID, roomID);
    }

    public boolean existsById(String roomID) {
        return rooms.containsKey(roomID);
    }
}
