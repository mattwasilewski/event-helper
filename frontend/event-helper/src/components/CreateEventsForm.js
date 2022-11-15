import React, {useState} from "react";
import "../register.css"
import img from "../assets/login-img.png";
import logo from "../assets/logociemne.png";
import switchMode from "../assets/dark-switch.png"

function CreateEventsorm() {

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
        //e.preventDefault();
        console.log(name,age,email,password);
        let obj = {
            name: name,
            age:age,
            email:email,
            password:password
        }
        console.log(obj)
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json; charset=UTF-8',
                'Access-Control-Allow-Origin': 'http://localhost:3000',
                'Access-Control-Allow-Credentials': 'true'},
            body: JSON.stringify({ name: name,
                age:age,
                email:email,
                password:password})
        };
        fetch('http://localhost:8080/registration', requestOptions)
            .then(response => response.json())
        // .then(data => this.setState({ postId: data.id }));
        // fetch(`http://localhost:8080/registration`, {
        //     method: "POST",
        //     mode: 'cors',
        //     headers: {
        //         'Content-Type': 'application/json; charset=UTF-8',
        //         'Access-Control-Allow-Origin': 'http://localhost:3000',
        //         'Access-Control-Allow-Credentials': 'true'
        //     },
        //     body: {
        //         name:name,
        //         age:age,
        //         email:email,
        //         password:password
        //     }
        // }).then(res => {
        //     console.log("Request complete! response:", res);
        // }).catch((error) => {
        //     console.log("registration error", error);
        //     // setErrors("registration error");
        // });
        // // setErrors("registered successfully");
        // // setForm({
        // //     taskName: "",
        // //     taskDeadline: ""
        // // });
    }

    return (
        <div className="form" >
            <div class="register-page">
                <div id="left-site">
                    <div id="switch-mode">
                        <img src={switchMode} alt=""/>
                    </div>
                    <div className="form-body" id="create-event-form">
                        <p id="register-word">Register: </p>

                        <div>
                            <label className="form__label" htmlFor="name"></label>
                            <input className="form__input" type="text" value={name} onChange={(e) => handleInputChange(e)}
                                   id="name" class="name-input" placeholder="Name"/>
                        </div>
                        <div>
                            <label className="form__label" htmlFor="age"></label>
                            <input className="form__input" type="number" value={age}
                                   onChange={(e) => handleInputChange(e)}
                                   id="age" class="age-input" placeholder="Age"/>
                        </div>
                        <div>
                            <label className="form__label" htmlFor="email"></label>
                            <input className="form__input" type="email" value={email}
                                   onChange={(e) => handleInputChange(e)}
                                   id="email" class="email-input" placeholder="Email"/>
                        </div>
                        <div>
                            <label className="form__label" htmlFor="password"></label>
                            <input className="form__input" type="password" id="password" class="password-input" value={password} min={4}
                                   onChange={(e) => handleInputChange(e)} placeholder="Password"/>
                        </div>
                    </div>
                </div>
                <div id="right-site">

                </div>

                <div className="footer" >
                    <button onClick={() => handleSubmit()} type="submit" id="signup-btn" className="btn" >Register</button>
                </div>
                <p id="have-acc-text">You have account?</p>
                <div>
                    <button id="login-btn" >Login</button>
                </div>
            </div>
        </div>
    )
}

export default CreateEventsorm
