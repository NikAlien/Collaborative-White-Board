package project.CollaborativeWhiteBoard.repository;

import org.springframework.stereotype.Repository;
import project.CollaborativeWhiteBoard.model.DrawEvent;
import project.CollaborativeWhiteBoard.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DrawingEventRepository {
    private final Map<Integer, List<DrawEvent>> drawingHistory = new ConcurrentHashMap<>();

    public List<DrawEvent> findById(int id) {
        return drawingHistory.getOrDefault(id, new ArrayList<>());
    }

    public DrawEvent addNewEvent(DrawEvent event) {
        List<DrawEvent> events = drawingHistory.getOrDefault(event.getRoomID(), new ArrayList<>());
        events.add(event);
        drawingHistory.put(event.getRoomID(), events);
        return event;
    }

    public void deleteById(int id) {
        drawingHistory.remove(id);
    }



}
