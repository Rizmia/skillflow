import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../../services/api';
import './GroupList.css';

const GroupList = () => {
  const [groups, setGroups] = useState([]);
  const [filteredGroups, setFilteredGroups] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');
  const [showJoinModal, setShowJoinModal] = useState(false);
  const [selectedGroupId, setSelectedGroupId] = useState(null);
  const [joinForm, setJoinForm] = useState({ name: '', email: '', agreeToTerms: false });
  const [joinError, setJoinError] = useState(null);
  const [showCreatorModal, setShowCreatorModal] = useState(false);
  const [creatorEmail, setCreatorEmail] = useState('');
  const [creatorError, setCreatorError] = useState(null);
  const [selectedGroup, setSelectedGroup] = useState(null);
  const navigate = useNavigate();

  const fetchGroups = async () => {
    try {
      const res = await api.get('/groups');
      const groupsWithMemberStatus = res.data.map(group => ({
        ...group,
        isMember: group.members.some(member => member.email === 'linarasenarathna2001@gmail.com'),
      }));
      setGroups(groupsWithMemberStatus);
      setFilteredGroups(groupsWithMemberStatus);
    } catch (error) {
      console.error('Error fetching groups:', error);
    }
  };

  const handleSearch = (e) => {
    const query = e.target.value.toLowerCase();
    setSearchQuery(query);
    const filtered = groups.filter(
      group =>
        group.title.toLowerCase().includes(query) ||
        group.category.toLowerCase().includes(query)
    );
    setFilteredGroups(filtered);
  };

  const handleJoinClick = (groupId) => {
    setSelectedGroupId(groupId);
    setShowJoinModal(true);
    setJoinForm({ name: '', email: '', agreeToTerms: false });
    setJoinError(null);
  };

  const handleJoinFormChange = (e) => {
    const { name, value, type, checked } = e.target;
    setJoinForm({ ...joinForm, [name]: type === 'checkbox' ? checked : value });
  };

  const handleJoinSubmit = async (e) => {
    e.preventDefault();
    if (!joinForm.name || !joinForm.email || !joinForm.agreeToTerms) {
      setJoinError('Please fill all fields and agree to terms.');
      return;
    }
    try {
      await api.post(`/groups/${selectedGroupId}/join`, { name: joinForm.name, email: joinForm.email });
      setShowJoinModal(false);
      setGroups(groups.map(g => g.id === selectedGroupId ? { ...g, isMember: true } : g));
      setFilteredGroups(filteredGroups.map(g => g.id === selectedGroupId ? { ...g, isMember: true } : g));
    } catch (error) {
      console.error('Error joining group:', error);
      setJoinError(error.response?.data?.message || error.response?.data || 'Error joining group.');
    }
  };

  const handleJoinCancel = () => {
    setShowJoinModal(false);
    setJoinError(null);
  };

  const handleCreatorCheck = (group) => {
    setSelectedGroup(group);
    setShowCreatorModal(true);
    setCreatorEmail('');
    setCreatorError(null);
  };

  const handleCreatorSubmit = () => {
    if (!creatorEmail) {
      setCreatorError('Please enter an email.');
      return;
    }
    if (creatorEmail === selectedGroup.createdBy) {
      navigate(`/group/${selectedGroup.id}`);
    } else {
      setCreatorError('Only the group creator can access this page.');
    }
  };

  const handleCreatorCancel = () => {
    setShowCreatorModal(false);
    setCreatorError(null);
  };

  useEffect(() => {
    fetchGroups();
  }, []);

  return (
    <div className="gl-container">
      <button onClick={() => navigate('/')} className="gl-back-btn">Back to Home</button>
      <div className="gl-header">
        <video className="background-video" autoPlay muted loop>
          <source src="/video/Employee Training Series.mp4" type="video/mp4" />
          Your browser does not support the video tag.
        </video>
        <div className="gl-header-content">
          <input
            type="text"
            placeholder="Search by group name or category..."
            value={searchQuery}
            onChange={handleSearch}
            className="gl-search-input"
          />
          <h2>Skill-Based Study Groups</h2>
        </div>
      </div>
      <div className="gl-grid">
        {filteredGroups.map((group) => (
          <div key={group.id} className="gl-card">
            {group.image && <img src={`data:image/jpeg;base64,${group.image}`} alt={group.title} className="gl-image" />}
            <h3 onClick={() => handleCreatorCheck(group)}>{group.title}</h3>
            <p>{group.description.substring(0, 100)}...</p>
            <p><strong>Category:</strong> {group.category}</p>
            <p><strong>Created by:</strong> {group.createdBy}</p>
            <div className="gl-actions">
              {group.isMember ? (
                <button onClick={() => navigate(`/group/${group.id}`)} className="gl-view-btn">View Group (Joined)</button>
              ) : (
                <button onClick={() => handleJoinClick(group.id)} className="gl-join-btn">Join Group</button>
              )}
            </div>
          </div>
        ))}
      </div>
      {showJoinModal && (
        <div className="gl-modal-overlay">
          <div className="gl-join-modal">
            <h3>Join Group</h3>
            {joinError && <div className="gl-error-message">{joinError}</div>}
            <form onSubmit={handleJoinSubmit} className="gl-join-form">
              <div className="gl-form-group"><label>Name</label><input type="text" name="name" value={joinForm.name} onChange={handleJoinFormChange} placeholder="Enter your name" required /></div>
              <div className="gl-form-group"><label>Email</label><input type="email" name="email" value={joinForm.email} onChange={handleJoinFormChange} placeholder="Enter your email" required /></div>
              <div className="gl-form-group"><label><input type="checkbox" name="agreeToTerms" checked={joinForm.agreeToTerms} onChange={handleJoinFormChange} /> I agree to terms and conditions</label></div>
              <div className="gl-modal-actions"><button type="submit" className="gl-submit-btn">Join</button><button type="button" onClick={handleJoinCancel} className="gl-cancel-btn">Cancel</button></div>
            </form>
          </div>
        </div>
      )}
      {showCreatorModal && (
        <div className="gl-modal-overlay">
          <div className="gl-creator-modal">
            <h3>Enter Creator Email</h3>
            {creatorError && <div className="gl-error-message">{creatorError}</div>}
            <div className="gl-form-group">
              <label>Email</label>
              <input
                type="email"
                value={creatorEmail}
                onChange={(e) => setCreatorEmail(e.target.value)}
                placeholder="Enter creator email"
                required
              />
            </div>
            <div className="gl-modal-actions">
              <button onClick={handleCreatorSubmit} className="gl-submit-btn">OK</button>
              <button onClick={handleCreatorCancel} className="gl-cancel-btn">Cancel</button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default GroupList;

