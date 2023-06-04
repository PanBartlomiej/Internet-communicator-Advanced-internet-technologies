package pl.Komunikator.znajomi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.Komunikator.user.UserService;
import pl.Komunikator.user.User_1;

import java.util.ArrayList;
import java.util.List;

/**
 * ZnajomiService klasa realizuje obsługę logiki biznesowej obsługi zapytań bazodanowych
 */
@Service
public class ZnajomiService {
    @Autowired
    ZnajomiRepository znajomiRepository;
    @Autowired
    UserService userService;

    ResponseEntity<List<User_1>> deleteFriendofUser(String znajomi){

        // Parse Body
        String[] strings = znajomi.split(";");
        String u1 = strings[0];
        String u2 = strings[1];
        Znajomi znajomi1 = znajomiRepository.findByUsers(u1, u2);
        znajomiRepository.delete(znajomi1);
        return ResponseEntity.ok(this.getFriendsofUser(u1));
    }
    List<User_1> getFriendsofUser(String user){
        List<List<String>> objectList=znajomiRepository.findByUser1(user);
        List<User_1> users= new ArrayList<>();
        for(List<String> lista :objectList)
            users.add(new User_1(lista.get(0),lista.get(1),lista.get(2)));
        return users;
    }
    ResponseEntity<List<User_1>> addFriend( String znajomi) {
        // Parse Body
        String[] strings = znajomi.split(";");
        String u1 = strings[0];
        String u2 = strings[1];


        User_1 user1 = userService.findUserById(u1);
        User_1 user2 = userService.findUserById(u2);
        if (user1 == null || user2 == null) {
            return ResponseEntity.notFound().build();
        }
        Znajomi znajomi1 = new Znajomi(user1, user2);
        znajomiRepository.save(znajomi1);
        return ResponseEntity.ok(this.getFriendsofUser(u1));
    }
}
