import { useState } from "react";
import StudentForm from "./pages/StudentForm";
import StudentList from "./pages/StudentList";
import "./pages/Student.css";

function App() {
  const [selected, setSelected] = useState(null);
  const [refresh, setRefresh] = useState(false);

  const reload = () => setRefresh(!refresh);

  return (
    <div className="container">
      <h1 style={{ color: "white" }}>Student Management System</h1>

      <StudentForm
        selected={selected}
        refresh={reload}
        clear={() => setSelected(null)}
      />

      <StudentList setSelected={setSelected} key={refresh} />
    </div>
  );
}

export default App;