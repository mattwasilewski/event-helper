import React, {useState} from "react";
import img from "../assets/login-img.png";

function LoginForm({ Login, error }) {

    const [details, setDetails] = useState({name: "", password: ""});

    const submitHandler = e => {
        e.preventDefault();
        Login(details);
    }

    return (
        <form onSubmit={submitHandler}>
            <div className="form-inner, container-left">
                <h2 className="text-left" >Login</h2>
                {(error !== "") ? (<div className="error">{error}</div> ) : ""}
                <div className="form-group">
                    <label htmlFor="name">Name: </label>
                    <input type="text" name="name" id="name" onChange={e =>
                        setDetails({...details, name: e.target.value})} value={details.name} />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password: </label>
                    <input type="password" name="password" id="password" onChange={e =>
                        setDetails({...details, password: e.target.value})} value={details.password} />
                </div>
                <input type="submit" value="LOGIN" />
            </div>
            <div className="container-right">
                <img src={img} alt={"img"}/>
            </div>
        </form>
    )
}

export default LoginForm
