import {useState} from "react";
import "../register.css"
import img from "../assets/login-img.png";
import logo from "../assets/logociemne.png";
import { useNavigate } from 'react-router-dom' ;


export default function RegistrationForm() {

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
        if (mail != null){
            mailInfo.style.display = "block";
            if (!mail.value.match(emailValidation)){
                mailInfo.textContent = 'Wrong Email!'
                mailInfo.style.color = 'tomato'
                return false
            }else {
                mailInfo.style.display = "none";
                return true
            }
        }
    }

    const confirmPasswordMsg = () => {
        if (confirmPass !=  null){
            confirmPassInfo.style.display = "block";
            confirmPassInfo.style.color = 'tomato'
            if (pass.value === confirmPass.value && confirmPass.value.length > 7){
                confirmPassInfo.style.display = "none";
                return true
            } else if (confirmPass.value.length === 0 || pass.value.length === 0){
                confirmPassInfo.style.display = "none";
                return false
            }
        }
    }

    const passwordValidationMsg = () => {
        if (pass != null){
            passInfo.style.display = "block";
            if (pass.value.length >= minValue && pass.value.match(letters) && pass.value.match(numbers)
                && pass.value.match(special)){
                passInfo.textContent = 'Perfect Password!'
                passInfo.style.color = 'lime'
                return true
            } else if (pass.value.length >= minValue && pass.value.match(letters) && pass.value.match(numbers)){
                passInfo.textContent = 'Good Password'
                passInfo.style.color = 'gold'
                return true
            } else if (pass.value.length === 0){
                passInfo.style.display = "none"
                return false
            } else if (pass.value.length >= minValue) {
                passInfo.textContent = 'Weak Password!'
                passInfo.style.color = 'tomato'
                return true
            } else {
                passInfo.textContent = 'Your password is too short'
                passInfo.style.color = 'tomato'
                return false
            }
        }
    }

    const formValidator = () => {
        if (emailValidationMsg() && confirmPasswordMsg()
            && passwordValidationMsg()){
            setValid(false)
        }else{
            setValid(true)
        }
        console.log('wynik email validator: ' + emailValidationMsg())
        console.log('wynik confirm pass validator: ' + confirmPasswordMsg())
        console.log('wynik pass validator: ' + passwordValidationMsg())
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
        if (email != null && password != null){
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
        fetch('http://localhost:8080/registration', requestOptions)
            .then(response => response.json())

        navigate("/home")

    }
    return (<div className="form">
            <div className="container-left">
                <a href="/home"><div id="navbar-logo">
                    <img src={logo} alt="logo"/>
                </div></a>
                <div className="form-body" id="register-form">
                    <p id="register-word">Register: </p>

                    <div className="name">
                        <label className="form__label" htmlFor="name"></label>
                        <input className="form__input name-input" type="text" value={name}
                               onChange={(e) => handleInputChange(e)}
                               id="name-signup" placeholder="Name"/>
                    </div>
                    <div className="age">
                        <label className="form__label" htmlFor="age"></label>
                        <input className="form__input age-input" type="number" value={age}
                               onChange={(e) => handleInputChange(e)}
                               id="age" placeholder="Age"/>
                    </div>
                    <div className="email">
                        <p id="email-info">Wrong Email</p>
                        <label className="form__label" htmlFor="email"></label>
                        <input className="form__input email-input" type="email" value={email}
                               onChange={(e) => handleInputChange(e)}
                               id="email" placeholder="Email"/>
                    </div>
                    <div className="password">
                        <p id="pass-info">Too short password</p>
                        <label className="form__label" htmlFor="password"></label>
                        <input className="form__input password-input" type="password" id="password-signup"
                               value={password}
                               onChange={(e) => handleInputChange(e)} placeholder="Password"/>
                    </div>
                    <div className="password">
                        <p id="confirm-pass-info">Password not match!</p>
                        <label className="form__label" htmlFor="password"></label>
                        <input className="form__input password-input" type="password" id="confirm-password-input"
                               onChange={(e) => handleInputChange(e)} placeholder="Confirm Password"/>
                    </div>
                </div>
            </div>

            <div className="register-button">
                <button disabled={valid} onClick={() => handleSubmit()} type="submit" id="signup-btn" className="btn">Register</button>
            </div>
            <p id="have-acc-text">Already have an account?</p>
            <div className="login-button">
                <a href="login">
                    <button id="login-btn">Login</button>
                </a>
            </div>
            <div className="container-right">
                <img src={img} alt={"img"}/>
            </div>

        </div>
    )

}


