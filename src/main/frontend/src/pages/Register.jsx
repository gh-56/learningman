import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";



function Register() {
  const baseUrl = "http://localhost:8080";
  const [memberName, setMemberName] = useState("");
  const [memberEmail, setMemberEmail] = useState("");
  const [memberPassword, setMemberPassword] = useState("");
  const [nameError, setNameError] = useState(null);
  const [emailError, setEmailError] = useState(null);
  const [passwordError, setPasswordError] = useState(null);
  const navigate = useNavigate();

  const formSubmit = async (e) => {
    if (
      memberName === null ||
      memberName === "" ||
      memberEmail === null ||
      memberEmail === "" ||
      memberEmail.includes("@") === false ||
      memberPassword === null ||
      memberPassword === ""
    ) {
      e.preventDefault();
      if (memberName === null || memberName === "") {
        setNameError("이름을 입력해주십시오");
      } else {
        setNameError(null);
      }
      if (memberEmail === null || memberEmail === "") {
        setEmailError("이메일을 입력해주십시오");
      } else if (memberEmail.includes("@") === false) {
        setEmailError("이메일 형식으로 입력해주십시오");
      } else {
        setEmailError(null);
      }
      if (memberPassword === null || memberPassword === "") {
        setPasswordError("비밀번호를 입력해주십시오");
      } else {
        setPasswordError(null);
      }
    } else {
      e.preventDefault();
      await axios
        .post(baseUrl + "/members/register", {
          memberName: memberName,
          memberEmail: memberEmail,
          memberPassword: memberPassword,
        })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
        console.log("test after api")
        navigate('/login');
    }
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
      <form onSubmit={formSubmit}>
        <label>이름</label>
        <input
          name="name"
          type="text"
          value={memberName}
          onChange={onChangeHandlerName}
        />
        {nameError === null ? null : <p>{nameError}</p>}

        <br />
        <label>이메일</label>
        <input
          name="email"
          type="text"
          value={memberEmail}
          onChange={onChangeHandlerEmail}
        />
        {emailError === null ? null : <p>{emailError}</p>}

        <br />
        <label>비밀번호</label>
        <input
          name="password"
          type="text"
          value={memberPassword}
          onChange={onChangeHandlerPassword}
        />
        {passwordError === null ? null : <p>{passwordError}</p>}

        <br />
        <button type="submit">회원가입</button>
      </form>
    </div>
  );
}
export default Register;
