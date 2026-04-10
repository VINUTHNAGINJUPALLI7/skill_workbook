import { useState, useEffect } from "react";
import API from "../api/api";
import "./Student.css";

const StudentForm = ({ selected, refresh, clear }) => {
  const [student, setStudent] = useState({
    name: "",
    email: "",
    course: ""
  });

  useEffect(() => {
    if (selected) setStudent(selected);
  }, [selected]);

  const handleChange = (e) => {
    setStudent({ ...student, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    if (student.id) {
      await API.put(`/${student.id}`, student);
    } else {
      await API.post("", student);
    }

    setStudent({ name: "", email: "", course: "" });
    clear();
    refresh();
  };

  return (
    <div className="card">
      <h2>{student.id ? "Update Student" : "Add Student"}</h2>

      <input name="name" value={student.name} onChange={handleChange} placeholder="Name" />
      <input name="email" value={student.email} onChange={handleChange} placeholder="Email" />
      <input name="course" value={student.course} onChange={handleChange} placeholder="Course" />

      <button onClick={handleSubmit}>
        {student.id ? "Update" : "Add"}
      </button>
    </div>
  );
};

export default StudentForm;