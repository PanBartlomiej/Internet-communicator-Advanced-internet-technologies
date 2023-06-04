package pl.Komunikator.znajomi;


import org.hibernate.dialect.PostgreSQLPGObjectJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.Komunikator.user.UserService;
import pl.Komunikator.user.User_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * ZnajomiController klasa obsługuje metody http związane z tabelą Znajomi
 * Realizuje technologię Cross-origin resource sharing –
 * mechanizm umożliwiający współdzielenie zasobów pomiędzy serwerami znajdującymi się w różnych domenach.
 * odostęnia endpointy odpowiedzialne za pobieranie oraz dodwanie znajomych.
 */
@RestController
@CrossOrigin
public class ZnajomiController {
    @Autowired
    ZnajomiService znajomiService;

    @GetMapping("/GetFriends/{user1}")
    List<User_1> getFriends(@PathVariable String user1){
        return znajomiService.getFriendsofUser(user1);
    }

    @PostMapping(value = "/DeleteFriend", consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<User_1>>  deleteFriendsofUser(@RequestBody String znajomi){
        return znajomiService.deleteFriendofUser(znajomi);
    }
    @PostMapping(value = "/AddFriend", consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
        ResponseEntity<List<User_1>> addFriend(@RequestBody String znajomi){
        return znajomiService.addFriend(znajomi);
    }
}
