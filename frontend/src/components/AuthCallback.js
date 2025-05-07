import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function AuthCallback() {
  const navigate = useNavigate();

  useEffect(() => {
    const query = new URLSearchParams(window.location.search);
    const token = query.get('token');

    if (token) {
      localStorage.setItem('jwt', token);
      navigate('/profile');
    } else {
      // Handle missing token (e.g., redirect to login with an error message)
      navigate('/login', { state: { error: 'Authentication failed. Please try again.' } });
    }
  }, [navigate]);

  return null;
}
export default AuthCallback;