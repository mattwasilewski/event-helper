import logo from "../assets/logo-jasne.png";

export default function WelcomePage() {
    return (
        <div className="welcome-page">
            <div className="welcome-page-gif-section">
                <a href={"/home"}><img className="welcome-page-logo" src={logo} alt="Event Helper"/></a>
            </div>
        </div>
    )

}