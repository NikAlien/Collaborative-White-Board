package project.CollaborativeWhiteBoard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DrawEvent {
    private EventType eventType;
    private int x;
    private int y;
    private int endX;
    private int endY;
    private String color;
    private int strokeWidth;
    private String text;
    private int roomID;
    private int userID;

}
