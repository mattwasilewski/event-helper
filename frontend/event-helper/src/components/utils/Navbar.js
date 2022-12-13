import "../../css/navbar.css";

export default function Navbar() {

    window.addEventListener("scroll",function(){
        const header = document.querySelector("header");
       header.classList.toggle("sticky",window.scrollY >0);
    })
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