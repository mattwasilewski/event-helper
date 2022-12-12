import React,{useEffect,useState} from "react";
import {OrganizedByUser} from "../event/OrganizedByUser";

export const AboutUser = (props) => {

    const [user, setUser] = useState([]);

    useEffect(() => {
        getUser().then(r => console.log(r))

    }, [props.userId]);

    const getUser = async () =>{
        const response = await fetch(`http://localhost:3000/api/user/${props.userId}`);
        const data = await response.json();
        setUser(data);
        console.log(data)
    }

    return (
        <>
            <h1>User information</h1>
            <p>User name: {user.name}</p>
            <p>User email: {user.email}</p>
            <p>User age: {user.age}</p>
            <p>User image: {user.imgUrl}</p>
            <OrganizedByUser userId={props.userId}/>
        </>
    )
}