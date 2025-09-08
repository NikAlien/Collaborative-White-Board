package project.CollaborativeWhiteBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import project.CollaborativeWhiteBoard.model.CursorPosition;
import project.CollaborativeWhiteBoard.model.DrawEvent;
import project.CollaborativeWhiteBoard.model.User;
import project.CollaborativeWhiteBoard.service.DrawingService;
import project.CollaborativeWhiteBoard.service.RoomService;

@Controller
@CrossOrigin
public class WhiteboardController {
    @Autowired
    RoomService roomService;
    @Autowired
    DrawingService drawingService;

    @MessageMapping("/whiteboard/{roomID}/draw")
    @SendTo("/topic/whiteboard/{roomID}")
    public DrawEvent handleDrawEvent(
            @DestinationVariable int roomID,
            DrawEvent drawEvent,
            SimpMessageHeaderAccessor headerAccessor,
            SimpMessagingTemplate messagingTemplate) {
        try {
            drawEvent.setRoomID(roomID);
            drawEvent.setUserID(extractUserId(headerAccessor));
            return drawingService.addDrawEvent(drawEvent);
        } catch (Exception e) {
            messagingTemplate.convertAndSendToUser(
                    headerAccessor.getSessionId(), "/queue/join", e.getMessage());
            throw e;
        }
    }

    @MessageMapping("/whiteboard/{roomID}/cursor")
    @SendTo("/topic/whiteboard/{roomID}/cursors")
    public CursorPosition handleCursorPosition(
            @DestinationVariable int roomID,
            CursorPosition cursorPos,
            SimpMessageHeaderAccessor headerAccessor,
            SimpMessagingTemplate messagingTemplate) {
        try {
            cursorPos.setUserID(extractUserId(headerAccessor));
            return cursorPos;
        } catch (Exception e) {
            messagingTemplate.convertAndSendToUser(
                    headerAccessor.getSessionId(), "/queue/join", e.getMessage());
            throw e;
        }
    }

    @MessageMapping("/whiteboard/{roomID}/join")
    @SendTo("/topic/whiteboard/{roomID}/users")
    public User handleUserJoin(
            @DestinationVariable int roomID,
            SimpMessageHeaderAccessor headerAccessor,
            SimpMessagingTemplate messagingTemplate) {
        try {
            return roomService.joinRoom(roomID);
        } catch (Exception e) {
            messagingTemplate.convertAndSendToUser(
                    headerAccessor.getSessionId(), "/queue/join", e.getMessage());
            throw e;
        }
    }

    private int extractUserId(SimpMessageHeaderAccessor headerAccessor) {
        return headerAccessor.getSessionId().hashCode();
    }
}
