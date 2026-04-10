import React from 'react';

const StudentList = ({ students, onDelete, onEdit }) => {
  return (
    <div className="table-container">
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {students.length === 0 ? (
            <tr>
              <td colSpan="5">No students found.</td>
            </tr>
          ) : (
            students.map((student) => (
              <tr key={student.id}>
                <td>{student.id}</td>
                <td>{student.name}</td>
                <td>{student.email}</td>
                <td>{student.course}</td>
                <td className="action-buttons">
                  <button onClick={() => onEdit(student)}>Update</button>{' '}
                  <button onClick={() => onDelete(student.id)}>Delete</button>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
};

export default StudentList;
