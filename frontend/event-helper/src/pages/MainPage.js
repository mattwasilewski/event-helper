import Navbar from "../components/Navbar";
import NewestEvents from "../components/NewestEvents";
import RecommendedEvents from "../components/RecommendedEvents";
import CalendarTile from "../components/CalendarTile";
import SubscribePanel from "../components/SubscribePanel";
import Footer from "../components/Footer";

export default function MainPage() {

    return (
        <div>
            <Navbar/>
            <h1>Newest Events</h1>
            <NewestEvents/>
            <h1>Recommended Events</h1>
            <RecommendedEvents/>
            <CalendarTile/>
            <SubscribePanel/>
            <Footer/>
        </div>
    )

}