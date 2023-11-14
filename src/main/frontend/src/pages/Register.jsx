import axios from 'axios';
import React, { useEffect, useState } from 'react';

function Register() {
  const [title, setTitle] = useState('');

  useEffect(() => {
    axios
      .get('/api/hello')
      .then((response) => setTitle(response.data))
      .catch((error) => console.log(error));
  }, []);
  return (
    <div>
      <h1>회원가입 페이지</h1>
      <p>{title}</p>
    </div>
  );
}
export default Register;
