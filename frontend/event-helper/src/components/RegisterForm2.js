import "../css/LoginAndRegister.css";
import {useState} from "react";
import React from "react";
import {useNavigate} from "react-router-dom";

export default function RegisterForm2({Login, error}) {

    const pass = document.querySelector('#password-signup')
    const mail = document.querySelector('#email')
    const passInfo = document.querySelector('#pass-info')
    const mailInfo = document.querySelector('#email-info')
    const confirmPass = document.querySelector('#confirm-password-input')
    const confirmPassInfo = document.querySelector('#confirm-pass-info')

    const [name, setName] = useState(null);
    const [age, setAge] = useState(null);
    const [email, setEmail] = useState(null);
    const [password, setPassword] = useState(null);
    const [valid, setValid] = useState(true);
    let navigate = useNavigate();


    const letters = /[a-z]/i;
    const numbers = /[0-9]/;
    const special = /[!@#$%]/;
    const minValue = 8;
    const emailValidation = /^[0-9a-z_.-]+@[0-9a-z>.-]+\.[a-z]{2,3}$/i

    const emailValidationMsg = () => {
        if (mail != null) {
            mailInfo.style.display = "block";
            if (!mail.value.match(emailValidation)) {
                setDisplayInfo('Wrong Email', 'tomato', mailInfo);
                return false
            } else {
                mailInfo.style.display = "none";
                return true
            }
        }
    }

    const confirmPasswordMsg = () => {
        if (confirmPass != null) {
            confirmPassInfo.style.display = "block";
            confirmPassInfo.style.color = 'tomato'
            if (pass.value === confirmPass.value && confirmPass.value.length > 7) {
                confirmPassInfo.style.display = "none";
                return true
            } else if (confirmPass.value.length === 0 || pass.value.length === 0) {
                confirmPassInfo.style.display = "none";
                return false
            }
        }
    }

    function setDisplayInfo(textContent, color, variable) {
        variable.textContent = textContent
        variable.style.color = color
    }

    const passwordValidationMsg = () => {
        if (pass != null) {
            passInfo.style.display = "block";
            if (pass.value.length >= minValue && pass.value.match(letters) && pass.value.match(numbers)
                && pass.value.match(special)) {
                setDisplayInfo('Perfect Password!', 'lime', passInfo)
                return true
            } else if (pass.value.length >= minValue && pass.value.match(letters) && pass.value.match(numbers)) {
                setDisplayInfo('Good Password', 'gold', passInfo)
                return true
            } else if (pass.value.length === 0) {
                passInfo.style.display = "none"
                return false
            } else if (pass.value.length >= minValue) {
                setDisplayInfo('Weak Password!', 'tomato', passInfo)
                return true
            } else {
                setDisplayInfo('Your password is too short', 'tomato', passInfo)
                return false
            }
        }
    }

    const formValidator = () => {
        if (emailValidationMsg() && confirmPasswordMsg()
            && passwordValidationMsg()) {
            setValid(false)
        } else {
            setValid(true)
        }
    }


    const handleInputChange = (e) => {
        const {id, value} = e.target;
        if (id === "name-signup") {
            setName(value);
        }
        if (id === "age") {
            setAge(value);
        }
        if (id === "email") {
            emailValidationMsg()
            setEmail(value);
        }
        if (id === "password-signup") {
            passwordValidationMsg()
            setPassword(value);
        }
        if (id === "confirm-password-input") {
            confirmPasswordMsg()
        }
        if (email != null && password != null) {
            formValidator()
        }
    }


    const handleSubmit = () => {
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8',
                'Access-Control-Allow-Origin': 'http://localhost:3000',
                'Access-Control-Allow-Credentials': 'true'
            },
            body: JSON.stringify({
                name: name,
                age: age,
                email: email,
                password: password
            })
        };
        fetch('http://localhost:3000/api/registration', requestOptions)
            .then(response => response.json())
        navigate("/home")

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
                        <h1>Sign Up to Event Helper</h1>
                            <div className="login-form">
                            <form>
                                {(error !== "") ? (<div className="error">{error}</div>) : ""}
                                <input type="text" value={name}
                                       onChange={(e) => handleInputChange(e)}
                                       id="name-signup" placeholder="Name"/>
                                <input type="text" value={age}
                                       onChange={(e) => handleInputChange(e)}
                                       id="age" placeholder="Age"/>
                                <p id="email-info">Wrong Email</p>
                                <input type="email" value={email}
                                       onChange={(e) => handleInputChange(e)}
                                       id="email" placeholder="Email"/>
                                <p id="pass-info">Too short password</p>
                                <input type="password" value={password} id="password-signup"
                                       onChange={(e) => handleInputChange(e)} placeholder="Password"/>
                                <p id="confirm-pass-info">Password not match!</p>
                                <input type="password" id="confirm-password-input"
                                       onChange={(e) => handleInputChange(e)} placeholder="Confirm Password"/>

                                <div className="remember-form">
                                    <a href="/login"><span>Already have an account?</span></a>
                                </div>
                                {/*<div className="forget-pass">*/}
                                {/*    <a href="#">Already have an account?</a>*/}
                                {/*</div>*/}

                                <button disabled={valid} onClick={() => handleSubmit()} type="submit"
                                        id="signup-btn">Sign Up
                                </button>
                            </form>
                            </div>
                    </div>

                </div>
            </div>
        </div>
</>
)

}