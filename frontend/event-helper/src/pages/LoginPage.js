import LoginForm from "../components/LoginForm";
import React, {useState} from "react";

export default function LoginPage() {

    const userData = {
        name: "name",
        password: "password"
    }

    const [user, setUser] = useState({name: ""});
    const [error, setError] = useState("");

    const Login = details => {
        //console.log(details);
        if (details.name === userData.name && details.password === userData.password) {
            console.log("Logged in!")
            setUser({
                name: details.name,
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
            {(user.name !== "") ? (
                <div className="welcome">
                    <h2>Welcome, <span>{user.name}</span></h2>
                    <button onClick={Logout}>Logout</button>
                </div>
            ) : (
                <LoginForm Login={Login} error={error} />
            )}

        </div>
    )

}