import {useState} from "react";
import React from "react";
import AuthService from "../auth.serivce";
import authSerivce from "../auth.serivce";


function ForgotPasswordForm() {

    const[email,setEmail]=useState('')
    const [message, setMessage] = useState('');



    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log("1")
        try {
            await fetch(`http://localhost:3000/api/forgot-password`,{
                method:"POST",
                headers:{"Content-Type":"application/json",
                    'Accept': 'application/json',
                    'Origin': 'http://localhost:3000'},
                body:JSON.stringify(email)
            })
            setMessage("Password reset link sent to your email");
        } catch (error) {
            console.log("3")
            setMessage("Error occured please try again later");
        }
    };



    return(
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
                            <h1>Forgot Password</h1>

                            <div className="login-form">
                                <form onSubmit={handleSubmit}>
                                    <input
                                        placeholder="E-mail Address"
                                        type="email"
                                        value={email}
                                        onChange={(e) => setEmail(e.target.value)}
                                    />
                                    <button type="submit">SEND EMAIL</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ForgotPasswordForm;