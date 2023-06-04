package pl.Komunikator.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * Klasa do wyspecjalizowanej deserializacji instancji klasy User_1
 */
public class UserDeserializer extends StdDeserializer<User_1> {
    public UserDeserializer() {
        super(User_1.class);
    }

    @Override
    public User_1 deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        String email = p.getValueAsString();

        return new User_1(email);
    }
}