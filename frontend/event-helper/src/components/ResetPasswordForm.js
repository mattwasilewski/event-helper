import {useParams} from "react-router-dom";
import {useState} from "react";

function ResetPasswordForm() {

    let { token } = useParams()
    const [password, setPassword] = useState(null)

    const handleInputChange = (e) => {
        const {id, value} = e.target;
        if (id === "password-signup") {
            setPassword(value);
        }
    }


    const sendEmail = async () =>{
    await fetch(`http://localhost:3000/reset-password/${token}`,{
        method:"PUT",
        headers:{"Content-Type":"application/json",
            'Accept': 'application/json',
            'Origin': 'http://localhost:3000',
            "Access-Control-Allow-Origin": "*"},
        body: JSON.stringify({
            password: password,
        })
    })
    }





    return(
        // onSubmit={ sendEmail }
        // <button value="Submit">Reset password</button>
        <div>
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

            <div className="register-button">
                <button onClick={() => sendEmail()} type="submit" id="signup-btn" className="btn">Submit password</button>
            </div>
        </div>
    )
}

    export default ResetPasswordForm;