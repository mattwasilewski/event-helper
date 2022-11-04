import Navbar from "../components/Navbar";
import NewestEvents from "../components/NewestEvents";

export default function MainPage() {
    return (
        <div>
            <Navbar/>
            <h1>Newest Events</h1>
            <NewestEvents/>
            <h1>Recommended Events</h1>
        </div>
    )

}