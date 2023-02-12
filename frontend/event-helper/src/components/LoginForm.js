import "../css/LoginAndRegister.css";
import {useEffect, useState} from "react";
import React from "react";
import {useNavigate} from "react-router-dom";
import authSerivce from "../auth.serivce";
import axios from "axios";

export default function LoginForm({Login, error}) {

    let navigate = useNavigate();
    const [errors, setErrors] = useState("");
    const SITE_KEY = process.env.REACT_APP_RECAPTCHA_SITE_KEY;
    const [form, setForm] = useState({
        email: "",
        password: ""
    });
    const {email, password} = form;

    function handleChange(e) {
        setForm({...form, [e.target.name]: e.target.value});
    }



    const submitData = token => {
        let customConfig = {
            headers: {
                'Content-Type': 'application/json'
            }
        };
        let json = JSON.stringify({
            email: email,
            password: password,
            token: token
        })
        console.log(token)
        return axios
            .post("http://localhost:3000/api/auth/" + "login", json, customConfig).then((response) => {
                localStorage.setItem("user", JSON.stringify(response.data))
                const user = JSON.parse(localStorage.getItem('user'))
            }).then(res => {
                console.log("Request complete! response:", res);
                if (authSerivce.getCurrentUser()) {
                    navigate('/home')
                }
            }).catch((error) => {
                console.log("login error", error);
                setErrors("login error");
            });
    }


    useEffect(() => {
        const loadScriptByURL = (id, url, callback) => {
            const isScriptExist = document.getElementById(id);

            if (!isScriptExist) {
                let script = document.createElement("script");
                script.type = "text/javascript";
                script.src = url;
                script.id = id;
                script.onload = function () {
                    if (callback) callback();
                };
                document.body.appendChild(script);
            }

            if (isScriptExist && callback) callback();
        }

        loadScriptByURL("recaptcha-key", `https://www.google.com/recaptcha/api.js?render=${SITE_KEY}`, function () {
            console.log("Script loaded!");
        });
    }, []);


    const handleOnClick = e => {
        e.preventDefault();
        window.grecaptcha.ready(() => {
            window.grecaptcha.execute(SITE_KEY, { action: 'submit' }).then(token => {
                submitData(token).then(r => console.log(r));
            });
        });
    }








    return (
        <>
            <script src={`https://www.google.com/recaptcha/api.js?render=${SITE_KEY}`}></script>
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
                                <form onSubmit={handleOnClick}>
                                    {(error !== "") ? (<div className="error">{error}</div>) : ""}
                                    <input type="email" name="email" placeholder="E-mail Address" onChange={handleChange}/>
                                    <input type="password"  name="password" placeholder="Password"  onChange={handleChange}/>

                                    <div className="remember-form">
                                        <a href="/register"><span>Create new account</span></a>
                                    </div>
                                    <div className="forget-pass">
                                        <a href="/forgot-password">Forgot Password ?</a>
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