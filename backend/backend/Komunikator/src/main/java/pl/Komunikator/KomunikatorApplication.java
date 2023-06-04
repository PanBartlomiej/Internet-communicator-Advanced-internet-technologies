package pl.Komunikator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *  Klasa  KomunikatorApplication stanowi główny punkt wejścia programu
 *  uruchamia aplikację SprinBoota.
 *
 *  Kod wykorzystany do utworzenia bazy danych z klas projektu został zakomentowany
 *   kod ten wykorzystuje instancję klasy EntityManager. konifugracja EntityManagerFactory znajduje się
 *   w pliku src/main/resources/META-INF/persistence.xml
 *
 *  W celu zresetowania bazy danych do stanu wyściowego można odkomentować cały kod oraz zakomentować
 *    linię:
 *    		SpringApplication.run(KomunikatorApplication.class, args);
 *    a następnie uruchomić projekt
 *     @author Bartłomiej Leśnicki
 *     @version 1.3;
 */
@SpringBootApplication
public class KomunikatorApplication {
	public static void main(String[] args) {
			SpringApplication.run(KomunikatorApplication.class, args);

//
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//		User_1 user1 = new User_1("michalAnioł@pocztowykod.com","Michał","P@ssword",new ArrayList<>(),new ArrayList<>());
//		User_1 user2 = new User_1("NowyEmmm@gmail.com","Wiktor2","123abc");
//		Znajomi znajomi = new Znajomi(user1, user2);
//
//		ArrayList<Znajomi> arrayList = new ArrayList<>();
//		arrayList.add(znajomi);
//		ChatRoom chatRoom = new ChatRoom("chatroooooom",arrayList,new ArrayList<>());
//
//
//		PostEntity post1 = new PostEntity();
//		post1.setMessage("Hello, world!");
//		post1.setDate(new Timestamp(System.currentTimeMillis()));
//		post1.setOwner(user1);
//		post1.setChatroom_id(chatRoom);
//
//		PostEntity post2 = new PostEntity();
//		post2.setMessage("I'm enjoying this conversation!");
//		post2.setDate(new Timestamp(System.currentTimeMillis()));
//		post2.setOwner(user2);
//		post2.setChatroom_id(chatRoom);
//
//		entityManager.getTransaction().begin();
//		entityManager.persist(user1);
//		entityManager.persist(user2);
//		entityManager.persist(znajomi);
//		entityManager.persist(chatRoom);
//		entityManager.persist(post1);
//		entityManager.persist(post2);
//		entityManager.getTransaction().commit();
//		entityManager.close();
//		entityManagerFactory.close();

	}
}
