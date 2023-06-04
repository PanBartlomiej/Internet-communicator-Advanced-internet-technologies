package pl.Komunikator.WebSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import pl.Komunikator.post.PostEntity;
import pl.Komunikator.post.PostService;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Klasa obsługująca konwersje wiadomość protokołu STOMP na posty
 */
@Component
public class CustomMessageConverter implements MessageConverter {
    private final ObjectMapper objectMapper;
    @Autowired
    PostService postService;

    public CustomMessageConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Object fromMessage(Message<?> message, Class<?> targetClass) {
        byte[] payload = (byte[]) message.getPayload();
        try {
            return objectMapper.readValue(payload, targetClass);
        } catch (IOException e) {
            throw new RuntimeException("Error converting message payload to object", e);
        }
    }
    @Override
    public Message<?> toMessage(Object payload, MessageHeaders headers) {
        try {
            PostEntity postEntity = (PostEntity) payload;
            byte[] payloadBytes = objectMapper.writeValueAsBytes(postService.toParse(postEntity));
            return new GenericMessage<>(payloadBytes, headers);
        } catch (IOException e) {
            throw new RuntimeException("Error converting object to message payload", e);
        }
    }

    public Object fromMessage(Message<?> message, Class<?> targetClass, Type targetType) {
        return fromMessage(message, targetClass);
    }

    public Message<?> toMessage(Object payload, MessageHeaders headers, Object conversionHint) {
        return toMessage(payload, headers);
    }
}

