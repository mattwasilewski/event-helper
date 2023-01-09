import React, {useState} from "react";
import img from "../assets/login-img.png";
import logo from "../assets/logociemne.png";
import "../css/register.css";
import ForgotPasswordForm from "./ForgotPasswordForm";



function LoginForm({Login, error}) {

    const [details, setDetails] = useState({name: "", password: ""});

    const submitHandler = e => {
        e.preventDefault();
        Login(details);
    }

    return (<>
            <div className="container-left">
                <a href="/home"><div id="navbar-logo"><img src={logo} alt="Event Helper"/></div></a>
                <div id="login-heading">Login</div>
                <form onSubmit={submitHandler}>
                    <div className="form-inner">
                        {(error !== "") ? (<div className="error">{error}</div>) : ""}
                        <div className="form-group">
                            <input type="text" name="name" id="name" placeholder="login" onChange={e =>
                                setDetails({...details, name: e.target.value})} value={details.name}/>
                        </div>
                        <div className="form-group">
                            <input type="password" name="password" id="password" placeholder="password" onChange={e =>
                                setDetails({...details, password: e.target.value})} value={details.password}/>
                        </div>
                        <button type="submit" id="login-submit" value="LOGIN">LOGIN</button>
                    </div>

                    <a href={"/admin"} style={{color:"white"}}> Admin </a>
                </form>

            </div>
            <ForgotPasswordForm/>
            <div className="container-right">
                <img className="login-photo" src={img} alt={"img"}/>
            </div>
        </>


    )
}

export default LoginForm
