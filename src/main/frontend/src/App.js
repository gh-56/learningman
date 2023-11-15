// src/main/frontend/src/App.js

import React, {useEffect, useState} from 'react';
import axios from 'axios';
import Header from './component/Header';
import Login from './component/Login';


function App() {
    const title = "타이틀";
 
    return (
        <div>
          <Header title={title} />
          <Login />
        </div>
    );
}

export default App;