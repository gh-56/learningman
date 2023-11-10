// src/main/frontend/src/App.js

import React, {useEffect, useState} from 'react';
import axios from 'axios';

function App() {
    const baseUrl = "http://localhost:8080";
   const [name, setName] = useState('');
   const [email, setEmail] = useState('');
   const [password, setPassword] = useState('');


    const formSubmit = async (e) => {
            await axios
            .post(baseUrl + "/members", {
                name : name,
                email : email,
                password : password
            })
            .then((res) => {
            })
            .catch((err) => {
                console.log(err);
            })
    }

    const onChangeHandlerName = (e) => {
        setName(e.target.value)
    }
    const onChangeHandlerEmail = (e) => {
        setEmail(e.target.value)
    }
    const onChangeHandlerPassword = (e) => {
        setPassword(e.target.value)
    }
 
    return (
        <div>
            <form onSubmit={formSubmit}>
                <input name="name" type="text" value={name} onChange={onChangeHandlerName}/>
                <input name="email" type="text" value={email} onChange={onChangeHandlerEmail}/>
                <input name="password" type="text" value={password} onChange={onChangeHandlerPassword}/>
                <button type='submit'>제출</button>
            </form>
        </div>
    );
}

export default App;