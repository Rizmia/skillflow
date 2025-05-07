// import { useState, useEffect } from 'react';
// import axios from 'axios';
// import { useNavigate } from 'react-router-dom';

// function Profile() {
//   const [user, setUser] = useState(null);
//   const [error, setError] = useState('');
//   const navigate = useNavigate();

//   useEffect(() => {
//     const fetchProfile = async () => {
//       try {
//         const response = await axios.get('http://localhost:8081/api/users/profile', {
//           headers: { Authorization: `Bearer ${localStorage.getItem('jwt')}` }
//         });
//         setUser(response.data);
//       } catch (err) {
//         setError('Failed to load profile');
//         navigate('/login');
//       }
//     };
//     fetchProfile();
//   }, [navigate]);

//   if (!user) return <div>Loading...</div>;

//   return (
//     <div className="form-container">
//       <h2>Profile</h2>
//       {error && <p className="form-error">{error}</p>}
//       <div className="form-group profile-info">
//         <label>Email</label>
//         <p>{user.email}</p>
//       </div>
//       <div className="form-group profile-info">
//         <label>Username</label>
//         <p>{user.username}</p>
//       </div>
//       <div className="form-group profile-info">
//         <label>Roles</label>
//         <p>{user.roles.join(', ')}</p>
//       </div>
//     </div>
//   );
// }

// export default Profile;

// src/components/Profile.js
import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const Profile = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem('jwt');
    if (!token) {
      navigate('/login');
    }
    // Optionally fetch user profile data using the token
  }, [navigate]);

  return (
    <div>
      <h2>Profile</h2>
      <p>Welcome to your profile!</p>
    </div>
  );
};

export default Profile;