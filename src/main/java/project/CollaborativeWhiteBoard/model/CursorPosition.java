package project.CollaborativeWhiteBoard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CursorPosition {
    private int x;
    private int y;
//    private String roomId;
    private String cursorColor;
}
