import React, { useEffect, useState } from 'react';
import './ViewSkillPosts.css';
import { useNavigate } from 'react-router-dom';

function ViewSkillPosts() {
  const [posts, setPosts] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetch('http://localhost:8081/api/skillposts')
      .then((res) => res.json())
      .then((data) => setPosts(data))
      .catch((err) => console.error(err));
  }, []);

  const handleDelete = async (id) => {
    const confirmDelete = window.confirm('Are you sure you want to delete this post?');
    if (!confirmDelete) return;

    try {
      const response = await fetch(`http://localhost:8081/api/skillposts/${id}`, {
        method: 'DELETE',
      });

      if (response.ok) {
        setPosts(posts.filter((post) => post.id !== id));
      } else {
        alert('Failed to delete post.');
      }
    } catch (error) {
      console.error(error);
    }
  };

  const handleLike = async (id) => {
    try {
      const response = await fetch(`http://localhost:8081/api/skillposts/${id}/like`, {
        method: 'PUT',
      });

      if (response.ok) {
        const updatedPost = await response.json();
        setPosts(posts.map((post) => (post.id === id ? updatedPost : post)));
      } else {
        alert('Failed to like post.');
      }
    } catch (error) {
      console.error(error);
    }
  };

  const handleAddComment = async (id, commentText) => {
    try {
      const response = await fetch(`http://localhost:8081/api/skillposts/${id}/comments`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(commentText),
      });

      if (response.ok) {
        const updatedPost = await response.json();
        setPosts(posts.map((post) => (post.id === id ? updatedPost : post)));
      } else {
        alert('Failed to add comment.');
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="view-container">
      <h2>My Skill Feed</h2>
      <button className="create-button" onClick={() => navigate('/create')}>+ Share New Skill</button>
      <div className="post-list">
        {posts.map((post) => (
          <div key={post.id} className="post-card">
            <div className="post-header">
              <div className="user-avatar">👤</div>
              <div className="post-info">
                <h3>{post.title}</h3>
                <p className="post-description">{post.description}</p>
              </div>
            </div>
            <div className="post-actions">
              <button onClick={() => handleLike(post.id)} className="like-btn">👍 {post.likes}</button>
              <button onClick={() => navigate(`/update/${post.id}`)} className="edit-btn">✏️</button>
              <button onClick={() => handleDelete(post.id)} className="delete-btn">🗑️</button>
            </div>
            <div className="comments-section">
              <h4>💬 Comments</h4>
              <ul className="comment-list">
                {post.comments && post.comments.map((comment, index) => (
                  <li key={index}>🗨️ {comment}</li>
                ))}
              </ul>
              <form onSubmit={(e) => {
                e.preventDefault();
                const comment = e.target.elements.comment.value;
                if (comment) {
                  handleAddComment(post.id, comment);
                  e.target.reset();
                }
              }}>
                <input type="text" name="comment" placeholder="Write a comment..." required />
                <button type="submit" className="comment-btn">Post</button>
              </form>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ViewSkillPosts;







