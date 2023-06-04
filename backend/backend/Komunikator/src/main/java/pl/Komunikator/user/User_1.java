package pl.Komunikator.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;
import pl.Komunikator.post.PostEntity;
import pl.Komunikator.znajomi.Znajomi;

import java.util.List;

/**
 * klasa User_1 odowiada tabeli User_1 w bazie danych
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "User_1")
@JsonDeserialize(using = UserDeserializer.class)
public class User_1 {
    public User_1(String user_id, String nick, String password) {
        this.user_id = user_id;
        this.nick = nick;
        this.password = password;
    }

     //* przez email bedzie odbywalo sie logowanie do strony
     //* adres email jest z definicji unikalny więc wykorzystywany jest jako ID
    @Id
    @Column(name = "user_id", unique = true)
    private String user_id;
    @Column(name = "nick", unique = true)
    private String nick;
    @Column(name = "password")
    private String password;
    @JsonManagedReference(value = "owner_ref")
    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    private List<PostEntity> posts;

    @OneToMany(mappedBy = "id_usera_1",cascade = CascadeType.ALL)
    private List<Znajomi> znajomi;

    //serializacja
    public User_1(String user_id) {
        this.user_id = user_id;
    }
    public static User_1 fromEmail(String email) {
        return new User_1(email);
    }
    public static User_1 fromEmail2(String user_id, String nick, String password) {
        return new User_1(user_id,nick,password);
    }
}
