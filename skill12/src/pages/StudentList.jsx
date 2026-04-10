import { useEffect, useState } from "react";
import API from "../api/api";
import "./Student.css";

const StudentList = ({ setSelected }) => {
  const [students, setStudents] = useState([]);

  // Fetch all students
  const fetchStudents = async () => {
    try {
      const res = await API.get("");
      setStudents(res.data);
    } catch (error) {
      console.error("Error fetching students:", error);
    }
  };

  // Load data on component mount
  useEffect(() => {
    fetchStudents();
  }, []);

  // Delete student
  const deleteStudent = async (id) => {
    try {
      await API.delete(`/${id}`);
      fetchStudents(); // refresh list
    } catch (error) {
      console.error("Error deleting student:", error);
    }
  };

  return (
    <div className="card">
      <h2>Student List</h2>

      {/* Scroll wrapper (prevents overflow) */}
      <div style={{ overflowX: "auto" }}>
        <table className="table">
          <thead>
            <tr>
              <th>Name</th>
              <th>Email</th>
              <th>Course</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {students.length === 0 ? (
              <tr>
                <td colSpan="4">No students found</td>
              </tr>
            ) : (
              students.map((s) => (
                <tr key={s.id}>
                  <td>{s.name}</td>
                  <td>{s.email}</td>
                  <td>{s.course}</td>
                  <td>
                    <button
                      className="edit-btn"
                      onClick={() => setSelected(s)}
                    >
                      Edit
                    </button>

                    <button
                      className="delete-btn"
                      onClick={() => deleteStudent(s.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default StudentList;