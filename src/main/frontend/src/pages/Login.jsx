import React, { useEffect, useState } from "react";
import axios from "axios";

function Login() {
  const baseUrl = "http://localhost:8080";
  const [memberEmail, setMemberEmail] = useState("");
  const [memberPassword, setMemberPassword] = useState("");

  const formSubmit = async (e) => {
    await axios
      .post(baseUrl + "/members/new", {
        memberEmail: memberEmail,
        memberPassword: memberPassword,
      })
      .then((res) => {})
      .catch((err) => {
        console.log(err);
      });
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
