import './App.css';
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import GroupHome from './components/GroupHome/GroupHome';
import GroupForm from './components/GroupForm/GroupForm';
import GroupList from './components/GroupList/GroupList';
import GroupDetails from './components/GroupDetails/GroupDetails';

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<GroupHome />} />
          <Route path="/groups" element={<GroupList />} />
          <Route path="/group/new" element={<GroupForm />} />
          <Route path="/group/:id" element={<GroupDetails />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;