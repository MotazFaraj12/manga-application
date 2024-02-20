import React , { useState } from 'react'
import {useNavigate} from 'react-router-dom'
import {authenticate} from '../service/UserService'
function LogIn() {

  const navigator = useNavigate();
  const [email , setEmail] =  useState('')
  const [password , setPassword] =  useState('')

  const handleEmail = (e) => {
    setEmail(e.target.value)
  };

  const handlePassword = (e) => {
    setPassword(e.target.value)
  };

  const handleLogIn = (e) =>{
    e.preventDefault();
    const user = { email , password}
    console.log(user)
    authenticate(user).then((response) => {
      console.log(response.data);
    })
    navigator('/Home')
  };


  return (
    <div className="bg-image vh-100 d-flex justify-content-center align-items-center">
    <div className='box-container'>
      <div className="linear-layout">
        <div className="linear-layout">
          <h1 className='welcome-text2'>Log-in</h1>
          <div className='view-divider'></div>
            <div className='linear-layout1'>
              <label htmlFor="email" className='signup-text'>Email:</label>
              <div className="input-with-icon">
                <input className='input' type="text" value={email} name='email' placeholder='email' onChange={handleEmail}/>
                  <span className="icon">
                    <i className="fas fa-lock"></i>
                  </span>
              </div>
            </div>
            <div className='linear-layout1'>
              <label htmlFor="Password" className='signup-text'>Password:</label>
              <div className="input-with-icon">
                  <input className='input' type="Password" id="Password" value={password} name='Password' placeholder='Password' onChange={handlePassword}/>
                  <span className="icon">
                    <i className="fas fa-lock"></i>
                  </span>
              </div>
            </div>

            <div className='linear-layout'>
                <button className="btn-custom" onClick={handleLogIn}>LogIn</button>
            </div>
        </div>
      </div>
    </div>
  </div>
  )
}

export default LogIn