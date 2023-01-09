import "../css/LoginAndRegister.css";
import {useState} from "react";
import React from "react";
import {useNavigate} from "react-router-dom";
import AuthService from "../auth.serivce";
import authSerivce from "../auth.serivce";

export default function LoginForm({Login, error}) {

    let navigate = useNavigate();

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
                if (authSerivce.getCurrentUser()){
                    navigate('/home')
                }
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


    return (
        <>
            <div className="section-1">
                <div className="parent clearfix">
                    <div className="bg-illustration">
                        <a href="/home"><img src={require("../assets/logo-duza-rozdzielczosc-jasne.png")} alt="logo"></img></a>

                        <div className="burger-btn">
                            <span></span>
                            <span></span>
                            <span></span>
                        </div>

                    </div>

                    <div className="login">
                        <div className="container">
                            <h1>Login to Event Helper</h1>
                            <h4>{errors}</h4>
                            <div className="login-form">
                                <form onSubmit={handleSubmit}>
                                    {(error !== "") ? (<div className="error">{error}</div>) : ""}
                                    <input type="email" name="email" placeholder="E-mail Address" onChange={handleChange}/>
                                    <input type="password"  name="password" placeholder="Password"  onChange={handleChange}/>

                                    <div className="remember-form">
                                        <a href="/register"><span>Create new account</span></a>
                                    </div>
                                    <div className="forget-pass">
                                        <a href="#">Forgot Password ?</a>
                                    </div>

                                    <button type="submit">LOG-IN</button>

                                </form>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </>
    )

}