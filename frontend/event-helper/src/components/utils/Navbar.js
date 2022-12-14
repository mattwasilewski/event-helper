import "../../css/navbar.css";
import authSerivce from "../../auth.serivce";
import authHeader from "../auth-header";
import AuthService from "../../auth.serivce";

export default function Navbar() {

    window.addEventListener("scroll",function(){
        const header = document.querySelector("header");
        header.classList.toggle("sticky",window.scrollY >0);
    })

    function handleLogout(e){
        e.preventDefault()
        AuthService.logout();
    }

    const isLoggedIn = authSerivce.getCurrentUser();
    let userDetails;
    if (isLoggedIn){
        userDetails = authSerivce.parseJwt(isLoggedIn.value)
        console.log("MAM USERA JEST ZALOGOWANY!")
        // const userName = document.querySelector('#user-name')
        // userName.textContent = userDetails.sub
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
                            <a id="user-name" href="/login">{userDetails.sub}

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