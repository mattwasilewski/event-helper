import {useState} from "react";


function ForgotPasswordForm() {

    const[email,setEmail]=useState('')

    const sendEmail = async () =>{
        const response = await fetch(`http://localhost:8080/forgot-password?email=${email}`,{
            method:"POST",
            headers:{"Content-Type":"application/json",
                'Accept': 'application/json',
                'Origin': 'http://localhost:3000'},
            body:JSON.stringify(email)
        })
        const data = await response.json();
        setEmail(data);
    }

    return(
        <form
        onSubmit={ sendEmail }
    >
        <input
            form="email"
            onChange={(e) => setEmail(e.target.value) }
            value={email}
            type="text"
            required={true}
        />
        <button value="Submit">Send Email</button>
    </form>
    )
}

export default ForgotPasswordForm;