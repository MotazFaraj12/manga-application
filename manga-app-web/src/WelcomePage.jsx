import React from 'react'
import {useNavigate} from 'react-router-dom'

function WelcomePage() {
  const navigator = useNavigate();
  const handleLogin = () => {
    // Handle login logic
    console.log('Redirecting to login page...');
    navigator('/login')
  };

  const handleSignup = () => {
    // Handle signup logic
    console.log('Redirecting to signup page...');
    navigator('/signup')
  };

  return (
    <div className="bg-image vh-100 d-flex justify-content-center align-items-center">
      <div className='box-container'>
        <div className="linear-layout">
          <div className="linear-layout">
            <h1 className='welcome-text2'>Welcome to T-Manga!</h1>
            <div className='view-divider'></div>
            <p className='welcome-text'>Alraedy have account?</p>
            <button className="btn-custom" onClick={handleLogin}>Login</button>
          </div>
          <div className="linear-layout">
            <p className='welcome-text'>Create an account</p>
            <button className="btn-custom" onClick={handleSignup}>Signup</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default WelcomePage