/*import React, { useState, useEffect } from 'react';
import './UpdateSkillPost.css';
import { useParams, useNavigate } from 'react-router-dom';

function UpdateSkillPost() {
  const { id } = useParams();
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    fetch(`http://localhost:8081/api/skillposts/${id}`)
      .then((res) => res.json())
      .then((data) => {
        setTitle(data.title);
        setDescription(data.description);
      })
      .catch((err) => console.error(err));
  }, [id]);

  const handleUpdate = async (e) => {
    e.preventDefault();

    const updatedPost = { title, description };

    try {
      const response = await fetch(`http://localhost:8081/api/skillposts/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(updatedPost),
      });

      if (response.ok) {
        alert('Post updated successfully!');
        navigate('/');
      } else {
        alert('Failed to update.');
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="update-container">
      <h2>Update Skill Post</h2>
      <form onSubmit={handleUpdate} className="update-form">
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
        <button type="submit">Update</button>
      </form>
    </div>
  );
}

export default UpdateSkillPost;*/




import React, { useState, useEffect } from 'react';
import './UpdateSkillPost.css';
import { useParams, useNavigate } from 'react-router-dom';

function UpdateSkillPost() {
  const { id } = useParams();
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    fetch(`http://localhost:8081/api/skillposts/${id}`)
      .then((res) => res.json())
      .then((data) => {
        setTitle(data.title);
        setDescription(data.description);
      })
      .catch((err) => console.error(err));
  }, [id]);

  const handleUpdate = async (e) => {
    e.preventDefault();

    const updatedPost = { title, description };

    try {
      const response = await fetch(`http://localhost:8081/api/skillposts/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(updatedPost),
      });

      if (response.ok) {
        alert('✅ Skill post updated successfully!');
        navigate('/');
      } else {
        alert('❌ Failed to update post.');
      }
    } catch (error) {
      console.error(error);
      alert('⚠️ An error occurred.');
    }
  };

  return (
    <div className="update-container">
      <div className="update-header">
        <span role="img" aria-label="edit" className="edit-icon">✏️</span>
        <h2>Edit Your Skill Post</h2>
      </div>
      <form onSubmit={handleUpdate} className="update-form">
        <input
          type="text"
          placeholder="Edit your skill title..."
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />
        <textarea
          placeholder="Edit your description..."
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          required
        />
        <button type="submit">Update Post</button>
      </form>
    </div>
  );
}

export default UpdateSkillPost;

