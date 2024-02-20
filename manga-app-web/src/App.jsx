
import WelcomePage from './WelcomePage'
import Header from './components/Header'
import Footer from './components/Footer'
import LogIn from './components/LogIn'
import Signup from './components/Signup'
import {BrowserRouter , Route, Routes} from 'react-router-dom'

function App() {

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path='/' element = {<WelcomePage />}> </Route>
          <Route path='/login' element = {<LogIn />}> </Route>
          <Route path='/signup' element = {<Signup />}> </Route>
        </Routes>
      </BrowserRouter> 
    </>
  )
}

export default App
