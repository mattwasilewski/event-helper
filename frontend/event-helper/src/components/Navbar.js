import logo from "../assets/logo3.png";

export default function Navbar() {
    return (
        <nav className="nav">
            <a href="/home" className="site-logo">
                <img src={logo} alt="Event Helper"/>
            </a>
            <div className="toggle-button">
                <span className="bar"></span>
                <span className="bar"></span>
                <span className="bar"></span>
            </div>
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
                        <a href="/login">
                            <button className="loginButton">Login</button>
                        </a>
                    </li>
                    <li>
                        <a href="/register">
                            <button className="signUpButton">Sign Up</button>
                        </a>
                    </li>
                </ul>
        </nav>
    )
}