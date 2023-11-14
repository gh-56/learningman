import { useNavigate } from 'react-router-dom';

function Login() {
  const nav = useNavigate();

  const goRegister = () => {
    nav('/Register');
  };


  return (
    <div>

      <button onClick={goRegister}>회원가입</button>
    </div>
  );
}
export default Login;
