package project.CollaborativeWhiteBoard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.CollaborativeWhiteBoard.model.DrawEvent;
import project.CollaborativeWhiteBoard.repository.DrawingEventRepository;
import project.CollaborativeWhiteBoard.repository.RoomRepository;
import project.CollaborativeWhiteBoard.repository.UserRepository;

import java.util.List;

@Service
public class DrawingService {
    @Autowired
    private DrawingEventRepository drawingEventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;

    public DrawEvent addDrawEvent(DrawEvent drawEvent) {
        return drawingEventRepository.addNewEvent(drawEvent);
    }

    public List<DrawEvent> getDrawingHistory(int roomID) {
        return drawingEventRepository.findById(roomID);
    }

    public void clearDrawingHistory(int roomID) {
        drawingEventRepository.removeRoomHistory(roomID);
    }

}
