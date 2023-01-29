import Navbar from "../components/utils/Navbar";
import {Events} from "../components/event/Events";
import React from "react";
import '../css/mainPage.css';
import CalendarTile from "../components/utils/CalendarTile";
import Footer from "../components/utils/Footer";
import AllEvents from "../components/event/AllEvents";
import RecommendedEvents from "../components/event/RecommendedEvents";
import authSerivce from "../auth.serivce";

export default function MainPage() {
    window.addEventListener("scroll", function () {
        const appDescription = document.getElementById("app-desc");
        const addEventButton = document.getElementById("add-event");
        appDescription.classList.toggle("hidden", window.scrollY > 600);
        addEventButton.classList.toggle("hidden", window.scrollY > 500);

    })
    const isLoggedIn = authSerivce.getCurrentUser();
    let userDetails;
    if (isLoggedIn) {
        userDetails = authSerivce.parseJwt(isLoggedIn.value)
    }

    return (

        <>
            <Navbar/>
            <div className="section-1 box">
                <div id="app-desc" className="app-desc">
                    {/*Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer*/}
                    {/*mollis nibh quis malesuada viverra. Quisque fringilla sollicitudin est ac consectetur. Nam bibendum*/}
                    {/*dolor malesuada egestas tristique. Praesent feugiat posuere dapibus.*/}
                    "Event Helper: Your go-to platform for event discovery & participation" - Discover and join events in Wroclaw and around the world. Create an account to add, manage and participate in events.

                </div>
                {userDetails?
                    <a href="/add-event">
                        <button id="add-event" className="add-event">Create Event</button>
                    </a>
                    :
                    <a href="/login">
                        <button id="add-event" className="add-event">Create Event</button>
                    </a>

                }
            </div>
            <div className="section-2 box">

                <h1>Newest events</h1>
                <Events sortBy="startDate" asc="descending" phrase=""/>

            </div>
            <div className="section-3 box">
                <h1>All events</h1>
                <AllEvents/>
            </div>
            <div className="section-4 box">

                <h1>Recommended Events</h1>
                <RecommendedEvents/>

            </div>
            <div className="section-5 box">
                <div className="calendar-background">
                    <CalendarTile/></div>
            </div>
            <div className="section-6">
                <Footer/>
            </div>
        </>)

}

