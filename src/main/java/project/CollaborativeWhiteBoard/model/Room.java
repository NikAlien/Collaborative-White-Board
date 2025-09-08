package project.CollaborativeWhiteBoard.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Room {
    private int roomID;
    private int creatorID;
    private boolean isActive; // TODO logic
    private long lastActivity;
}
