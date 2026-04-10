import React, { useEffect, useState } from 'react';
import api from './api.js';
import StudentList from './components/StudentList.jsx';
import AddStudent from './components/AddStudent.jsx';
import './App.css';

const App = () => {
  const [students, setStudents] = useState([]);
  const [selectedStudent, setSelectedStudent] = useState(null);

  const fetchStudents = async () => {
    const response = await api.get('/students');
    setStudents(response.data);
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  const handleAddOrUpdate = async (formData) => {
    if (selectedStudent) {
      const response = await api.put(`/students/${selectedStudent.id}`, formData);
      const updated = response.data;
      setStudents((prev) => prev.map((s) => (s.id === updated.id ? updated : s)));
      setSelectedStudent(null);
    } else {
      const response = await api.post('/students', formData);
      const created = response.data;
      setStudents((prev) => [...prev, created]);
    }
  };

  const handleDelete = async (id) => {
    await api.delete(`/students/${id}`);
    setStudents((prev) => prev.filter((s) => s.id !== id));
  };

  const handleEdit = (student) => {
    setSelectedStudent(student);
  };

  return (
    <div className="app-container">
      <h2>Student Management System</h2>
      <AddStudent onSubmit={handleAddOrUpdate} selectedStudent={selectedStudent} />
      <StudentList students={students} onDelete={handleDelete} onEdit={handleEdit} />
    </div>
  );
};

export default App;
