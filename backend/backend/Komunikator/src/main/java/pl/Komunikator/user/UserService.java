package pl.Komunikator.user;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * UserService klasa realizuje obsługę logiki biznesowej obsługi zapytań bazodanowych
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public User_1 findUserById(String id) {
        try {
            return userRepository.findById(id).get();
        }catch (NoSuchElementException e){
            return null;
        }
    }
    User_1 findUserByNick(String nick) {
       return userRepository.getUserByNick(nick);
    }
    void addUser(String user1){
        JSONObject jsonObject = new JSONObject(user1);
        String user_id = jsonObject.getString("user_id");
        String nick = jsonObject.getString("nick");
        String password = jsonObject.getString("password");

        User_1 user = new User_1(user_id,nick,password);
        userRepository.save(user);
    }

    public List<User_1> getAllUsers() {
        return userRepository.findAll();
    }
}
