import React from 'react';
import './Header.css';
import { Link } from 'react-router-dom';

function Header({ title, dropdownTab, searchInput, right }) {
  return (
    <nav className='navbar navbar-expand-lg bg-body-tertiary'>
      <div className='Header container-fluid'>
        <a className='navbar-brand' href='/'>
          학습 도우미
        </a>
        <button
          className='navbar-toggler'
          type='button'
          data-bs-toggle='collapse'
          data-bs-target='#navbarSupportedContent'
          aria-controls='navbarSupportedContent'
          aria-expanded='false'
          aria-label='Toggle navigation'
        >
          <span className='navbar-toggler-icon'></span>
        </button>
        <div className='collapse navbar-collapse' id='navbarSupportedContent'>
          <ul className='navbar-nav me-auto mb-2 mb-lg-0'>
            <li className='nav-item'>
              <Link className='nav-link active' aria-current='page' to='/'>로그인</Link>
            </li>
            <li className='nav-item'>
              <Link className='nav-link active' aria-current='page' to='/register'>회원가입</Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}
export default Header;
