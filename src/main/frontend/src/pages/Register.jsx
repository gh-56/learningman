import axios from "axios";
import React, { useEffect, useState } from "react";
import Card from "../components/Card";

function Register() {
  const baseUrl = "http://localhost:8080";
  const [memberName, setMemberName] = useState("");
  const [memberEmail, setMemberEmail] = useState("");
  const [memberPassword, setMemberPassword] = useState("");

  const formSubmit = async (e) => {
    await axios
      .post(baseUrl + "/members/new", {
        memberName: memberName,
        memberEmail: memberEmail,
        memberPassword: memberPassword,
      })
      .then((res) => {})
      .catch((err) => {
        console.log(err);
      });
  };

  const onChangeHandlerName = (e) => {
    setMemberName(e.target.value);
  };
  const onChangeHandlerEmail = (e) => {
    setMemberEmail(e.target.value);
  };
  const onChangeHandlerPassword = (e) => {
    setMemberPassword(e.target.value);
  };

  return (
    <div>
      <h1>회원가입 페이지</h1>
      <form onSubmit={formSubmit} method="GET">
        <input
          name="name"
          type="text"
          value={memberName}
          onChange={onChangeHandlerName}
        />
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
export default Register;
