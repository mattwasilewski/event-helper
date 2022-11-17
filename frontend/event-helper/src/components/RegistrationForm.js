import React, {useState} from "react";
import "../register.css"
import img from "../assets/login-img.png";
import logo from "../assets/logociemne.png";
import switchMode from "../assets/dark-switch.png"

export default function RegistrationForm() {

    const [name, setName] = useState(null);
    const [age, setAge] = useState(null);
    const [email, setEmail] = useState(null);
    const [password,setPassword] = useState(null);


    const handleInputChange = (e) => {
        const {id , value} = e.target;
        if(id === "name"){
            setName(value);
        }
        if(id === "age"){
            setAge(value);
        }
        if(id === "email"){
            setEmail(value);
        }
        if(id === "password"){
            setPassword(value);
        }
    }

    const handleSubmit  = (e) => {
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
    return (
        <div className="form">
            <div className="register-page">
                <div id="left-site">
                    <div id="logo">
                        <img src={logo} alt="logo"/>
                    </div>
                    <div id="switch-mode">
                        <img src={switchMode} alt=""/>
                    </div>
                    <div className="form-body" id="register-form">
                        <p id="register-word">Register: </p>

                        <div className="name">
                            <label className="form__label" htmlFor="name"></label>
                            <input className="form__input name-input" type="text" value={name}
                                   onChange={(e) => handleInputChange(e)}
                                   id="name" placeholder="Name"/>
                        </div>
                        <div className="age">
                            <label className="form__label" htmlFor="age"></label>
                            <input className="form__input age-input" type="number" value={age}
                                   onChange={(e) => handleInputChange(e)}
                                   id="age" placeholder="Age"/>
                        </div>
                        <div className="email">
                            <label className="form__label" htmlFor="email"></label>
                            <input className="form__input password-input" type="email" value={email}
                                   onChange={(e) => handleInputChange(e)}
                                   id="email" placeholder="Email"/>
                        </div>
                        <div className="password">
                            <label className="form__label" htmlFor="password"></label>
                            <input className="form__input password-input" type="password" id="password"
                                   value={password} min={4}
                                   onChange={(e) => handleInputChange(e)} placeholder="Password"/>
                        </div>
                    </div>
                </div>
                <div id="right-site">

                </div>

                <div className="footer">
                    <button onClick={() => handleSubmit()} type="submit" id="signup-btn" className="btn">Register
                    </button>
                </div>
                <p id="have-acc-text">You have account?</p>
                <div>
                    <button id="login-btn">Login</button>
                </div>
            </div>
        </div>
    )

}


