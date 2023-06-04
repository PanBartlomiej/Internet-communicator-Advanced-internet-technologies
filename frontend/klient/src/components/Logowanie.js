import React, { useState } from 'react';
import ChatRoom from './ChatRoom';
//import '../style/index.css'
import axios from 'axios';

function LoginPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [userData, setUserData] = useState(null);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleSubmit = async (event) => {
    event.preventDefault();
    
    try {
      const response = await axios.get(`http://localhost:8080/getUserById/${username}`);
      const data = response.data;
      console.log(data);
  
      if (data === "") {
        alert("nieprawidłowy login lub hasło");
      } else {
        setUserData(data);
        
        if (data.password === password) {
          setIsLoggedIn(true);  
        localStorage.setItem('sessionToken',"token" );
        localStorage.setItem('username',username);
        localStorage.setItem('nick',data.nick);
        } else {
          setIsLoggedIn(false);
          alert("nieprawidłowy login lub hasło");
        }
      }
    } catch (error) {
      console.error(error);
      
      alert("nieprawidłowy login lub hasło");
    }
  };

  if (isLoggedIn) {
    return <ChatRoom username={username} nick={userData.nick}/>;
  }

  return (
    <div class ="conteiner">
    <div class="outside"> 
    <div class="login-container">
      <h1>Logowanie</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Email:
          <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
        </label>
        <br />
        <label>
          Hasło:
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
        </label>
        <br />
        <button type="submit" class="login-button">Submit</button>
      </form>
      {/* {userData && (
        <div class="user-data-container">
          <h2>User Data</h2>
          <p>Username: {userData.username}</p>
          <p>Email: {userData.email}</p>
          <p>Age: {userData.age}</p>
        </div>
      )} */}
    </div>
    </div>
    </div>
  );
}

export default LoginPage;
