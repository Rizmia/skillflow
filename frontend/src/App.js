import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import CourseList from './components/CourseList';
import CourseForm from './components/CourseForm';
import Navbar from './components/Navbar';
import './App.css';

const App = () => {
  return (
    <Router>
      <div className="app-container">
        <Navbar />
        <Routes>
          <Route path="/" element={<CourseList />} />
          <Route path="/create" element={<CourseForm />} />
          <Route path="/edit/:id" element={<CourseForm />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;