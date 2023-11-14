// src/main/frontend/src/App.js
import Login from './pages/Login';
import Register from './pages/Register';
import Header from './components/Header';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import axios from 'axios';
import React, { useEffect, useState } from 'react';

function App() {
  return (
    <div>
      <Router>
        <Header />
        <Routes>
          <Route path='/' element={<Login />} />
          <Route path='/register' element={<Register />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
