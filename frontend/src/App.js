import './App.css';
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './components/Home';

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <router>
            <Route path="/" element={<Home />} />
           
            {/* Add more routes here as needed */}
          </router>
        </Routes>
        
        
        </Router></div>
  );
}

export default App;
