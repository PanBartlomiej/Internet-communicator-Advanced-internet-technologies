import React, { useState } from 'react';
import axios from 'axios';
import ChatRoom from './ChatRoom';
function RegistrationPage() {
  const [nick, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const [isLoggedIn, setIsLoggedIn] = useState(false);
  if (isLoggedIn) {
    return <ChatRoom username={email} nick={nick}/>;
  }
  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    axios.post('http://localhost:8080/addUser', {
      user_id:email,  
      nick,
      password
    })
    .then(response => {
      if( response.status === 200){
        setIsLoggedIn(true);
    
        localStorage.setItem('sessionToken',"token" );
        localStorage.setItem('username',email);
        
        localStorage.setItem('nick',nick);
      }
      console.log(response);
    })
    .catch(error => {
      console.log(error);
    });
  };


  return (
    <div class="conteiner">
    <div class="outside">
    <div class="register-container">
      <h1>Rejestracja</h1>
      <form onSubmit={handleSubmit}>
        <div class="form-group">
          <label htmlFor="nick">Nick:</label>
          <input type="text" id="nick" value={nick} onChange={handleUsernameChange} />
        </div>
        <div class="form-group">
          <label htmlFor="email">Email:</label>
          <input type="email" id="email" value={email} onChange={handleEmailChange} />
        </div>
        <div class="form-group">
          <label htmlFor="password">Hasło:</label>
          <input type="password" id="password" value={password} onChange={handlePasswordChange} />
        </div>
        <button type="submit" class="register-button">Register</button>
      </form>
    </div>
    </div>
    </div>
  );
}

export default RegistrationPage;