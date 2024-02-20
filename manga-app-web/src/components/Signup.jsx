import React, { useState } from 'react'
import {saveUser} from '../service/UserService'
import {useNavigate} from 'react-router-dom'

function Signup() {

 const navigator = useNavigate();
 const [email , setEmail] =  useState('')
 const [username , setUserName] =  useState('')
 const [password , setPassword] =  useState('')
 const [ConfirmPassword , setconfirmPassword] =  useState('')

 const role = 'user';

 const handleEmail = (e) => {
  setEmail(e.target.value)
  };
  const handleUserName = (e) => {
    setUserName(e.target.value)
  };

  const handlePassword = (e) => {
    setPassword(e.target.value)
  };

  const handleConfirmPassword = (e) => {
    setconfirmPassword(e.target.value)
  };

  const handleSignUp = (e) =>{
    e.preventDefault();

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      alert('Please enter a valid email address');
      return;
    }
    
    if (username.length < 3 || username.length > 20) {
      alert('Username must be between 3 and 20 characters');
      return;
    }

    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    if (!passwordRegex.test(password)) {
      alert('Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character');
      return;
    }

    if (password !== ConfirmPassword) {
      alert('Passwords do not match');
      return;
    }

    const user = { username , email , password , role }
    console.log(user)
    saveUser(user).then((response) => {
      console.log(response.data);
    })
    navigator('/login')
  };

  return (
    <div className="bg-image vh-100 d-flex justify-content-center align-items-center">
    <div className='box-container'>
      <div className="linear-layout">
        <div className="linear-layout">
          <h1 className='welcome-text2'>Sign-up</h1>
          <div className='view-divider'></div>
            <div className='linear-layout1'>
              <label htmlFor="email" className='signup-text'>Email</label>
              <div className="input-with-icon">
                <input className='input' type="text" value={email} name='Email' placeholder='email' onChange={handleEmail}/>
                  <span className="icon">
                    <i className="fas fa-lock"></i>
                  </span>
              </div>
            </div>
            <div className='linear-layout1'>
              <label htmlFor="UserName" className='signup-text'>UserName</label>
              <div className="input-with-icon">
                <input className='input' type="text" value={username} name='UserName' placeholder='UserName' onChange={handleUserName}/>
                  <span className="icon">
                    <i className="fas fa-lock"></i>
                  </span>
              </div>
            </div>
            <div className='linear-layout1'>
              <label htmlFor="Password" className='signup-text'>Password</label>
              <div className="input-with-icon">
                <input className='input' type="Password" id="Password" value={password} name='Password' placeholder='Password' onChange={handlePassword}/>
                  <span className="icon">
                    <i className="fas fa-lock"></i>
                  </span>
              </div>
            </div>
            <div className='linear-layout1'>
              <label htmlFor="Confirm Password" className='signup-text'>Confirm Password</label>
              <div className="input-with-icon">
                <input className='input' type="password" id="confirmPassword" value={ConfirmPassword} name='ConfirmPassword' placeholder='Confirm Password' onChange={handleConfirmPassword}/>
                <span className="icon">
                  <i className="fas fa-lock"></i>
                </span>
              </div>
            </div>
            <div className='linear-layout'>
                <button className="btn-custom" onClick={handleSignUp} >Signup</button>
            </div>
        </div>
      </div>
    </div>
  </div>
  )
}

export default Signup