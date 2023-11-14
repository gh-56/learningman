import Header from '../components/Header';
import { useNavigate } from 'react-router-dom';

function Register() {
  const nav = useNavigate();

  const goLogin = () => {
    nav('/');
  };

  const title = '회원가입 페이지';
  return (
    <div>
      <Header title={title} />
      <button onClick={goLogin}>로그인으로 이동</button>
    </div>
  );
}
export default Register;
