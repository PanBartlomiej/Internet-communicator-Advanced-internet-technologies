package pl.Komunikator.WebSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import pl.Komunikator.post.PostEntity;
import pl.Komunikator.post.PostRepository;


/**
 * klasa WebSocketController realizuje obsługę punktów wyjścia protokołu WebSocket
 */
@CrossOrigin
@EnableWebSocket
@Controller
public class WebSocketController {
    @Autowired
    private PostRepository postRepository;
    /**
     * MassageMapping to jest endpoint na który serwer wysyła wiadomości a klient z niego czyta
     * a SendTo to jest endpoint na który metoda wysyła widomości (wysyła na kolejke Brokera)
     * @param post Instancja klasy PostEntity, nośnik wiadomości
     * @return
     */
    @MessageMapping("/addPost")
    @SendTo("/topic/posts")
    public PostEntity addPost(PostEntity post) {
        // Save the post to the database
        PostEntity savedPost = postRepository.save(post);
        // Broadcast the newly added post to all subscribers
        return savedPost;
    }
}
