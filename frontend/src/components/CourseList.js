// import React, { useState, useEffect } from 'react';
// import axios from 'axios';
// import { Link } from 'react-router-dom';
// import '../styles/CourseList.css';

// const CourseList = () => {
//   const [courses, setCourses] = useState([]);

//   useEffect(() => {
//     axios.get('http://localhost:8081/api/courses')
//       .then(response => setCourses(response.data))
//       .catch(error => console.error('Error fetching courses:', error));
//   }, []);

//   const handleDelete = (id) => {
//     axios.delete(`http://localhost:8081/api/courses/${id}`, {
//       headers: { 'User-Id': 'user123' } // Replace with actual user ID from auth
//     })
//       .then(() => setCourses(courses.filter(course => course.id !== id)))
//       .catch(error => console.error('Error deleting course:', error));
//   };

//   return (
//     <div className="course-list">
//       <h2>Available Courses</h2>
//       <div className="course-grid">
//         {courses.map(course => (
//           <div key={course.id} className="course-card">
//             <h3>{course.title}</h3>
//             <p>{course.description}</p>
//             <p><strong>Category:</strong> {course.category}</p>
//             <p><strong>Level:</strong> {course.level}</p>
//             <p><strong>Tags:</strong> {course.tags.join(', ')}</p>
//             <div className="course-actions">
//               <Link to={`/edit/${course.id}`} className="edit-btn">Edit</Link>
//               <button onClick={() => handleDelete(course.id)} className="delete-btn">Delete</button>
//             </div>
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// };

// export default CourseList;

// import React, { useState, useEffect } from 'react';
// import axios from 'axios';
// import { Link } from 'react-router-dom';
// import '../styles/CourseList.css';

// const CourseList = () => {
//   const [courses, setCourses] = useState([]);

//   useEffect(() => {
//     axios.get('http://localhost:8081/api/courses')
//       .then(response => setCourses(response.data))
//       .catch(error => console.error('Error fetching courses:', error));
//   }, []);

//   const handleDelete = (id) => {
//     axios.delete(`http://localhost:8081/api/courses/${id}`, {
//       headers: { 'User-Id': 'user123' } // Matches creatorId
//     })
//       .then(() => setCourses(courses.filter(course => course.id !== id)))
//       .catch(error => console.error('Error deleting course:', error));
//   };

//   return (
//     <div className="course-list">
//       <h2>Available Courses</h2>
//       <div className="course-grid">
//         {courses.map(course => (
//           <div key={course.id} className="course-card">
//             <h3>{course.title}</h3>
//             <p>{course.description}</p>
//             <p><strong>Category:</strong> {course.category}</p>
//             <p><strong>Level:</strong> {course.level}</p>
//             <p><strong>Tags:</strong> {course.tags.join(', ')}</p>
//             <div className="course-actions">
//               <Link to={`/edit/${course.id}`} className="edit-btn">Edit</Link>
//               <button onClick={() => handleDelete(course.id)} className="delete-btn">Delete</button>
//             </div>
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// };

// export default CourseList;

import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import { debounce } from "lodash";
import "../styles/CourseList.css";
import Swal from "sweetalert2";

