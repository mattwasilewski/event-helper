import Navbar from "../components/utils/Navbar";
import {Events} from "../components/event/Events";
import React from "react";
import CalendarTile from "../components/utils/CalendarTile";
import SubscribePanel from "../components/utils/SubscribePanel";
import Footer from "../components/utils/Footer";
import AllEvents from "../components/event/AllEvents";
import RecommendedEvents from "../components/event/RecommendedEvents";

export default function MainPage() {

    return (
        <div>
            <Navbar/>
            <h1>Newest Events</h1>
            <Events sortBy="startDate" asc="descending" phrase=""/>

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