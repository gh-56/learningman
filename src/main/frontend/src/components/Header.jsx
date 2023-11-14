import React from 'react';
import './Header.css';

function Header ({ title, dropdownTab, searchInput, right }) {
  
  return (
    <div className='Header'>
      <div className='header_title'> {title} </div>
      <div className='header_dropdownTab'> {dropdownTab} </div>
      <div className='header_searchInput'> {searchInput} </div>
      <div className='header_right'> {right} </div>
    </div>
  );
}
export default Header;
