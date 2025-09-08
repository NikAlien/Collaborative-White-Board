package project.CollaborativeWhiteBoard.repository;

import org.springframework.stereotype.Repository;
import project.CollaborativeWhiteBoard.model.DrawEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class DrawingEventRepository {
    private final Map<Integer, CopyOnWriteArrayList<DrawEvent>> drawingHistory = new ConcurrentHashMap<>();

    public List<DrawEvent> findById(int id) {
        return drawingHistory.getOrDefault(id, new CopyOnWriteArrayList<>());
    }

    public DrawEvent addNewEvent(DrawEvent event) {
        CopyOnWriteArrayList<DrawEvent> events = drawingHistory
                .computeIfAbsent(event.getRoomID(), k -> new CopyOnWriteArrayList<>());
        events.add(event);
        return event;
    }

    public void removeRoomHistory(int id) {
        drawingHistory.put(id, new CopyOnWriteArrayList<>());
    }
}
