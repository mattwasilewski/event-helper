import React,{useEffect,useState} from "react";

function AllUsersComponent() {

    const [users, setUsers] = useState([]);

    useEffect(() => {
        getUsers().then(r => console.log(r))

    }, []);

    const getUsers = async () => {
        const response = await fetch(`http://localhost:3000/api/user/users`)
        const data = await response.json();
        setUsers(data);
    }

    return(
        <>{users.map((user) => (
            <p>{user.name}</p>
        ))}
        </>

    )
}

export default AllUsersComponent