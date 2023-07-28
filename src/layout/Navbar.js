import React from 'react';
import './Navbar.css';
import { useLocation } from 'react-router-dom';

function Navbar() {

  const location = useLocation();

  if (location.pathname === '/') {
    return null; // Hides the Navbar component on the login page
  }

  return (
    <div className="navbar">
      <a className="navbar-brand" href="http://localhost:3000/Home" style={{ color: "white" }}>Score Management Software Engineering (SMSE)</a>
      <ul className="menu">
        <li className="menu-item dropdown">
        Add Result
          <ul className="dropdown-menu">
            <li className="dropdown-item"><a href="/AddResult" className="dropdown-link">Add Manually</a></li>
            <li className="dropdown-item"><a href="/uploadExcel" className="dropdown-link">Add with Excel</a></li>
          </ul>
        </li>
        <li className="menu-item dropdown">
          Calendar setting
          <ul className="dropdown-menu">
            <li className="dropdown-item"><a href="/createNewYear" className="dropdown-link">Add New Academic Year</a></li>
            <li className="dropdown-item"><a href="/setCurrentYear" className="dropdown-link">Change Academic Year</a></li>
          </ul>
        </li>
        <li className="menu-item dropdown">
          Edit Information
          <ul className="dropdown-menu">
            <li className="dropdown-item"><a href="/search" className="dropdown-link">Edit student Result</a></li>
            <li className="dropdown-item"><a href="/StudentInfo" className="dropdown-link">Edit Student Information</a></li>
          </ul>
        </li>
        <li className="menu-item dropdown"><a href="/Report" className="menu-link" style={{ color: "white" }}>Report Results</a></li>
      </ul>
    </div>
  );
}

export default Navbar;
