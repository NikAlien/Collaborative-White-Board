package project.CollaborativeWhiteBoard.repository;

import org.springframework.stereotype.Repository;
import project.CollaborativeWhiteBoard.model.Room;
import project.CollaborativeWhiteBoard.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private int idGenerator = 1;
    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    public User findById(int id) {
        return users.get(id);
    }

    public User addNewUser(User user) {
        user.setUserID(idGenerator);
        idGenerator++;
        users.put(user.getUserID(), user);
        return user;
    }

    public void deleteById(int id) {
        users.remove(id);
    }

    public boolean existsById(int id) {
        return users.containsKey(id);
    }




}
