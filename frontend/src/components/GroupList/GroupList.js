import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../../services/api';
import './GroupList.css';

const GroupList = () => {
  const [groups, setGroups] = useState([]);
  const [showJoinModal, setShowJoinModal] = useState(false);
  const [selectedGroupId, setSelectedGroupId] = useState(null);
  const [joinForm, setJoinForm] = useState({ name: '', email: '', agreeToTerms: false });
  const [joinError, setJoinError] = useState(null);
  const navigate = useNavigate();

  const fetchGroups = async () => {
    try { const res = await api.get('/groups'); setGroups(res.data); } catch (error) { console.error('Error fetching groups:', error); }
  };

  const deleteGroup = async (id) => {
    if (window.confirm('Are you sure you want to delete this group?')) {
      try { await api.delete(`/groups/${id}`); fetchGroups(); } catch (error) { console.error('Error deleting group:', error); alert('Error deleting group'); }
    }
  };

  const handleJoinClick = (groupId) => { setSelectedGroupId(groupId); setShowJoinModal(true); setJoinForm({ name: '', email: '', agreeToTerms: false }); setJoinError(null); };
  const handleJoinFormChange = (e) => { const { name, value, type, checked } = e.target; setJoinForm({ ...joinForm, [name]: type === 'checkbox' ? checked : value }); };
  const handleJoinSubmit = async (e) => { e.preventDefault(); if (!joinForm.name || !joinForm.email || !joinForm.agreeToTerms) { setJoinError('Please fill all fields and agree to terms.'); return; } try { await api.post(`/groups/${selectedGroupId}/join`, { name: joinForm.name, email: joinForm.email }); setShowJoinModal(false); setGroups(groups.map(g => g.id === selectedGroupId ? { ...g, isMember: true } : g)); } catch (error) { console.error('Error joining group:', error); setJoinError(error.response?.data?.message || error.response?.data || 'Error joining group.'); } };
  const handleJoinCancel = () => { setShowJoinModal(false); setJoinError(null); };

  useEffect(() => { fetchGroups(); }, []);

  return (
    <div className="group-list-container">
      <button onClick={() => navigate('/')} className="back-btn">Back to Home</button>
      <h2>Skill-Based Study Groups</h2>
      <div className="group-grid">
        {groups.map((group) => (
          <div key={group.id} className="group-card">
            {group.image && <img src={`data:image/jpeg;base64,${group.image}`} alt={group.title} className="group-image" />}
            <h3 onClick={() => navigate(`/group/${group.id}`)}>{group.title}</h3>
            <p>{group.description.substring(0, 100)}...</p>
            <p><strong>Category:</strong> {group.category}</p>
            <p><strong>Created by:</strong> {group.createdBy}</p>
            <div className="group-actions">
              <button onClick={() => navigate('/group/new', { state: { group } })} className="edit-btn">Edit</button>
              <button onClick={() => deleteGroup(group.id)} className="delete-btn">Delete</button>
              {group.isMember ? (
                <button onClick={() => navigate(`/group/${group.id}`)} className="view-btn">View Group (Joined)</button>
              ) : (
                <button onClick={() => handleJoinClick(group.id)} className="join-btn">Join Group</button>
              )}
            </div>
          </div>
        ))}
      </div>
      {showJoinModal && (
        <div className="modal-overlay">
          <div className="join-modal">
            <h3>Join Group</h3>
            {joinError && <div className="error-message">{joinError}</div>}
            <form onSubmit={handleJoinSubmit} className="join-form">
              <div className="form-group"><label>Name</label><input type="text" name="name" value={joinForm.name} onChange={handleJoinFormChange} placeholder="Enter your name" required /></div>
              <div className="form-group"><label>Email</label><input type="email" name="email" value={joinForm.email} onChange={handleJoinFormChange} placeholder="Enter your email" required /></div>
              <div className="form-group"><label><input type="checkbox" name="agreeToTerms" checked={joinForm.agreeToTerms} onChange={handleJoinFormChange} /> I agree to terms and conditions</label></div>
              <div className="modal-actions"><button type="submit" className="submit-btn">Join</button><button type="button" onClick={handleJoinCancel} className="cancel-btn">Cancel</button></div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default GroupList;