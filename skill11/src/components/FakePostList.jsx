import axios from "axios";
import { useEffect, useState } from "react";
function FakePostList() {
    const [posts, setPosts] = useState([]);
    const [filter, setFilter] = useState("");
    const fetchData = () => {
        axios.get("https://dummyjson.com/posts")
            .then(res => setPosts(res.data.posts))
            .catch(() => console.log("Error"));
    };
    useEffect(() => {
        fetchData();
    }, []);
    const filteredPosts = posts.filter(post =>
        filter ? post.userId == filter : true
    );
    return (
        <div>
            <h2>Fake API Posts</h2>
            <select onChange={(e) => setFilter(e.target.value)}>
                <option value="">All</option>
                <option value="1">User 1</option>
                <option value="2">User 2</option>
            </select>
            <button onClick={fetchData}>Refresh</button>
            {filteredPosts.map(post => (
                <div key={post.id}>
                    <h4>{post.title}</h4>
                    <p>{post.body}</p>
                </div>
            ))}
        </div>
    );
}
export default FakePostList;