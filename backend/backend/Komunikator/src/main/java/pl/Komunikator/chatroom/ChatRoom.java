package pl.Komunikator.chatroom;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;
import pl.Komunikator.post.PostEntity;
import pl.Komunikator.znajomi.Znajomi;

import java.util.List;


/**
 * ChatRoom reprezentuje encje chatroom w bazie danych
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "chatroom")
@JsonDeserialize(using = ChatRoomDeserializer.class)
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatroom_SEQ")
    @SequenceGenerator(name = "chatroom_SEQ", sequenceName = "chatroom_SEQ", allocationSize = 1)
    @Column(name = "chatroom_id")
    private Long chatroom_id;

    @Column(name ="nazwa")
    private String nazwa;

    public ChatRoom(String nazwa, List<Znajomi> id_relacji, List<PostEntity> posty) {
        this.nazwa = nazwa;
        this.id_relacji = id_relacji;
        this.posty = posty;
    }

    @OneToMany(mappedBy = "id_relacji")
    @Column(name = "id_relacji")
    private List<Znajomi> id_relacji;

    @JsonManagedReference(value = "chatroom_ref")
    @OneToMany(mappedBy= "chatroom_id")
    private List<PostEntity> posty;

    public ChatRoom(Long chatroom_id) {
        this.chatroom_id=chatroom_id;
    }

    /**
     * metoda potrzebna do ręcznej serializacji
     * @param chatroom_id
     * @return
     */
    public static ChatRoom fromRoomId(Long chatroom_id) {
        return new ChatRoom(chatroom_id);
    }

}
