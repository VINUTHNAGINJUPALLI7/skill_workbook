import { useEffect, useState } from "react";

function UserList() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        fetch("https://jsonplaceholder.typicode.com/users")
            .then(res => res.json())
            .then(data => setUsers(data))
            .catch(() => console.log("Error"));
    }, []);

    return (
        <div>
            <h2>API Users</h2>
            {users.map(user => (
                <p key={user.id}>
                    {user.name} - {user.email} - {user.phone}
                </p>
            ))}
        </div>
    );
}

export default UserList;