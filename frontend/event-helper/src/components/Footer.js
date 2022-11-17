import logo from "../assets/logociemne.png"

export default function Footer() {
    return (
        <>
            <div className="footer-logo">
                <img src={logo}/>
                <div className="app-description">
                    We provide information about upcoming events and also support creating your own private party
                </div>
                <div className="social-media-logos">
                    <div className="social-logo">
                        <a href="https://www.facebook.com"><ion-icon name="logo-facebook"></ion-icon></a>
                    </div>
                    <div className="social-logo">
                        <a href="https://www.twitter.com"><ion-icon name="logo-twitter"></ion-icon></a>
                    </div>
                    <div className="social-logo">
                        <a href="https://www.instagram.com"><ion-icon name="logo-instagram"></ion-icon></a>
                    </div>
                </div>
            </div>
            <div className="footer-events">Events
            <ul>
                <a href="/home"><li>Music Events</li></a>
                <a href="/home"><li>Expo</li></a>
                <a href="/home"><li>Conferences</li></a>
            </ul></div>
            <div className="footer-user">User
                <ul>
                    <li><a href="/login">Login</a></li>
                    <li><a href="/register">Register</a></li>
                    <li><a href="/add-event">Add Event</a></li>

               </ul></div>
            <div className="footer-contact">Contact
                <ul>
                    <li>New Jersey 45463</li>
                    <li>(671) 555-0110</li>
                    <li>info@hounter.com</li>
                </ul>
            </div>

        </>
    )
}