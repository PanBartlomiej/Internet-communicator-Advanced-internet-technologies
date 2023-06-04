import React, { useState , useEffect } from "react";
import Logowanie from "./Logowanie";
import RegistrationPage from "./RegistrationPage";
import ChatRoom from "./ChatRoom";
//import '../style/index.css'

function StartWeb() {
    const [page, setPage] = useState('login');
  

    useEffect(() => {
      if(localStorage.getItem("sessionToken")==="token")
      setPage('zalogowany')
    }, []);

    const handleLoginClick = () => {
      if(localStorage.getItem("sessionToken")!=="token"){  
      setPage('login');
      }
      else{
        
    localStorage.setItem('sessionToken'," " );
        setPage('logout');
      }
    };
  
    const handleRegisterClick = () => {
      if(localStorage.getItem("sessionToken")!=="token"){  
        setPage('register');
        }
        else{
          
    localStorage.setItem('sessionToken'," " );
          setPage('logout');
        }
    };
    const handleClick = () => {
      setPage('login');
    };
    const zostan =()=>{
      setPage('zalogowany');
      localStorage.setItem('sessionToken',"token" );

    }
    return (
      <div>
        { page==="logout" ?(
          <div class="logout-container">
            <span>
              Wyloguj <button onClick={handleClick}>Wyloguj</button>
            </span>
            <p>
              Wróć do aplikacji <button onClick={zostan}>Zostań</button>
            </p>
          </div>

        )
        :(
      <div>
        <p>
       
           
            
            <div class="title">
            <span >
                 Masz już konto? &ensp;<button onClick={handleLoginClick}>zaloguj się</button>
            </span>
            <span id="tytul">Komunikator Internetowy</span>
            <span>
              <button onClick={handleRegisterClick}>zarejestruj się</button> &ensp;  Nie masz konta? 
            </span>
            </div>
          
          </p>
          
        {page === 'login' ? (
          <Logowanie />
        ) : page === "zalogowany" ? <ChatRoom username={localStorage.getItem("username")} nick={localStorage.getItem("nick")}/>
        : (
          <RegistrationPage />
        )}        
          </div>)
          }
          </div>
    )}
          
export default StartWeb;

