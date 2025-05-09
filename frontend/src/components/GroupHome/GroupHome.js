import React from 'react';
import { useNavigate } from 'react-router-dom';
import './GroupHome.css';

const GroupHome = () => {
  const navigate = useNavigate();

  return (
    <div className="group-home">
      <h1>Welcome to SkillFlow Study Groups</h1>
      <p>Join or create study groups to collaborate and learn IT skills.</p>
      <button onClick={() => navigate('/group/new')} className="new-group-btn">
        New Group
      </button>
      <button onClick={() => navigate('/groups')} className="view-groups-btn">
        View All Groups
      </button>
    </div>
  );
};

export default GroupHome;