package pl.Komunikator.znajomi;

import org.hibernate.dialect.PostgreSQLPGObjectJdbcType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.Komunikator.user.User_1;

import java.util.List;

/**
 * interface  ZnajomiRepository rozszerza JpaRepository
 * reprezentuje repozytorium, dostarcza metody odpowiedzialne za zapytania do bazy danych
 * Realizuje dwa dodatkowe  zapytania wykorzystywane w ChatRoomService
 * do pobierania znajomych użytkownika po jego ID
 * do pobierania znajomych dla relacji zawartej między dwoma użytkownikami
 */
public interface ZnajomiRepository extends JpaRepository<Znajomi,Long> {
    @Query(value = "SELECT user_id,nick,password FROM user_1 u INNER JOIN ( SELECT z.id_usera_2 as id FROM znajomi z WHERE z.id_usera_1=?1) a ON u.user_id = a.id", nativeQuery = true)
    List<List<String>> findByUser1(String user1);
    @Query(value = "SELECT * FROM znajomi z WHERE z.id_usera_1= ?1 AND z.id_usera_2 = ?2",nativeQuery = true)
    Znajomi findByUsers(String user1,String user2);

}
