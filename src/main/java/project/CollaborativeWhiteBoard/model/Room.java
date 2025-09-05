package project.CollaborativeWhiteBoard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Room {
    private int roomID;
//    private ConcurrentHashMap<Integer, User> users;
    private int creatorID;
    private boolean isActive; // TODO logic


}
