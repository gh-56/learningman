import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function Login() {
  const baseUrl = "http://localhost:8080";
  const [memberEmail, setMemberEmail] = useState("");
  const [memberPassword, setMemberPassword] = useState("");
  const [params, setParams] = useState("");
  const navigate = useNavigate();

  const formSubmit = async (e) => {
    e.preventDefault();
    await axios
      .post(baseUrl + "/members/login", {
        memberEmail: memberEmail,
        memberPassword: memberPassword,
      })
      .then(response => {
        setParams(response.data)
        console.log(params);
      })
      .catch((err) => {
        console.log(err);
      });
      navigate('/');
  };

  const onChangeHandlerEmail = (e) => {
    setMemberEmail(e.target.value);
  };
  const onChangeHandlerPassword = (e) => {
    setMemberPassword(e.target.value);
  };

  return (
    <div>
      <form onSubmit={formSubmit} method="GET">
        <input
          name="email"
          type="text"
          value={memberEmail}
          onChange={onChangeHandlerEmail}
        />
        <input
          name="password"
          type="text"
          value={memberPassword}
          onChange={onChangeHandlerPassword}
        />
        <button type="submit">제출</button>
      </form>
    </div>
  );
}

export default Login;
