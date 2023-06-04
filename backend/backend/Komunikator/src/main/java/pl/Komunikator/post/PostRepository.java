package pl.Komunikator.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * interface  PostRepository rozszerza JpaRepository
 * reprezentuje repozytorium, dostarcza metody odpowiedzialne za zapytania do bazy danych
 * Realizuje dwa dodatkowe  zapytania wykorzystywane w ChatRoomService
 * do pobierania postów po ID chatroomu
 * do pobierania Nicku osoby o zadanym id(emailu)
 */
@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {
    @Query(value = "SELECT * FROM post p WHERE p.chatroom_id =?1",nativeQuery = true)
    List<PostEntity> getPostsByChatroomID(Long user_id);

    @Query(value = "SELECT u.nick FROM user_1 u WHERE u.user_id = ?1",nativeQuery = true)
    String getNickByUserID(String user_id);
}
