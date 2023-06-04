package pl.Komunikator.post;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import pl.Komunikator.chatroom.ChatRoom;
import pl.Komunikator.user.User_1;

import java.sql.Timestamp;


/**
 * PostEntity realizuje tablę Post w bazie danyhc
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_SEQ")
    @SequenceGenerator(name = "post_SEQ", sequenceName = "post_SEQ", allocationSize = 1)
    @Column(name = "post_id")
    private Long post_id;
    @Column(name = "message", length = 10000 )
    private String message;
    @Column(name = "date")
    private Timestamp date;


    //@JsonBackReference zapobiega zapentleniu sie z zależnościami
    //że pokazuje na użytkownika który ma posty który ma użytkownika który ma posty itd...
    @JsonBackReference(value = "owner_ref")
    @ManyToOne()
    @JoinColumn(name = "owner_id")
   // @JsonProperty("owner")
    private User_1 owner;
    @JsonBackReference(value = "chatroom_ref")
    @ManyToOne()
    @JoinColumn(name = "chatroom_id")
   // @JsonProperty("chatroom_id")
    private ChatRoom chatroom_id;

}
