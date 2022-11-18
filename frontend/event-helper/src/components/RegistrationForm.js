import {useState} from "react";
import "../register.css"
import img from "../assets/login-img.png";
import logo from "../assets/logociemne.png";


export default function RegistrationForm() {

    const [name, setName] = useState(null);
    const [age, setAge] = useState(null);
    const [email, setEmail] = useState(null);
    const [password, setPassword] = useState(null);


    const handleInputChange = (e) => {
        const {id, value} = e.target;
        if (id === "name-signup") {
            setName(value);
        }
        if (id === "age") {
            setAge(value);
        }
        if (id === "email") {
            setEmail(value);
        }
        if (id === "password-signup") {
            setPassword(value);
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
                        <label className="form__label" htmlFor="email"></label>
                        <input className="form__input email-input" type="email" value={email}
                               onChange={(e) => handleInputChange(e)}
                               id="email" placeholder="Email"/>
                    </div>
                    <div className="password">
                        <label className="form__label" htmlFor="password"></label>
                        <input className="form__input password-input" type="password" id="password-signup"
                               value={password} min={4}
                               onChange={(e) => handleInputChange(e)} placeholder="Password"/>
                    </div>
                </div>
            </div>

            <div className="register-button">
                    <a href="/home"><button onClick={() => handleSubmit()} type="submit" id="signup-btn" className="btn">Register
                    </button></a>
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


