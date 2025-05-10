import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

function Navbar() {
  const navigate = useNavigate();
  const isLoggedIn = !!localStorage.getItem('jwt');

  const handleLogout = async () => {
    try {
      await axios.post('http://localhost:8081/api/auth/logout', {}, {
        headers: { Authorization: `Bearer ${localStorage.getItem('jwt')}` }
      });
      localStorage.removeItem('jwt');
      navigate('/login');
    } catch (error) {
      console.error('Logout failed', error);
    }
  };

  return (
    <nav className="navbar">
      <div className="navbar-container">
        <Link to="/" className="navbar-brand">Skillflow</Link>
        <div className="navbar-links">
          {isLoggedIn ? (
            <>
              <Link to="/profile">Profile</Link>
              <button onClick={handleLogout}>Logout</button>
            </>
          ) : (
            <>
              <Link to="/login">Login</Link>
              <Link to="/register">Register</Link>
            </>
          )}
        </div>
      </div>
    </nav>
  );
}

export default Navbar;

