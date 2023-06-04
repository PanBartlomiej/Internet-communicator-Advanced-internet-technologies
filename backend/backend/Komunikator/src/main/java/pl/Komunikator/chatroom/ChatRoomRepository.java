package pl.Komunikator.chatroom;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * interface  ChatRoomRepository rozszerza JpaRepository
 * reprezentuje repozytorium, dostarcza metody odpowiedzialne za zapytania do bazy danych
 */
@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long>  {
//    @Query(value = "SELECT cr.chatroom_id, cr.nazwa " +
//            "FROM chatroom cr " +
//            "JOIN znajomi z ON z.relacja= cr.chatroom_id " +
//            "JOIN user_1 u ON (z.id_usera_1 = u.user_id OR z.id_usera_2 = u.user_id) " +
//            "WHERE u.user_id= ?1",nativeQuery = true)
//    List<ChatRoom> getChatRoomsByUserId(String user_id);
}
