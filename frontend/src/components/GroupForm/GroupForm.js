import React, { useState, useEffect } from 'react';
import api from '../../services/api';

const GroupForm = ({ currentGroup, onSuccess }) => {
  const [formData, setFormData] = useState({
    title: '',
    description: '',
    category: '',
    createdAt: new Date().toISOString(),
  });

  useEffect(() => {
    if (currentGroup) {
      setFormData(currentGroup);
    }
  }, [currentGroup]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (currentGroup) {
      await api.put(`/groups/${currentGroup.id}`, formData);
    } else {
      await api.post('/groups', formData);
    }
    onSuccess();
    setFormData({ title: '', description: '', category: '', createdAt: new Date().toISOString() });
  };

  return (
    <form onSubmit={handleSubmit} className="group-form">
      <input name="title" value={formData.title} onChange={handleChange} placeholder="Group Name" required />
      <input name="category" value={formData.category} onChange={handleChange} placeholder="Category" required />
      <textarea name="description" value={formData.description} onChange={handleChange} placeholder="Description" required />
      <button type="submit">{currentGroup ? "Update" : "Create"} Group</button>
    </form>
  );
};

export default GroupForm;
