import LoginForm from "../components/LoginForm";
import React, {useState} from "react";
import LoginForm2 from "../components/LoginForm2";
import RecommendedEvents from "../components/event/RecommendedEvents";
import RegisterForm2 from "../components/RegisterForm2";

export default function LoginPage() {


    const userData = {
        name: "name",
        password: "password"
    }

    const [user, setUser] = useState({name: ""});
    const [error, setError] = useState("");

    const Login = details => {
        if (details.name === userData.name && details.password === userData.password) {
            console.log("Logged in!")
            setUser({
                name: details.name,
            });
        } else {
            console.error("Details do not match!")
            setError("Details do not match!")
        }
        console.error("test log")
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
                <LoginForm2 Login={Login} error={error}/>
                // <LoginForm Login={Login} error={error} />
            )}
        </div>
    )

}