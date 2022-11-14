import React, {useState} from "react";
import img from "../assets/login-img.png";

function RegistrationForm() {

    // const [register, setRegister] = useState({name: "",age: "", email: "", password: ""});
    const [name, setName] = useState(null);
    const [age, setAge] = useState(null);
    const [email, setEmail] = useState(null);
    const [password,setPassword] = useState(null);
    //
    // const submitHandler = e => {
    //     e.preventDefault();
    //     Login(register);
    // }

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
        // e.preventDefault();
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
        <div className="form">
            <div className="form-body">
                <div className="name">
                    <label className="form__label" htmlFor="name">Name </label>
                    <input className="form__input" type="text" value={name} onChange={(e) => handleInputChange(e)}
                           id="name" placeholder="Name"/>
                </div>
                <div className="age">
                    <label className="form__label" htmlFor="age">Age </label>
                    <input type="number" name="" id="age" value={age} className="form__input"
                           onChange={(e) => handleInputChange(e)} />
                </div>
                <div className="email">
                    <label className="form__label" htmlFor="email">Email </label>
                    <input type="email" id="email" className="form__input" value={email}
                           onChange={(e) => handleInputChange(e)} placeholder="Email"/>
                </div>
                <div className="password">
                    <label className="form__label" htmlFor="password">Password </label>
                    <input className="form__input" type="password" id="password" value={password} min={4}
                           onChange={(e) => handleInputChange(e)} placeholder="Password"/>
                </div>
            </div>
            <div className="footer">
                <button onClick={() => handleSubmit()} type="submit" className="btn">Register</button>
            </div>
        </div>
    )
}

export default RegistrationForm
