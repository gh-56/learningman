// src/main/frontend/src/App.js
import UserProfile from './components/UserProfile';
import Login from './pages/Login';
import Register from './pages/Register';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import axios from 'axios';
import React, { useEffect, useState } from 'react';

function App() {
  const [hello, setHello] = useState('');
  const title = '빈 칸 채우기';

  useEffect(() => {
    axios
      .get('/api/hello')
      .then((response) => setHello(response.data))
      .catch((error) => console.log(error));
  }, []);

  const [message, setMessage] = useState(null);
  const [arr, setArr] = useState([]);

  const callApi = () => {
    const url = 'http://localhost:8080/hello';
    fetch(url)
      .then((response) => response.text())
      .then((text) => {
        console.log(text);
        setMessage(text);
      })
      .catch((e) => console.error(e));
  };

  // axios 사용
  const callApiAxios = () => {
    const url = 'http://localhost:8080/hello';
    axios.get(url).then((response) => setMessage(response.data));
  };

  const callApi2 = () => {
    const url = 'http://localhost:8080/hello2';
    fetch(url)
      .then((response) => response.text())
      .then((text) => {
        console.log(text);
        setMessage(text);
      })
      .catch((e) => console.error(e));
  };

  const callApi3 = () => {
    const url = 'http://localhost:8080/hello3';
    fetch(url)
      .then((response) => response.text())
      .then((text) => {
        console.log(text);
        setMessage(text);
      })
      .catch((e) => console.error(e));
  };

  const callApi3Arr = () => {
    const url = 'http://localhost:8080/hello3';
    fetch(url)
      .then((response) => response.json())
      .then((json) => {
        console.log(json);
        setArr(json);
      })
      .catch((e) => console.error(e));
  };

  return (
    <div className='App'>
      <Router>
        <Routes>
          <Route path='/' element={<Login />} />
          <Route path='/Register' element={<Register />} />
        </Routes>
      </Router>
      백엔드에서 가져온 데이터입니다 : {hello}
      <UserProfile />
      아랫 구문입니다 ===============================
      <button onClick={callApi}>문자열</button>
      <button onClick={callApiAxios}>문자열 Axios</button>
      <button onClick={callApi2}>응답객체+문자열</button>
      <button onClick={callApi3}>응답객체+JSON+텍스트</button>
      <button onClick={callApi3Arr}>응답객체+JSON+JSX 반복으로 풀어보기</button>
      <h1>{message}</h1>
      <h2>{arr.size}</h2>
      {arr.map((item, index) => (
        <li key={index}>{item.key}</li>
      ))}
    </div>
  );
}

export default App;
