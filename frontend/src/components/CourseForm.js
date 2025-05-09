// import React, { useState, useEffect } from 'react';
// import axios from 'axios';
// import { useNavigate, useParams } from 'react-router-dom';
// import '../styles/CourseForm.css';

// const CourseForm = () => {
//   const { id } = useParams();
//   const navigate = useNavigate();
//   const [course, setCourse] = useState({
//     title: '',
//     description: '',
//     category: '',
//     level: 'Beginner',
//     materials: [],
//     tags: []
//   });

//   useEffect(() => {
//     if (id) {
//       axios.get(`http://localhost:8081/api/courses/${id}`)
//         .then(response => setCourse(response.data))
//         .catch(error => console.error('Error fetching course:', error));
//     }
//   }, [id]);

//   const handleChange = (e) => {
//     const { name, value } = e.target;
//     if (name === 'materials' || name === 'tags') {
//       setCourse({ ...course, [name]: value.split(',').map(item => item.trim()) });
//     } else {
//       setCourse({ ...course, [name]: value });
//     }
//   };

//   const handleSubmit = (e) => {
//     e.preventDefault();
//     const request = id
//       ? axios.put(`http://localhost:8081/api/courses/${id}`, course, { headers: { 'User-Id': 'user123' } })
//       : axios.post('http://localhost:8081/api/courses', course, { headers: { 'User-Id': 'user123' } });

//     request
//       .then(() => navigate('/'))
//       .catch(error => console.error('Error saving course:', error));
//   };

//   return (
//     <div className="course-form">
//       <h2>{id ? 'Edit Course' : 'Create Course'}</h2>
//       <form onSubmit={handleSubmit}>
//         <div className="form-group">
//           <label>Title</label>
//           <input type="text" name="title" value={course.title} onChange={handleChange} required />
//         </div>
//         <div className="form-group">
//           <label>Description</label>
//           <textarea name="description" value={course.description} onChange={handleChange} required></textarea>
//         </div>
//         <div className="form-group">
//           <label>Category</label>
//           <input type="text" name="category" value={course.category} onChange={handleChange} required />
//         </div>
//         <div className="form-group">
//           <label>Level</label>
//           <select name="level" value={course.level} onChange={handleChange}>
//             <option value="Beginner">Beginner</option>
//             <option value="Intermediate">Intermediate</option>
//             <option value="Advanced">Advanced</option>
//           </select>
//         </div>
//         <div className="form-group">
//           <label>Materials (comma-separated URLs)</label>
//           <input type="text" name="materials" value={course.materials.join(', ')} onChange={handleChange} />
//         </div>
//         <div className="form-group">
//           <label>Tags (comma-separated)</label>
//           <input type="text" name="tags" value={course.tags.join(', ')} onChange={handleChange} />
//         </div>
//         <button type="submit" className="submit-btn">Save</button>
//       </form>
//     </div>
//   );
// };

// export default CourseForm;

// import React, { useState, useEffect } from 'react';
// import axios from 'axios';
// import { useNavigate, useParams } from 'react-router-dom';
// import '../styles/CourseForm.css';

// const CourseForm = () => {
//   const { id } = useParams();
//   const navigate = useNavigate();
//   const [course, setCourse] = useState({
//     title: '',
//     description: '',
//     category: '',
//     level: 'Beginner', // Default value for level
//     materials: [],
//     tags: []
//   });

//   useEffect(() => {
//     if (id) {
//       axios.get(`http://localhost:8081/api/courses/${id}`)
//         .then(response => {
//           const fetchedCourse = response.data;
//           // Ensure level has a default value if null or undefined
//           setCourse({
//             ...fetchedCourse,
//             level: fetchedCourse.level || 'Beginner',
//             materials: fetchedCourse.materials || [],
//             tags: fetchedCourse.tags || []
//           });
//         })
//         .catch(error => console.error('Error fetching course:', error));
//     }
//   }, [id]);

//   const handleChange = (e) => {
//     const { name, value } = e.target;
//     if (name === 'materials' || name === 'tags') {
//       setCourse({ ...course, [name]: value.split(',').map(item => item.trim()) });
//     } else {
//       setCourse({ ...course, [name]: value });
//     }
//   };

//   const handleSubmit = (e) => {
//     e.preventDefault();
//     const request = id
//       ? axios.put(`http://localhost:8081/api/courses/${id}`, course, { headers: { 'User-Id': 'user123' } })
//       : axios.post('http://localhost:8081/api/courses', course, { headers: { 'User-Id': 'user123' } });

//     request
//       .then(() => navigate('/'))
//       .catch(error => console.error('Error saving course:', error));
//   };

//   return (
//     <div className="course-form">
//       <h2>{id ? 'Edit Course' : 'Create Course'}</h2>
//       <form onSubmit={handleSubmit}>
//         <div className="form-group">
//           <label>Title</label>
//           <input type="text" name="title" value={course.title} onChange={handleChange} required />
//         </div>
//         <div className="form-group">
//           <label>Description</label>
//           <textarea name="description" value={course.description} onChange={handleChange} required></textarea>
//         </div>
//         <div className="form-group">
//           <label>Category</label>
//           <input type="text" name="category" value={course.category} onChange={handleChange} required />
//         </div>
//         <div className="form-group">
//           <label>Level</label>
//           <select name="level" value={course.level || 'Beginner'} onChange={handleChange}>
//             <option value="Beginner">Beginner</option>
//             <option value="Intermediate">Intermediate</option>
//             <option value="Advanced">Advanced</option>
//           </select>
//         </div>
//         <div className="form-group">
//           <label>Materials (comma-separated URLs)</label>
//           <input type="text" name="materials" value={course.materials.join(', ')} onChange={handleChange} />
//         </div>
//         <div className="form-group">
//           <label>Tags (comma-separated)</label>
//           <input type="text" name="tags" value={course.tags.join(', ')} onChange={handleChange} />
//         </div>
//         <button type="submit" className="submit-btn">Save</button>
//       </form>
//     </div>
//   );
// };

