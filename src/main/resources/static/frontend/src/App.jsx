/* eslint-disable no-unused-vars */
import { useState } from 'react'
import './App.css'
import RegisterForm from './RegisterForm'
import { RouterProvider, createBrowserRouter } from 'react-router-dom'
import Application from './components/Application'
import LoginForm from './view/LoginForm'

function App() {
  const [count, setCount] = useState(0)

  const router = createBrowserRouter([
    {
      path: '/',
      element: <Application />,
      children: [
        {
          path: '/register',
          element: <RegisterForm />,
        },
        {
          path: '/login',
          element: <LoginForm />,
        },
      ],
    },
  ]);

  return <RouterProvider router={router} />
}

export default App
