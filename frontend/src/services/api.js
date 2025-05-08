// frontend/src/services/api.js

import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8082/api', // your Spring Boot backend base path
});

export default api;
