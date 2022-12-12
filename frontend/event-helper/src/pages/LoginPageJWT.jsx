import React, {useState} from 'react';
import {Link} from "react-router-dom";
import AuthService from "../auth.serivce";

function Login() {

    const [errors, setErrors] = useState("");

    const [form, setForm] = useState({
        email: "",
        password: ""
    });
    const {email, password} = form;

    function handleChange(e) {
        setForm({...form, [e.target.name]: e.target.value});
    }

    function handleSubmit(e) {
        e.preventDefault();

        AuthService.login(email, password)
            .then(res => {
                console.log("Request complete! response:", res);
            }).catch((error) => {
            console.log("login error", error);
            setErrors("login error");
        });
        setErrors("logged in successfully");
        setForm({
            email: "",
            password: ""
        });
    }

    function handleLogout(e){
        e.preventDefault()
        AuthService.logout();
    }

    return (
        <div className={"wrapper-container"}>
            <div className={"outer-box"}>
                <div className={"inner-box"}>
                    <h1 className={"title"}>Login</h1>
                    <h4>{errors}</h4>
                    <form onSubmit={handleSubmit}>
                        <input
                            type="text"
                            name="email"
                            placeholder="email"
                            value={email}
                            onChange={handleChange}
                            required
                        />
                        <input
                            type="text"
                            name="password"
                            placeholder="password"
                            value={password}
                            onChange={handleChange}
                            required
                        />
                        <button className={"submit-btn"} type="submit">
                            Login
                        </button>
                        <Link to="/">
                            Back to main page
                        </Link>
                        <Link to="/login">
                            Login
                        </Link>
                        <Link to="/logout" onClick={handleLogout}>
                            Logout
                        </Link>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default Login;