// export default CourseForm;

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import '../styles/CourseForm.css';

const CourseForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [course, setCourse] = useState({
    title: '',
    description: '',
    category: '',
    level: 'Beginner',
    materials: [],
    tags: []
  });
  const [errors, setErrors] = useState({}); // State to store validation errors

  useEffect(() => {
    if (id) {
      axios.get(`http://localhost:8081/api/courses/${id}`)
        .then(response => {
          const fetchedCourse = response.data;
          setCourse({
            ...fetchedCourse,
            level: fetchedCourse.level || 'Beginner',
            materials: fetchedCourse.materials || [],
            tags: fetchedCourse.tags || []
          });
        })
        .catch(error => console.error('Error fetching course:', error));
    }
  }, [id]);

  // Validation function
  const validateForm = () => {
    const newErrors = {};

    // Title validation
    if (!course.title.trim()) {
      newErrors.title = 'Title is required';
    } else if (course.title.length < 3) {
      newErrors.title = 'Title must be at least 3 characters long';
    } else if (course.title.length > 100) {
      newErrors.title = 'Title cannot exceed 100 characters';
    }

    // Description validation
    if (!course.description.trim()) {
      newErrors.description = 'Description is required';
    } else if (course.description.length < 10) {
      newErrors.description = 'Description must be at least 10 characters long';
    } else if (course.description.length > 1000) {
      newErrors.description = 'Description cannot exceed 1000 characters';
    }

    // Category validation
    if (!course.category.trim()) {
      newErrors.category = 'Category is required';
    } else if (course.category.length < 3) {
      newErrors.category = 'Category must be at least 3 characters long';
    } else if (course.category.length > 50) {
      newErrors.category = 'Category cannot exceed 50 characters';
    }

    // Level validation (optional, since dropdown enforces valid values)
    if (!['Beginner', 'Intermediate', 'Advanced'].includes(course.level)) {
      newErrors.level = 'Please select a valid level';
    }

    // Materials validation (optional)
    if (course.materials.length > 0) {
      const urlRegex = /^(https?:\/\/[^\s$.?#].[^\s]*)$/;
      course.materials.forEach((material, index) => {
        if (material && !urlRegex.test(material)) {
          newErrors.materials = `Material at position ${index + 1} is not a valid URL`;
        }
      });
    }

    // Tags validation (optional)
    if (course.tags.length > 0) {
      course.tags.forEach((tag, index) => {
        if (tag && (tag.length < 2 || tag.length > 30)) {
          newErrors.tags = `Tag at position ${index + 1} must be between 2 and 30 characters`;
        }
      });
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0; // Return true if no errors
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === 'materials' || name === 'tags') {
      setCourse({ ...course, [name]: value.split(',').map(item => item.trim()).filter(item => item) });
    } else {
      setCourse({ ...course, [name]: value });
    }
    // Clear error for the field being edited
    setErrors({ ...errors, [name]: '' });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validateForm()) {
      const request = id
        ? axios.put(`http://localhost:8081/api/courses/${id}`, course, { headers: { 'User-Id': 'user123' } })
        : axios.post('http://localhost:8081/api/courses', course, { headers: { 'User-Id': 'user123' } });

      request
        .then(() => navigate('/'))
        .catch(error => console.error('Error saving course:', error));
    }
  };

  return (
    <div className="course-form">
      <h2>{id ? 'Edit Course' : 'Create Course'}</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Title</label>
          <input
            type="text"
            name="title"
            value={course.title}
            onChange={handleChange}
            required
          />
          {errors.title && <span className="error">{errors.title}</span>}
        </div>
        <div className="form-group">
          <label>Description</label>
          <textarea
            name="description"
            value={course.description}
            onChange={handleChange}
            required
          ></textarea>
          {errors.description && <span className="error">{errors.description}</span>}
        </div>
        <div className="form-group">
          <label>Category</label>
          <input
            type="text"
            name="category"
            value={course.category}
            onChange={handleChange}
            required
          />
          {errors.category && <span className="error">{errors.category}</span>}
        </div>
        <div className="form-group">
          <label>Level</label>
          <select name="level" value={course.level || 'Beginner'} onChange={handleChange}>
            <option value="Beginner">Beginner</option>
            <option value="Intermediate">Intermediate</option>
            <option value="Advanced">Advanced</option>
          </select>
          {errors.level && <span className="error">{errors.level}</span>}
        </div>
        <div className="form-group">
          <label>Materials (comma-separated URLs)</label>
          <input
            type="text"
            name="materials"
            value={course.materials.join(', ')}
            onChange={handleChange}
          />
          {errors.materials && <span className="error">{errors.materials}</span>}
        </div>
        <div className="form-group">
          <label>Tags (comma-separated)</label>
          <input
            type="text"
            name="tags"
            value={course.tags.join(', ')}
            onChange={handleChange}
          />
          {errors.tags && <span className="error">{errors.tags}</span>}
        </div>
        <button type="submit" className="submit-btn">Save</button>
      </form>
    </div>
  );
};

export default CourseForm;