import React from 'react';
import axios from 'axios';
// import ReactRouterDOM from 'react-router-dom';
import { useLocation, useNavigate } from 'react-router-dom';

function Logout() {
    const navigate = useNavigate();

    React.useEffect(() => {
        axios.post('http://localhost:8081/api/auth/logout')
            .then(response => {
                localStorage.removeItem('token');
                navigate('/');
            })
            .catch(() => {
                localStorage.removeItem('token');
                navigate('/');
            });
    }, [navigate]);

    return (
        <div className="container">
            <h1>Logging out...</h1>
        </div>
    );
}

export default Logout;