import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import CreateSkillPost from './components/CreateSkillPost/CreateSkillPost'
//import CreateSkillPost from './components/CreateSkillPost/';
import ViewSkillPosts from './components/ViewSkillPosts/ViewSkillPosts';
import UpdateSkillPost from './components/UpdateSkillPost/UpdateSkillPost';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<ViewSkillPosts />} />
        <Route path="/create" element={<CreateSkillPost />} />
        <Route path="/update/:id" element={<UpdateSkillPost />} />
      </Routes>
    </Router>
  );
}

export default App;
