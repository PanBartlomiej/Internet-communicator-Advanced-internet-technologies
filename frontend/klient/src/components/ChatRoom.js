import { useState, useEffect  } from 'react'
import axios from 'axios';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import '../style/easyStyle.css'
function ChatRoom(props){
const [friends, setFriends] = useState([]);
const [rooms, setRooms] = useState([]);
const [currentRoom, setCurrentRoom] = useState(null);
const [content, setContent] = useState('');
const [email, setEmail] = useState('');
const [message, setMessage] = useState('');
const [posts, setPosts] = useState([]);
const [ChatRoomName, setChatRoomName] = useState('');
const [checkedItems, setCheckedItems] = useState([]);
const [stompClient, setStompClient] = useState(null);
const [userData, setUserData] = useState({
  username: props.username,
  nick: props.nick,
  connected: false
});


useEffect(() => {
  axios.get('http://localhost:8080/GetFriends/'.concat(userData.username))
    .then(response => {
      setFriends(response.data);
    })
    .catch(error => {
      console.log(error);
    });
}, []);

function handleEmailChange(event) {
  setEmail(event.target.value);
}


  const getRooms = async () => {
    try {
      const response = await axios.get('http://localhost:8080/getChatRoomsByUserId/'.concat(userData.username));

      setRooms(response.data);
      console.log(response.data);

    } catch (error) {
      console.error(error);
    }
  };
  useEffect(() => {
    getFriends();
    getRooms();
  }, []);

  /**
   * znajomi
   */
  const getFriends = async () => {
    try {
      
      const response = await axios.get('http://localhost:8080/GetFriends/'.concat(userData.username));
      setFriends(response.data);
    } catch (error) {
      console.error(error);
    }
  };
  const addFriend = async () => {
    try {
        setContent('dodajZnajomego');
  
    } catch (error) {
      console.error(error);
    }
  };

 function handleAddFriend(event) {
    const user1_id_user_id = userData.username;
    const user2_id_user_id = email;
    event.preventDefault();
    axios.post('http://localhost:8080/AddFriend', user1_id_user_id+";"+user2_id_user_id
    , {
      headers: {
        'Content-Type': 'text/plain'
      }
    })  
      .then(response => {
        console.log(response.data);
        setEmail('');
        console.log(response.data);
        setFriends(response.data);
        alert("dodano pomyślnie")
      })
      .catch(error => {
       alert("nie udało się dodać znajomego");
        console.log(error);
      });
  }

  const handleChangeZnajomiCheckboxlist = (event) => {
    const isChecked = event.target.checked;
    const itemId = event.target.value;

    if (isChecked) {
      setCheckedItems((prevItems) => [...prevItems, itemId]);
    } else {
      setCheckedItems((prevItems) => prevItems.filter((id) => id !== itemId));
    }
  };
  const setChatRoomName2 = (event) =>{
    const value = event.target.value;
    setChatRoomName(value);
  }

  
function deleteFriend(user_id) {
  const user1_id_user_id = userData.username;
    const user2_id_user_id = user_id;
  axios.post('http://localhost:8080/DeleteFriend', user1_id_user_id+";"+user2_id_user_id
  , {
    headers: {
      'Content-Type': 'text/plain'
    }
  
  })  
    .then(response => {
      setFriends(response.data);
    })
    .catch(error => {
      // Obsługa błędu
      console.error('Wystąpił błąd podczas usuwania znajomego:', error);
    });
}

  /**
   * 
   * CHatroomy
   * @param {*} event 
   */
  const handleAddChatroom = async (event) => {

    event.preventDefault();

    console.log({
      nazwa:ChatRoomName,
      user: userData.username,
      id_relacji: checkedItems,
      });
    try {
      const response = await axios.post('http://localhost:8080/AddChatroom', {
      nazwa:ChatRoomName,
      user: userData.username,
      id_relacji: checkedItems,
      });
      console.log('Response:', response.data);
     // setChatRoomName(response.data);
      alert("dodano pomyślnie");
      getRooms();
      
    } catch (error) {
      console.error('Error:', error);
      alert("wystąpił nieoczekiwany błąd");
    }
  };
  const addRoom = async () =>{
    setContent('dodajChatroom');
  }
/**
 * POSTY
 * 
 */



useEffect(() => {
  const socket = new SockJS('http://localhost:8080/ws');
  const client = Stomp.over(socket);
  client.connect({}, () => {
    setStompClient(client);
  });

  return () => {
    if (stompClient) {
      stompClient.disconnect();
    }
  };
}, []);

useEffect(() => {
  if (!stompClient) return;

  const subscription = stompClient.subscribe('/topic/posts', (message) => {
    const receivedPost = JSON.parse(message.body);
    setPosts((prevPosts) => [...prevPosts, receivedPost]);
  });

  return () => {
    subscription.unsubscribe();
  };
}, [stompClient]);


const fetchPosts = async (chatroom_id_1) => {
  try {
    const response = await axios.get('http://localhost:8080/getPostsByChatroomID/'.concat(chatroom_id_1));
    setPosts(response.data);
  } catch (error) {
    console.error('Error fetching posts:', error);
  }
};
  useEffect(() => {
    // Fetch initial posts from the backend
    const fetchPosts = async () => {
      try {
        const response = await axios.get('http://localhost:8080/getPostsByChatroomID/'.concat(currentRoom.chatroom_id));
        setPosts(response.data);
      } catch (error) {
        console.error('Error fetching posts:', error);
      }
    };

    fetchPosts();
  }, []);

  const addPost = () => {
    const post = {
      message: message,
      date: new Date(),
      owner: userData.username, 
      chatroom_id: currentRoom.chatroom_id, 
    };
 
    stompClient.send('/app/addPost', {}, JSON.stringify(post));
    
    setMessage('');
  };

  

  /**
   * KOD HTML'A
   */

  return (
    <div id="container">
  
      <div id="left">
        <div class="containerGreen">
      <h2>Znajomi</h2>
      <ul>
        {friends.map((friend) => (
          <li key={friend.user_id}>
            <span class="user-id">{friend.user_id}</span>
            <br/>
            <span class="nick">{friend.nick}</span>
          
            <button class="del" onClick={() => deleteFriend(friend.user_id)}>Usuń</button>
          
          </li>
        ))}
      </ul>
      <button id="DodajZnajomegobutton" onClick={addFriend}>Dodaj znajomego</button>
      </div>
    </div>



       <div id ='center'>
        {content === "chatroom" ? ( <div>
         {currentRoom ? (
//Chatroom


<div class="chat-container">
  <h2 class="room-name">{currentRoom.nazwa}</h2>
  <div class='scrollCenter'>
  <div class="friend-list-container">
    <ul class="friend-list">
      {currentRoom.id_relacji.map((friend) => (
        <li key={friend.relacja}>{friend.user2_id}</li>
      ))}
    </ul>
  </div>
  <div class="message-list-container">
    <ul class="message-list">
      {posts.map((post, index) => (
        <li key={index} >
          <div class="message-info">
            <div class="message-owner">{post.owner} -
            <span id="postNickStyle"> {post.nick}</span></div>
            <div class="message-date">{post.date}</div>
          </div>
          <div class="message-content">{post.message}</div>
        </li>
      ))}
    </ul>
  </div>
  </div>
  <div class="input-container">
    <textarea
      class="message-input"
      placeholder="Dodaj post"
      required="required"
      pattern="[A-Za-z0-9]{1,20}"
      value={message}
      onChange={(e) => setMessage(e.target.value)}
    />
    <button class="add-post-button" onClick={addPost}>Dodaj post</button>
  </div>
</div>




         ) : 
         //wybieranie pokoju 
         (
           <p>Wybierz pokój</p>
         )}
         </div> ): 
         (<div>{content === "dodajZnajomego"?(
          <div class="add-friend-container">
          <form onSubmit={handleAddFriend}>
            <label>
              Email:
              <input type="text" value={email} onChange={handleEmailChange} />
            </label>
            <br />
            <button type="submit" class="add-friend-button">Dodaj Znajomego</button>
          </form>
        </div>)
         : content==="dodajChatroom"?
          <div>
            <form onSubmit={handleAddChatroom} class="chat-form">
            <label>
              nazwa chatroom'u:
              <br/>
              <input type="text" onChange={setChatRoomName2} />
            </label>
            <br />
            <div class="friend-list-container">
              {friends.map((item) => (
                <label key={item.user_id}>
                  <input
                    type="checkbox"
                    value={item.user_id}
                    checked={checkedItems.includes(item.user_id)}
                    onChange={handleChangeZnajomiCheckboxlist}
                  />
                  {item.nick}
                </label>
              ))}
            </div>
            <button type="submit" class="add-chatroom-button">Dodaj ChatRoom</button>
          </form>

         </div>
         :<div><h2 class="room-name">Witaj  {userData.nick} !</h2>
          <p>Komunikator internetowy to aplikacja umożliwiająca użytkownikom komunikację w czasie rzeczywistym za pomocą Internetu.
             Projekt oparty jest na technologiach Java Spring Boot, React, WebSocket, HTTP i PostgresSQL. 
            Celem projektu jest stworzenie platformy, która umożliwi użytkownikom swobodną wymianę wiadomości,
             prowadzenie rozmów grupowych i zapewni bezpieczne przechowywanie danych.</p>
         </div>}
        </div>)
}
       </div>
    <div id='right'>
         <h2>Pokoje</h2>
         <ul>
           {rooms.map((room) => (
             <li key={room.chatroom_id} onClick={() => {setCurrentRoom(room) ; fetchPosts(room.chatroom_id); setContent("chatroom"); } }>
               {room.nazwa}
             </li>
           ))}
         </ul>
         <button onClick={addRoom}>Dodaj pokój</button>
       </div>
</div>
   );         
}
export default ChatRoom;