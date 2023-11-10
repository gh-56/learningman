// src/main/frontend/src/App.js

import React, {useEffect, useState} from 'react';
import axios from 'axios';

function App() {
   const [hello, setHello] = useState('');
   const [test, setTest] = useState("test");
   const [text, setText] = useState('');

    useEffect(() => {
        axios.get('/api/hello')
        .then(response => setHello(response.data))
        .catch(error => console.log(error))
    }, []);

    return (
        <div>
            백엔드에서 가져온 데이터입니다 : {hello}
            <p>{test}</p>
            <form>
                <input name="text" type="text" value={text}/>
            </form>
        </div>
    );
}

export default App;