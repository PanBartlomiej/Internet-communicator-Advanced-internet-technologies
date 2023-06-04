package pl.Komunikator.chatroom;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * ChatRoomDeserializer klasa odpowiedzialna za deserializacje klasy ChatRoom
 * rozszerza klase StdDeserializer
 */

public class ChatRoomDeserializer extends StdDeserializer<ChatRoom> {
    public ChatRoomDeserializer() {
        super(ChatRoom.class);
    }

    @Override
    public ChatRoom deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Long chatroom_id = p.getValueAsLong();
        return new ChatRoom(chatroom_id);
    }
}
