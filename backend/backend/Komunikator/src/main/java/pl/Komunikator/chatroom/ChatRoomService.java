package pl.Komunikator.chatroom;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.Komunikator.znajomi.Znajomi;
import pl.Komunikator.znajomi.ZnajomiRepository;

import java.util.ArrayList;
import java.util.List;


/**
 * ChatRoomService klasa realizuje obsługę logiki biznesowej obsługi zapytań bazodanowych
 */
@Service
public class ChatRoomService {
    @Autowired
    ChatRoomRepository chatRoomRepository;
    @Autowired
    ZnajomiRepository znajomiRepository;

    List<ChatRoom> addChatroom(String postBody) {

        // Parse the JSON data
        JSONObject jsonObject = new JSONObject(postBody);

        String name = jsonObject.get("nazwa").toString();
        JSONArray jasonArray = jsonObject.getJSONArray("id_relacji");
        String userName = jsonObject.getString("user");


        ChatRoom chatRoom = new ChatRoom(name,new ArrayList<>(),new ArrayList<>());
        ArrayList<Znajomi> znajomi =new ArrayList<>();
        int size = jasonArray.length();
        int i = 0;
        while (i < size) {
            String tmp = jasonArray.get(i).toString();
//            System.out.println("BLOK DECYZYJNY :::::::: " + userName+"::::::::::"+tmp);
            if(znajomiRepository.findByUsers(userName,tmp) != null)
                znajomi.add(znajomiRepository.findByUsers(userName,tmp ) );

            i++;
        }
        chatRoom.setId_relacji(znajomi);
//        System.out.println("::::: AWGDB ::::::"+chatRoom.getId_relacji().get(0).getId_usera_2().getUser_id());

        chatRoomRepository.save(chatRoom);
        return this.getAllChatRooms();
    }

    List<ChatRoom> getAllChatRooms(){
        return chatRoomRepository.findAll();
    }
    ChatRoom getChatRoomById(Long id){
         return  chatRoomRepository.findById(id).get();
    }

    List <ChatRoom> getChatRoomsByUserId(String user_id){
        List<ChatRoom> chatRoomList = new ArrayList<>();
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        for(ChatRoom chatRoom : chatRooms){
//            System.out.println("WSZYSTKIE ::::: " +chatRoom);
           for (Znajomi znajomi :chatRoom.getId_relacji()){
               if (znajomi.getId_usera_1().getUser_id().equals(user_id) ||
                       znajomi.getId_usera_2().getUser_id().equals(user_id)){
                   chatRoomList.add(chatRoom);
//                   System.out.println("DODANE ::::::: "+ chatRoom);
               }
           }
        }
//        System.out.println("----------------------------------------------");
        return chatRoomList;
    }
}
