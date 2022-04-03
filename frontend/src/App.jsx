import { useState } from 'react'
import { ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css';
import './App.css'
import Signup from './components/Signup'
import { BrowserRouter, Route, Link, Routes, Navigate } from "react-router-dom";
import Login from './components/Login';
import Navbar from './components/Navbar';
import Dashboard from './components/Dashboard';

function App() {

  const [customerNo, setCustomerNo] = useState(undefined);
  const [ticket, setTicket] = useState(undefined);
  const [loggedIn, setLoggedIn] = useState(false);

  function logout(e){
    e.preventDefault();
    setTicket(undefined);
    setCustomerNo(undefined);
    setLoggedIn(false);
    window.location.href = window.location.origin;
  }

  return (
    <div>
      <BrowserRouter>
        <ToastContainer></ToastContainer>
        {/*<div className="button" onClick={()=>console.log(ticket+" "+customerNo)}>debug</div> */}
        <Navbar logoutFunc={logout} loggedIn={{"value": loggedIn}}></Navbar>
        <Routes>
          <Route path='/' element={ loggedIn ? <Navigate to="/dashboard"></Navigate> : <Navigate to="/login"></Navigate> }></Route>
          <Route path="/login" element={<Login ticket={{"value": ticket, "set": setTicket}} customerNo={{"value":customerNo, "set":setCustomerNo}} loggedIn={{"value": loggedIn, "set": setLoggedIn}} ></Login>}>
          </Route>
          <Route path="/signup" element={<Signup loggedIn={{"value": loggedIn}} />}>
          </Route>
          <Route path='/dashboard' element={ <Dashboard loggedIn={{"value": loggedIn}} ticket={{value: ticket}} customerNo={{value: customerNo}} ></Dashboard> }></Route>
        </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App
