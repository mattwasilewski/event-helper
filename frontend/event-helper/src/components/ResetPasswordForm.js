import {useParams} from "react-router-dom";


function ResetPasswordForm() {
let { token } = useParams()


const sendEmail = async () =>{
    await fetch(`http://localhost:8080/reset-password/${token}`,{
        method:"PUT",
        headers:{"Content-Type":"application/json",
            'Accept': 'application/json',
            'Origin': 'http://localhost:3000',
            "Access-Control-Allow-Origin": "*"},
        body:JSON.stringify(token)
    })
}


return(
    <form
        onSubmit={ sendEmail }
    >
        <button value="Submit">Reset password</button>

    </form>

)
}

export default ResetPasswordForm;