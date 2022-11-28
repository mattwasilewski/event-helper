import Navbar from "../components/Navbar";
import {Events} from "../components/Events";
import React from "react";
import CalendarTile from "../components/CalendarTile";
import SubscribePanel from "../components/SubscribePanel";
import Footer from "../components/Footer";
import AllEvents from "../components/AllEvents";
import RecommendedEvents from "../components/RecommendedEvents";

export default function MainPage() {

    return (
        <div>
            <Navbar/>
            <h1>Newest Events</h1>
            <Events sortBy="date" asc="descending" phrase="phrase"/>

            <h1>All events</h1>
            <AllEvents/>
            <h1>Recommended Events</h1>
            <RecommendedEvents/>
            <CalendarTile/>
            <SubscribePanel/>
            <Footer/>
        </div>
    )

}