import "../../css/footer.css"

export default function Footer() {
    return (
        <footer className="footer">
            <div className="container">
                <div className="row">
                    <div className="footer-col">
                        <h4>Events</h4>
                        <ul>
                            <li><a href="">Music Events</a></li>
                            <li><a href="">Expo</a></li>
                            <li><a href="">Festivals</a></li>
                        </ul>
                    </div>
                    <div className="footer-col">
                        <h4>User</h4>
                        <ul>
                            <li><a href="/login">Login</a></li>
                            <li><a href="/register">Register</a></li>
                            <li><a href="/add-event">Add Event</a></li>
                        </ul>
                    </div>
                    <div className="footer-col">
                        <h4>Contact</h4>
                        <ul>
                            <li><a href="">New Jersey 45463</a></li>
                            <li><a href="">(671) 555-0110</a></li>
                            <li><a href="">info@hounter.com</a></li>
                        </ul>
                    </div>
                    <div className="footer-col">
                        <h4>Follow Us</h4>
                        <div className="social-links">
                            <a href="https://www.facebook.com"><i className="fab fa-facebook-f"></i></a>
                            <a href="https://www.twitter.com"><i className="fab fa-twitter"></i></a>
                            <a href="https://www.instagram.com"><i className="fab fa-instagram"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </footer>


    )
}