import { useState } from 'react'
import { ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css';
import './App.css'
import Signup from './components/Signup'

function App() {

  return (
    <div>
      <ToastContainer></ToastContainer>
        <Signup></Signup>
    </div>
  )
}

export default App
