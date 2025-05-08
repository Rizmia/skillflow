import './App.css';
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './components/Home';
import GroupList from './components/GroupList/GroupList';

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/groups" element={<GroupList />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
