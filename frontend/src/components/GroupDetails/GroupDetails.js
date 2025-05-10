import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import api from '../../services/api';
import './GroupDetails.css';

const GroupDetails = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [group, setGroup] = useState(null);
  const [posts, setPosts] = useState([]);
  const [showPostForm, setShowPostForm] = useState(false);
  const [postForm, setPostForm] = useState({ caption: '', description: '', media: null });
  const [editPostId, setEditPostId] = useState(null);
  const [postError, setPostError] = useState(null);
  const [userEmail] = useState('linarasenarathna2001@gmail.com'); // Hardcoded for now

  useEffect(() => {
    const fetchGroupAndPosts = async () => {
      try {
        const [groupRes, postsRes] = await Promise.all([api.get(`/groups/${id}`), api.get(`/posts/group/${id}`)]);
        setGroup(groupRes.data);
        setPosts(postsRes.data);
      } catch (error) {
        console.error('Error fetching group or posts:', error);
        alert('Error fetching group or posts');
        navigate('/groups');
      }
    };
    fetchGroupAndPosts();
  }, [id, navigate]);

  const handlePostFormChange = (e) => {
    const { name, value, files } = e.target;
    setPostForm({ ...postForm, [name]: files ? files[0] : value });
  };

  const handlePostSubmit = async (e) => {
    e.preventDefault();
    const data = new FormData();
    data.append('groupId', id);
    data.append('caption', postForm.caption);
    data.append('description', postForm.description);
    if (postForm.media) data.append('media', postForm.media);

    try {
      const response = editPostId ? await api.put(`/posts/${editPostId}`, data) : await api.post('/posts', data);
      setPosts(editPostId ? posts.map(p => p.id === editPostId ? response.data : p) : [...posts, response.data]);
      setShowPostForm(false);
      setPostForm({ caption: '', description: '', media: null });
      setEditPostId(null);
      setPostError(null);
    } catch (error) {
      console.error('Error saving post:', error);
      setPostError(error.response?.data?.message || 'Error saving post');
    }
  };

  const handleEditPost = (post) => { setEditPostId(post.id); setPostForm({ caption: post.caption, description: post.description, media: null }); setShowPostForm(true); };
  const handleDeletePost = async (postId) => { if (window.confirm('Are you sure?')) { try { await api.delete(`/posts/${postId}`); setPosts(posts.filter(p => p.id !== postId)); } catch (error) { console.error('Error deleting post:', error); alert('Error deleting post'); } } };
  const handleLike = async (postId) => { try { const response = await api.post(`/posts/${postId}/like`, { userEmail }); setPosts(posts.map(p => p.id === postId ? response.data : p)); } catch (error) { console.error('Error liking post:', error); } };
  const handleComment = async (postId, text) => { try { const response = await api.post(`/posts/${postId}/comment`, { userEmail, text, createdAt: new Date() }); setPosts(posts.map(p => p.id === postId ? response.data : p)); } catch (error) { console.error('Error commenting:', error); } };
  const handleLeaveGroup = async () => { if (window.confirm('Are you sure you want to leave this group?')) { try { await api.post(`/groups/${id}/leave`, { email: userEmail }); navigate('/groups'); } catch (error) { console.error('Error leaving group:', error); alert('Error leaving group'); } } };
  const handleDeleteGroup = async () => { if (window.confirm('Are you sure you want to delete this group?')) { try { await api.delete(`/groups/${id}`); navigate('/groups'); } catch (error) { console.error('Error deleting group:', error); alert('Error deleting group'); } } };

  if (!group) return <div className="group-details-container">Loading...</div>;

  return (
    <div className="group-details-container">
      <button onClick={() => handleLeaveGroup()} className="leave-btn">Leave Group</button>
      <h2>{group.title}</h2>
      {group.image && <img src={`data:image/jpeg;base64,${group.image}`} alt={group.title} className="group-image" />}
      <div className="group-info">
        <p><strong>Description:</strong> {group.description}</p>
        <p><strong>Category:</strong> {group.category}</p>
        <p><strong>Created by:</strong> {group.createdBy}</p>
        <p><strong>Created at:</strong> {new Date(group.createdAt).toLocaleDateString()}</p>
        <h3>Members</h3>
        {group.members.length > 0 ? (
          <ul className="members-list">{group.members.map((m, i) => <li key={i}>{m.name} ({m.email})</li>)}</ul>
        ) : <p>No members yet.</p>}
        {group.createdBy === userEmail && (
          <div className="group-actions">
            <button onClick={() => navigate('/group/new', { state: { group } })} className="edit-btn">Edit Group</button>
            <button onClick={handleDeleteGroup} className="delete-btn">Delete Group</button>
          </div>
        )}
      </div>
      <button onClick={() => { setShowPostForm(true); setEditPostId(null); setPostForm({ caption: '', description: '', media: null }); setPostError(null); }} className="new-post-btn">New Post</button>
      {showPostForm && (
        <div className="post-form-overlay">
          <div className="post-form">
            {postError && <div className="error-message">{postError}</div>}
            <form onSubmit={handlePostSubmit} className="post-form-content">
              <div className="form-group"><label>Caption</label><input type="text" name="caption" value={postForm.caption} onChange={handlePostFormChange} required /></div>
              <div className="form-group"><label>Description</label><textarea name="description" value={postForm.description} onChange={handlePostFormChange} required /></div>
              <div className="form-group"><label>Media (Image/Video)</label><input type="file" accept="image/*,video/*" name="media" onChange={handlePostFormChange} /></div>
              <div className="form-actions"><button type="submit" className="submit-btn">{editPostId ? 'Update' : 'Create'}</button><button type="button" onClick={() => setShowPostForm(false)} className="cancel-btn">Cancel</button></div>
            </form>
          </div>
        </div>
      )}
      <h3>Posts</h3>
      {posts.map((post) => (
        <div key={post.id} className="post-card">
          {post.media && <img src={`data:image/jpeg;base64,${post.media}`} alt="Post media" className="post-media" />}
          <h4>{post.caption}</h4>
          <p>{post.description}</p>
          <p><strong>By:</strong> {post.createdBy} <strong>At:</strong> {new Date(post.createdAt).toLocaleString()}</p>
          <button onClick={() => handleLike(post.id)} className="like-btn">Like ({post.likes.length})</button>
          <div>
            <input type="text" placeholder="Add comment..." onKeyPress={(e) => { if (e.key === 'Enter') handleComment(post.id, e.target.value); e.target.value = ''; }} />
            <button onClick={() => handleComment(post.id, prompt('Enter comment:'))} className="comment-btn">Comment ({post.comments.length})</button>
          </div>
          <ul className="comments-list">{post.comments.map((c, i) => <li key={i}>{c.text} by {c.userEmail} at {new Date(c.createdAt).toLocaleString()}</li>)}</ul>
          <button onClick={() => handleEditPost(post)} className="edit-btn">Edit</button>
          <button onClick={() => handleDeletePost(post.id)} className="delete-btn">Delete</button>
        </div>
      ))}
    </div>
  );
};

export default GroupDetails;  