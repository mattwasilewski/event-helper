import Navbar from "../components/Navbar";
import NewestEvents from "../components/NewestEvents";
import RecommendedEvents from "../components/RecommendedEvents";
import CalendarTile from "../components/CalendarTile";
import Calendar from "react-calendar";
import React,{useState} from "react";

export default function MainPage() {

    const [date, setDate] = useState(new Date());

    return (
        <div>
            <Navbar/>
            <h1>Newest Events</h1>
            <NewestEvents/>
            <h1>Recommended Events</h1>
            <RecommendedEvents/>
            <CalendarTile/>
        </div>
    )

}