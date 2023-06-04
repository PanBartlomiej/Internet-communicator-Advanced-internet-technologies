package pl.Komunikator.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * interface  UserRepository rozszerza JpaRepository
 * reprezentuje repozytorium, dostarcza metody odpowiedzialne za zapytania do bazy danych
 * Realizuje  dodatkowe/customowe  zapytanie wykorzystywane w UserService
 * do pobierania urzytkowników po nicku
 */
@Repository
public interface UserRepository extends JpaRepository<User_1,String> {
    @Query("SELECT u FROM User_1 u WHERE u.nick = ?1")
    User_1 getUserByNick(String nick);
}
