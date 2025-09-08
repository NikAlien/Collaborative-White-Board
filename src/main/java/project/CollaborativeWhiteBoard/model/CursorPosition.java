package project.CollaborativeWhiteBoard.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursorPosition {
    private int x;
    private int y;
    private int userID;
    private String cursorColor;
}
