import React, { useState } from 'react';
import './login.css';
import { useNavigate } from 'react-router-dom';


function UniqueLoginForm() {
  const [userName, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();


  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  }

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    fetch('http://localhost:8080/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        userName: userName,
        password: password
      })
    })
    .then(response => {
      if (response.ok) {
        navigate('/Home')
      } else {
        setErrorMessage("Invalid username or password");
      }
    })
    .catch(error => console.error('Error:', error));
  }

  return (
    <>
      <head>
        <link rel="stylesheet" href="./login.css"/>
      </head>
      <body>
        <div className="container">
          <div className="row">
            <div className="col-md-offset-5 col-md-4 text-center">
              <div className="form-login">
                <h4>SMSE application Login</h4>
                <br />
                <form onSubmit={handleSubmit}>
                  <input type="text" id="userName" className="form-control input-sm chat-input" placeholder="username" value={userName} onChange={handleUsernameChange} />
                  <br /><br />
                  <input type="password" id="userPassword" className="form-control input-sm chat-input" placeholder="password" value={password} onChange={handlePasswordChange} />
                  <br /><br />
                  {errorMessage && <div>{errorMessage}</div>}
                  <div className="wrapper">
                    <span className="group-btn">
                      <button type="submit" className="btn btn-danger btn-md">
                        login <i className="fa fa-sign-in"></i>
                      </button>
                    </span>
                  </div>
                </form>
              </div>
            </div>
          </div>
          <br /><br /><br />
        </div>
      </body>
    </>
  );
}

export default UniqueLoginForm;
