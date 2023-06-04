package pl.Komunikator.znajomi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import pl.Komunikator.user.User_1;

/**
 *  Klasa Znajomi odwzorowuje encję znajomi w bazie danych
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "znajomi"
//        , uniqueConstraints =
//        {@UniqueConstraint(columnNames = {"id_usera_1", "id_usera_2"} ),
//        @UniqueConstraint(columnNames = {"id_usera_2","id_usera_1"})
//        }  // Zrezygnowano z tej unikalności ze wzglęu na funkcjonalność
        )

public class Znajomi {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "znajomi_SEQ")
    @SequenceGenerator(name = "znajomi_SEQ", sequenceName = "znajomi_SEQ", allocationSize = 1)
    @JoinColumn()
    @Column(name = "id_relacji")
    private Long id_relacji;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_usera_1", referencedColumnName = "user_id")
    private User_1 id_usera_1;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_usera_2", referencedColumnName = "user_id")
    private User_1 id_usera_2;

    public Znajomi(User_1 id_usera_1, User_1 id_usera_2) {
        this.id_usera_1 = id_usera_1;
        this.id_usera_2 = id_usera_2;
    }
}
