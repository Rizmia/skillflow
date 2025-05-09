/*import React, { useState } from 'react';
import './CreateSkillPost.css';
import { useNavigate } from 'react-router-dom';

function CreateSkillPost() {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const newPost = { title, description };

    try {
      const response = await fetch('http://localhost:8081/api/skillposts', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newPost),
      });

      if (response.ok) {
        alert('Skill post created successfully!');
        navigate('/');
      } else {
        alert('Failed to create post.');
      }
    } catch (error) {
      console.error(error);
      alert('An error occurred.');
    }
  };

  return (
    <div className="create-container">
      <h2>Create Skill Post</h2>
      <form onSubmit={handleSubmit} className="create-form">
        <input
          type="text"
          placeholder="Enter Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />
        <textarea
          placeholder="Enter Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          required
        />
        <button type="submit">Share</button>
      </form>
    </div>
  );
}

export default CreateSkillPost;*/




import React, { useState } from 'react';
import './CreateSkillPost.css';
import { useNavigate } from 'react-router-dom';

function CreateSkillPost() {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const newPost = { title, description };

    try {
      const response = await fetch('http://localhost:8081/api/skillposts', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newPost),
      });

      if (response.ok) {
        alert('🎉 Skill post shared successfully!');
        navigate('/');
      } else {
        alert('❌ Failed to share post.');
      }
    } catch (error) {
      console.error(error);
      alert('⚠️ An error occurred.');
    }
  };

  return (
    <div className="create-container">
      <div className="create-header">
        <span role="img" aria-label="user" className="avatar-icon">👤</span>
        <h2>Share Your Skill</h2>
      </div>
      <form onSubmit={handleSubmit} className="create-form">
        <input
          type="text"
          placeholder="What's your skill title?"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />
        <textarea
          placeholder="Describe your skill or experience..."
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          required
        />
        <button type="submit">Post</button>
      </form>
    </div>
  );
}

export default CreateSkillPost;

