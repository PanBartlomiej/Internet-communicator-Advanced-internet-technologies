package pl.Komunikator.chatroom;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.Komunikator.znajomi.Znajomi;
import pl.Komunikator.znajomi.ZnajomiRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * ChatRoomControler klasa obsługuje metody http związane z encją chatroom
 * Realizuje technologię Cross-origin resource sharing –
 * mechanizm umożliwiający współdzielenie zasobów pomiędzy serwerami znajdującymi się w różnych domenach.
 */
@CrossOrigin
@RestController
public class ChatRoomControler {
    @Autowired
    ChatRoomService chatRoomService;
    @Autowired
    ZnajomiRepository znajomiRepository;

    @GetMapping("/getChatRoom/{id}")
    ChatRoom getChatRoomById(@PathVariable Long id){
        return chatRoomService.getChatRoomById(id);
    }
    @GetMapping("/getChatRoomsByUserId/{id1}")
    List<ChatRoom> getChatRoomsByUserId(@PathVariable String id1){
        return chatRoomService.getChatRoomsByUserId(id1);
    }
    @GetMapping("/ChatRooms")
    List<ChatRoom> getAllChatRooms(){
        return chatRoomService.getAllChatRooms();
    }
    @PostMapping("/AddChatroom")
    List<ChatRoom> addChatroom(@RequestBody String postBody) {
        return chatRoomService.addChatroom(postBody);
    }
}
