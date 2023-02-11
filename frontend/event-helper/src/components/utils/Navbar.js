import "../../css/navbar.css";
import authSerivce from "../../auth.serivce";
import authHeader from "../auth-header";
import AuthService from "../../auth.serivce";
import {useNavigate} from "react-router-dom";

export default function Navbar() {
    let navigate = useNavigate();
    window.addEventListener("scroll",function(){
        const header = document.querySelector("header");
        header.classList.toggle("sticky",window.scrollY >0);
    })

function handleLogout(e){
        e.preventDefault()
        AuthService.logout();
        navigate('/home');
        window.location.reload();
    }

    const isLoggedIn = authSerivce.getCurrentUser();
    let userDetails;
    if (isLoggedIn){
        userDetails = authSerivce.parseJwt(isLoggedIn.value)
        return (<>
                <header>
                    <a href="/home" className="logo">Event Helper</a>
                    <ul>
                        <li>
                            <a href="/home">Home</a>
                        </li>
                        <li>
                            <a href="/events">Events</a>
                        </li>
                        <li>
                            <a href="/community">Community</a>
                        </li>
                        <li>
                            <a href="/about">About Us</a>
                        </li>
                        <li>
                            <a id="user-name" href={'/user/'}>{userDetails.sub}

                            </a>
                        </li>
                        <li>
                            <a href="/logout" onClick={handleLogout}>Logout
                            </a>
                        </li>

                    </ul>
                </header>
            </>
        )

    } else{
        return (<>
                <header>
                    <a href="/home" className="logo">Event Helper</a>
                    <ul>
                        <li>
                            <a href="/home">Home</a>
                        </li>
                        <li>
                            <a href="/events">Events</a>
                        </li>
                        <li>
                            <a href="/community">Community</a>
                        </li>
                        <li>
                            <a href="/about">About Us</a>
                        </li>
                        <li>
                            <a href="/login">Login

                            </a>
                        </li>
                        <li>
                            <a href="/register">Register
                            </a>
                        </li>

                    </ul>
                </header>
            </>
        )
    }


}