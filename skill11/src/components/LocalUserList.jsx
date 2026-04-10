import { useEffect, useState } from "react";

function LocalUserList() {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        fetch("/user.json")
            .then(res => res.json())
            .then(data => {
                setUsers(data);
                setLoading(false);
            })
            .catch(() => {
                setError("Error fetching data");
                setLoading(false);
            });
    }, []);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div>
            <h2>Local Users</h2>
            {users.map(user => (
                <div key={user.id}>
                    <p>{user.name} - {user.email} - {user.phone}</p>
                </div>
            ))}
        </div>
    );
}

export default LocalUserList;