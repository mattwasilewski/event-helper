import "../css/LoginAndRegister.css";
import {useState} from "react";
import React from "react";

export default function LoginForm2({Login, error}) {

    const [details, setDetails] = useState({name: "", password: ""});

    const submitHandler = e => {
        e.preventDefault();
        Login(details);
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

                            <div className="login-form">
                                <form onSubmit={submitHandler}>
                                    {(error !== "") ? (<div className="error">{error}</div>) : ""}
                                    <input type="email" placeholder="E-mail Address" onChange={e =>
                                        setDetails({...details, name: e.target.value})} value={details.name}/>
                                    <input type="password" placeholder="Password" onChange={e =>
                                        setDetails({...details, password: e.target.value})} value={details.password}/>

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