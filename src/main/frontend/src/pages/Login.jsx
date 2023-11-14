import Header from '../components/Header';
import { useNavigate } from 'react-router-dom';

function Login() {
  const nav = useNavigate();

  const goRegister = () => {
    nav('/Register');
  };

  const filler = '빈 칸 채우기';
  return (
    <div>
      <Header
        title={filler}
        dropdownTab='reveal yourself'
        searchInput={filler}
        right={filler}
      />
      <button onClick={goRegister}>회원가입</button>
    </div>
  );
}
export default Login;
