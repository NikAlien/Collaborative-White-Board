package project.CollaborativeWhiteBoard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.CollaborativeWhiteBoard.model.Room;
import project.CollaborativeWhiteBoard.repository.DrawingEventRepository;
import project.CollaborativeWhiteBoard.repository.RoomRepository;
import project.CollaborativeWhiteBoard.repository.UserRepository;

import java.util.Map;

@Component
public class RoomCleanupTask {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private DrawingEventRepository drawingEventRepository;

    @Scheduled(fixedRate = 3600000) // 1 hour
    public void cleanupInactiveRooms() {
        long now = System.currentTimeMillis();

        for (Room room : roomRepository.findAll()) {
            if (roomRepository.findAllUsers(room.getRoomID()).isEmpty() || !room.isActive()) {
                drawingEventRepository.removeRoomHistory(room.getRoomID());
                roomRepository.deleteById(room.getRoomID());
            }
        }
    }
}
