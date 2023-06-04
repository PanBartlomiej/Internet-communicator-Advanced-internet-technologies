package pl.Komunikator.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController klasa obsługuje metody http związane z encją Post
 * Realizuje technologię Cross-origin resource sharing –
 * mechanizm umożliwiający współdzielenie zasobów pomiędzy serwerami znajdującymi się w różnych domenach.
 * obsługuje endpointy http odpowiedzialne za pobieranie i wysyłanie danych do bazy
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    List<User_1> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getUserByNick/{nick}")
    public User_1 getUserByNick(@PathVariable String nick){
        return  userService.findUserByNick(nick);
    }
    @GetMapping("/getUserById/{id}")
    public User_1 getUserById(@PathVariable String id){
        return  userService.findUserById(id);
    }
    @PostMapping(value = "/addUser", consumes = {"application/json"})
    void addUser(@RequestBody String user1){

        userService.addUser(user1);

    }
}
