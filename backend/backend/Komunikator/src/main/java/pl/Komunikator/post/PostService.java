package pl.Komunikator.post;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * PostService klasa realizuje obsługę logiki biznesowej obsługi zapytań bazodanowych
 */
@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    List<PostEntity> getPostsByChatroomID(Long id){
        return postRepository.getPostsByChatroomID(id);
    }
    PostEntity getPostByID(Long id){
       return postRepository.findById(id).get();
    }

    /**
     * funkcja do parsowania przed ręczną serializacją
     * Potrzebna do specyficznej zamiany postów na message dla protokołu WebSocket
     * @return
     * Zwraca Mapę danych używaną w aplikacji klienckiej
     */
    public Map<String, String> toParse(PostEntity post){
        Map<String, String> data = new HashMap<>();
        data.put("post_id", post.getPost_id().toString());
        data.put("message", post.getMessage());
        data.put("date", post.getDate().toString());
        data.put("owner", post.getOwner().getUser_id());
        data.put("nick", postRepository.getNickByUserID(post.getOwner().getUser_id()));
        data.put("chatroom_id", post.getChatroom_id().getChatroom_id().toString());

        return data;
    }
    void addPost(PostEntity post){
        postRepository.save(post);
    }
}
