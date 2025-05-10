import React from 'react';
import { useNavigate } from 'react-router-dom';
import './GroupHome.css';
import Navbar from '../NavBar/NavBar';

const GroupHome = () => {
  const navigate = useNavigate();

  return (
    <>
      <Navbar />
      <div className="group-home">
        <div className="video-container">
          <video className="background-video" autoPlay muted loop>
            <source src="/video/STUDY GROUP.mp4" type="video/mp4" />
            Your browser does not support the video tag.
          </video>
        </div>
        <div className="content-container">
          <h1>Welcome to SkillFlow Study Groups</h1>
          <p>Join or create study groups to collaborate and learn IT skills.</p>
          <div className="button-group">
            <button onClick={() => navigate('/group/new')} className="new-group-btn">
              New Group
            </button>
            <button onClick={() => navigate('/groups')} className="view-groups-btn">
              View All Groups
            </button>
          </div>
        </div>
      </div>
    </>
  );
};

export default GroupHome;                                                               
