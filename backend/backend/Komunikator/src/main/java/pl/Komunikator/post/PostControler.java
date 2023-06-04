package pl.Komunikator.post;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * PostControler klasa obsługuje metody http związane z encją Post
 * Realizuje technologię Cross-origin resource sharing –
 * mechanizm umożliwiający współdzielenie zasobów pomiędzy serwerami znajdującymi się w różnych domenach.
 * obsługuje endpointy http odpowiedzialne za pobieranie i wysyłanie danych do bazy
 */
@RestController
@CrossOrigin
public class PostControler {
    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;
    @GetMapping("/getPostsByChatroomID/{id}")
    List<Map<String, String>> getPosts(@PathVariable Long id) {
        return postService.getPostsByChatroomID(id).stream().map((x) -> postService.toParse(x)).toList();
    }
    @GetMapping("/getPostByID/{id}")
    PostEntity getPostByID(@PathVariable Long id){
        return postService.getPostByID(id);
    }


}
