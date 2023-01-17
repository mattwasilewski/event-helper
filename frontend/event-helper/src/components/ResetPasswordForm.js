import {useParams} from "react-router-dom";
import {useState} from "react";
import React from "react";
import {useNavigate} from "react-router-dom";
import RegisterForm from "./RegisterForm";

function ResetPasswordForm() {

    let { token } = useParams()
    let navigate = useNavigate();
    const [password, setPassword] = useState('')
    const [repeatPassword, setRepeatPassword] = useState("");
    const [error, setError] = useState(null);

    const letters = /[a-z]/i;
    const numbers = /[0-9]/;
    const special = /[!@#$%]/;
    const minValue = 8;



    const handleSubmit = async (e) => {
        e.preventDefault();
        if (passwordValidationMsg() === true) {
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
            setPassword("");
            setRepeatPassword("");
            setError(null);
            navigate('/home')
        }
        else {
            passwordValidationMsg();
        }
    };


    const passwordValidationMsg = () => {
        if (password != null) {
            if (password.length >= minValue && password.match(letters) && password.match(numbers)
                && password.match(special)) {
                setError('Perfect Password!')
                return true
            } else if (password.length >= minValue && password.match(letters) && password.match(numbers)) {
                setError('Good Password')
                return true
            } else if (password.length === 0) {
                return false
            } else if (password.length >= minValue) {
                setError('Weak password!')
                return false
            } else if (password !== repeatPassword) {
                setError("Passwords do not match");
                return false
            }
            else {
                setError('Your password is too short')
                return false
            }
        }
    }





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
                                        id="password-signup"
                                        type="password"
                                        onChange={(e) => setPassword(e.target.value)}
                                        value={password}
                                    />
                                    <input
                                        type="password"
                                        placeholder="repeat password"
                                        id="confirm-password-input"
                                        value={repeatPassword}
                                        onChange={(event) => setRepeatPassword(event.target.value)}
                                    />

                                    {error && <div style={{ color: "red" }}>{error}</div>}

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