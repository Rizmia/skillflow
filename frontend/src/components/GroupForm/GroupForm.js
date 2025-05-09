import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import api from '../../services/api';
import './GroupForm.css';

const GroupForm = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const currentGroup = location.state?.group;

  const [formData, setFormData] = useState({
    title: '',
    description: '',
    category: '',
    image: null,
  });
  const [error, setError] = useState(null);

  useEffect(() => {
    if (currentGroup) {
      setFormData({
        title: currentGroup.title,
        description: currentGroup.description,
        category: currentGroup.category,
        image: null,
      });
    }
  }, [currentGroup]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleImageChange = (e) => {
    setFormData({ ...formData, image: e.target.files[0] });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null); // Reset error state

    const data = new FormData();
    data.append('title', formData.title);
    data.append('description', formData.description);
    data.append('category', formData.category);
    if (formData.image) {
      data.append('image', formData.image);
    }

    // Log FormData contents for debugging
    console.log('FormData contents:');
    for (let [key, value] of data.entries()) {
      console.log(key, value);
    }

    try {
      let response;
      if (currentGroup) {
        response = await api.put(`/groups/${currentGroup.id}`, data);
      } else {
        response = await api.post('/groups', data);
      }
      console.log('Success response:', response.data);
      navigate('/groups');
    } catch (error) {
      console.error('Error saving group:', error);
      let errorMessage = 'An unexpected error occurred while saving the group.';
      if (error.response) {
        console.log('Server response:', error.response.status, error.response.data);
        // Handle cases where error.response.data is a string or an object
        const errorData = error.response.data;
        errorMessage = `Server error: ${error.response.status} - ${
          typeof errorData === 'string' ? errorData : errorData.message || JSON.stringify(errorData)
        }`;
      } else if (error.request) {
        console.log('No response received:', error.request);
        errorMessage = 'Network error: Unable to reach the backend server at http://localhost:8082/api/groups. Is it running?';
      } else {
        console.log('Error message:', error.message);
        errorMessage = `Error: ${error.message}`;
      }
      setError(errorMessage);
      console.log('Error message set to:', errorMessage);
    }
  };

  return (
    <div className="group-form-container">
      <h2>{currentGroup ? 'Edit Group' : 'Create New Group'}</h2>
      {error && <div className="error-message">{error}</div>}
      <form onSubmit={handleSubmit} className="group-form">
        <div className="form-group">
          <label>Group Name</label>
          <input
            name="title"
            value={formData.title}
            onChange={handleChange}
            placeholder="Enter group name"
            required
          />
        </div>
        <div className="form-group">
          <label>Category</label>
          <input
            name="category"
            value={formData.category}
            onChange={handleChange}
            placeholder="Enter category"
            required
          />
        </div>
        <div className="form-group"> 
          <label>Description</label>
          <textarea
            name="description"
            value={formData.description}
            onChange={handleChange}
            placeholder="Enter description"
            required
          />
        </div>
        <div className="form-group">
          <label>Image</label>
          <input
            type="file"
            accept="image/*"
            onChange={handleImageChange}
          />
        </div>
        <button type="submit" className="submit-btn">
          {currentGroup ? 'Update Group' : 'Create Group'}
        </button>
      </form>
    </div>
  );
};

export default GroupForm;