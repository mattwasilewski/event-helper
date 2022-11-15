import Navbar from "../components/Navbar";
import React, {useState} from "react";
import CreateEventsForm from "../components/CreateEventsForm";

export default function AddEventPage() {
    const userData = {
        name: "name",
        age: "age",
        email: "email",
        password: "password"
    }

    const [user, setUser] = useState({name: ""});
    const [error, setError] = useState("");

    const Login = register => {
        if (register.name === userData.name && register.password === userData.password) {
            console.log("Logged in!")
            setUser({
                name: register.name,
            });
        } else {
            console.log("Details do not match!")
            setError("Details do not match!")
        }
    }

    const Logout = () => {
        console.log("Logout");
        setUser({name: ""});
    }


    return (
        <div>
            <Navbar/>
                <CreateEventsForm Login={Login} error={error} />
        </div>
    )

}