const CourseList = () => {
  const [courses, setCourses] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [searchField, setSearchField] = useState("all"); // Default to search all fields
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);
  const [pageSize] = useState(10);
  const [triggerSearch, setTriggerSearch] = useState(false);

  // Debounced function to update search query
  const debouncedSetSearchQuery = debounce((value) => {
    setSearchQuery(value);
    setPage(0); // Reset to first page on new search
  }, 300);

  const handleSearchChange = (e) => {
    debouncedSetSearchQuery(e.target.value);
  };

  const handleFieldChange = (e) => {
    setSearchField(e.target.value);
    setPage(0); // Reset to first page on field change
    setTriggerSearch(true); // Trigger search on field change
  };

  const handleSearchSubmit = (e) => {
    e.preventDefault();
    setTriggerSearch(true); // Trigger search on button click
  };

  // const handleDelete = (id) => {
  //   // Show confirmation dialog
  //   const confirmDelete = window.confirm('Are you sure you want to delete this course?');
  //   if (confirmDelete) {
  //     axios.delete(`http://localhost:8081/api/courses/${id}`, {
  //       headers: { 'User-Id': 'user123' }
  //     })
  //       .then(() => setCourses(courses.filter(course => course.id !== id)))
  //       .catch(error => console.error('Error deleting course:', error));
  //   }
  // };

  const handleDelete = (id) => {
    Swal.fire({
      title: "Are you sure?",
      text: "You won’t be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!",
    }).then((result) => {
      if (result.isConfirmed) {
        axios
          .delete(`http://localhost:8081/api/courses/${id}`, {
            headers: { "User-Id": "user123" },
          })
          .then(() => setCourses(courses.filter((course) => course.id !== id)))
          .catch((error) => console.error("Error deleting course:", error));
      }
    });
  };

  const handlePageChange = (newPage) => {
    if (newPage >= 0 && newPage < totalPages) {
      setPage(newPage);
    }
  };

  // Highlight matching text
  const highlightMatch = (text, query) => {
    if (!query || !text) return text;
    const regex = new RegExp(`(${query})`, "gi");
    return text.replace(regex, '<span class="highlight">$1</span>');
  };

  useEffect(() => {
    const fetchCourses = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8081/api/courses/search",
          {
            params: {
              query: searchQuery,
              field: searchField === "all" ? null : searchField,
              page,
              size: pageSize,
            },
          }
        );
        setCourses(response.data.content);
        setTotalPages(response.data.totalPages);
      } catch (error) {
        console.error("Error fetching courses:", error);
      }
    };

    fetchCourses();
    setTriggerSearch(false); // Reset trigger after search
  }, [searchQuery, searchField, page, pageSize, triggerSearch]);

  return (
    <div className="course-list">
      <h2>Available Courses</h2>
      <div className="search-bar">
        <form onSubmit={handleSearchSubmit}>
          <div className="search-container">
            <select
              value={searchField}
              onChange={handleFieldChange}
              className="search-filter"
            >
              <option value="all">All Fields</option>
              <option value="title">Title</option>
              <option value="category">Category</option>
              <option value="tags">Tags</option>
            </select>
            <input
              type="text"
              placeholder={`Search by ${
                searchField === "all" ? "title, category, or tags" : searchField
              }...`}
              onChange={handleSearchChange}
              className="search-input"
            />
            <button type="submit" className="search-btn">
              Search
            </button>
          </div>
        </form>
      </div>
      {courses.length === 0 && searchQuery && (
        <p className="no-results">No courses found for "{searchQuery}"</p>
      )}
      <div className="course-grid">
        {courses.map((course) => (
          <div key={course.id} className="course-card">
            <h3
              dangerouslySetInnerHTML={{
                __html: highlightMatch(course.title, searchQuery),
              }}
            />
            <p>{course.description}</p>
            <p>
              <strong>Category:</strong>{" "}
              <span
                dangerouslySetInnerHTML={{
                  __html: highlightMatch(course.category, searchQuery),
                }}
              />
            </p>
            <p>
              <strong>Level:</strong> {course.level}
            </p>
            <p>
              <strong>Tags:</strong>{" "}
              <span
                dangerouslySetInnerHTML={{
                  __html: highlightMatch(course.tags.join(", "), searchQuery),
                }}
              />
            </p>
            <div className="course-actions">
              <Link to={`/edit/${course.id}`} className="edit-btn">
                <i className="fas fa-edit"></i>
              </Link>
              <button
                onClick={() => handleDelete(course.id)}
                className="delete-btn"
              >
                <i className="fas fa-trash-alt"></i>
              </button>
            </div>
          </div>
        ))}
      </div>
      <div className="pagination">
        <button
          onClick={() => handlePageChange(page - 1)}
          disabled={page === 0}
          className="page-btn"
        >
          Previous
        </button>
        <span>
          Page {page + 1} of {totalPages}
        </span>
        <button
          onClick={() => handlePageChange(page + 1)}
          disabled={page >= totalPages - 1}
          className="page-btn"
        >
          Next
        </button>
      </div>
    </div>
  );
};

export default CourseList;
