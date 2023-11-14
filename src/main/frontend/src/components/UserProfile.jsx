import React, { useRef, useState } from 'react';

function UserProfile() {
  /* 저장된 상태의 배열 */
  const [상태배열, 상태배열변경함수] = useState([
    { id: 1, name: '홍길동', email: 'a@b.com', age: '30' },
    { id: 2, name: '홍홍홍', email: 'c@d.com', age: '50' },
  ]);

  /* 입력창에서 입력받은 정보를 업데이트하는 상태 */
  const [프로필정보, 프로필정보변경함수] = useState({
    name: '',
    email: '',
    age: '',
  });

  const onChangeHandler = (event) => {
    프로필정보변경함수((프로필정보) => ({
      ...프로필정보,
      [event.target.name]: event.target.value,
    }));
  };
  const idRef = useRef(3);
  const onSave = () => {
    const 새프로필객체 = {
      id: idRef.current,
      ...프로필정보,
    };
    상태배열변경함수(
      /* 기존배열에 새로운 프로필객체 추가한 새 배열 */
      (이전상태배열) => [새프로필객체, ...이전상태배열]
    );
    idRef.current += 1;
  };
  const onDelete = (id) => {
    상태배열변경함수((이전상태배열) =>
      /* 해당id만 필터링된 새 배열 */
      이전상태배열.filter((프로필객체) => 프로필객체.id !== id)
    );
  };
  return (
    <div>
      <h2>유저 프로필</h2>
      <div>
        <label>이름 : </label>
        <input name='name' value={프로필정보.name} onChange={onChangeHandler} />
      </div>
      <div>
        <label>email : </label>
        <input
          name='email'
          value={프로필정보.email}
          onChange={onChangeHandler}
        />
      </div>
      <div>
        <label>나이 : </label>
        <input name='age' value={프로필정보.age} onChange={onChangeHandler} />
      </div>
      <button onClick={onSave}>저장</button>
      <h3>유저 정보</h3>
      {상태배열.map((프로필객체) => (
        <div key={프로필객체.id}>
          <p>이름: {프로필객체.name}</p>
          <p>email: {프로필객체.email}</p>
          <p>나이: {프로필객체.age}</p>
          <button onClick={() => onDelete(프로필객체.id)}>삭제</button>
        </div>
      ))}
    </div>
  );
}

export default UserProfile;
