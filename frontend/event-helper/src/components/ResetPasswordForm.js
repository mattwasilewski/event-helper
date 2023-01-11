import {useParams} from "react-router-dom";
import {useState} from "react";
import React from "react";

function ResetPasswordForm() {

    let { token } = useParams()
    const [password, setPassword] = useState('')


    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log("1")
        try {
            await fetch(`http://localhost:3000/api/reset-password/?token=${token}`,{
                method:"PUT",
                headers:{"Content-Type":"application/json",
                    'Accept': 'application/json',
                    'Origin': 'http://localhost:3000',
                    "Access-Control-Allow-Origin": "*"},
                body: JSON.stringify({
                    password: password
                })
            })
        } catch (error) {
            console.log("3")
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
                            <h1>Reset Password</h1>

                            <div className="login-form">
                                <form onSubmit={handleSubmit}>
                                    <input
                                        placeholder="password"
                                        type="password"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                    />
                                    <button type="submit">CHANGE PASSWORD</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

    export default ResetPasswordForm;