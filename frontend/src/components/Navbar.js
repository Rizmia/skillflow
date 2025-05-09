import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Navbar.css';

const Navbar = () => {
  return (
    <nav className="navbar">
      <h1 className="navbar-brand">SkillFlow</h1>
      <ul className="navbar-links">
        <li><Link to="/">Courses</Link></li>
        <li><Link to="/create">Create Course</Link></li>
      </ul>
    </nav>
  );
};

export default Navbar;