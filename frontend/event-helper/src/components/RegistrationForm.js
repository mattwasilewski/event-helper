import {useState} from "react";
import "../register.css"
import img from "../assets/login-img.png";
import logo from "../assets/logociemne.png";


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
            }else {
                mailInfo.style.display = "none";
            }
        }
    }

    const confirmPasswordMsg = () => {
        if (confirmPass !=  null){
            confirmPassInfo.style.display = "block";
            confirmPassInfo.style.color = 'tomato'
            if (pass.value === confirmPass.value){
                confirmPassInfo.style.display = "none";
            } else if (confirmPassInfo.value.length === 0 || pass.value.length === 0){
                confirmPassInfo.style.display = "none";
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
            } else if (pass.value.length >= minValue && pass.value.match(letters) && pass.value.match(numbers)){
                passInfo.textContent = 'Good Password'
                passInfo.style.color = 'gold'
            } else if (pass.value.length === 0){
                passInfo.style.display = "none"
            } else if (pass.value.length >= minValue) {
                passInfo.textContent = 'Weak Password!'
                passInfo.style.color = 'tomato'
            } else {
                passInfo.textContent = 'Your password is too short'
                passInfo.style.color = 'tomato'
            }
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
                <a href="/home"><button onClick={() => handleSubmit()} type="submit" id="signup-btn" className="btn">Register</button></a>
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


