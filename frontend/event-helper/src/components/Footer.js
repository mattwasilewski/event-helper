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
                        <ion-icon name="logo-facebook"></ion-icon>
                    </div>
                    <div className="social-logo">
                        <ion-icon name="logo-twitter"></ion-icon>
                    </div>
                    <div className="social-logo">
                        <ion-icon name="logo-instagram"></ion-icon>
                    </div>
                </div>
            </div>
            <div className="footer-events">Events
            <ul>
                <li>Music Events</li>
                <li>Expo</li>
                <li>Conferences</li>
            </ul></div>
            <div className="footer-user">User
                <ul>
                    <li>Login</li>
                    <li>Register</li>
                    <li>Add Event</li>
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