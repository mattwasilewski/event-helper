import "./EventPage.css";
import Navbar from "../components/utils/Navbar";
import EventTile from "../components/event/EventTile";
import React from "react";

export default function UserPage() {

    return (
        <>
            <Navbar/>
            <div className="wrapper">
                <div className="left">
                    <img src="https://i.imgur.com/cMy8V5j.png" alt="user" width="80%"/>
                    <h4>Alex William</h4>
                    <div className="social_media">
                        <ul>
                            <li><a href="#"><i className="fab fa-facebook-f"></i></a></li>
                            <li><a href="#"><i className="fab fa-twitter"></i></a></li>
                            <li><a href="#"><i className="fab fa-instagram"></i></a></li>
                        </ul>
                    </div>
                </div>
                <div className="right">
                    <div className="info">
                        <h3>Information</h3>
                        <div className="info_data">
                            <div className="data">
                                <h4>Email</h4>
                                <p>alex@gmail.com</p>
                            </div>
                            <div className="data">
                                <h4>Location</h4>
                                <p>Krakow</p>
                            </div>
                            <div className="data">
                                <h4>Age</h4>
                                <p>22</p>
                            </div>
                        </div>
                    </div>

                    <div className="projects">
                        <h3>Events</h3>
                        <div className="projects_data">
                            <div className="serv">
                                <ul>
                                    <li><EventTile name={"Fajny"}
                                                   location={"Dupa"}
                                                   eventId={"aaaa"}
                                                   logo={"event.logo"}
                                                   startDate={"event.startDate"}
                                                   eventType={"event.eventType"}
                                                   description={"EYP"}
                                    /></li>
                                    <li><EventTile name={"Fajny"}
                                                   location={"Dupa"}
                                                   eventId={"aaaa"}
                                                   logo={"event.logo"}
                                                   startDate={"event.startDate"}
                                                   eventType={"event.eventType"}
                                                   description={"EYP"}
                                    /></li>
                                    <li><EventTile name={"Fajny"}
                                                   location={"Dupa"}
                                                   eventId={"aaaa"}
                                                   logo={"event.logo"}
                                                   startDate={"event.startDate"}
                                                   eventType={"event.eventType"}
                                                   description={"EYP"}
                                    /></li>
                                    <li><EventTile name={"Fajny"}
                                                   location={"Dupa"}
                                                   eventId={"aaaa"}
                                                   logo={"event.logo"}
                                                   startDate={"event.startDate"}
                                                   eventType={"event.eventType"}
                                                   description={"EYP"}
                                    /></li>
                                    <li><EventTile name={"Fajny"}
                                                   location={"Dupa"}
                                                   eventId={"aaaa"}
                                                   logo={"event.logo"}
                                                   startDate={"event.startDate"}
                                                   eventType={"event.eventType"}
                                                   description={"EYP"}
                                    /></li>
                                    <li><EventTile name={"Fajny"}
                                                   location={"Dupa"}
                                                   eventId={"aaaa"}
                                                   logo={"event.logo"}
                                                   startDate={"event.startDate"}
                                                   eventType={"event.eventType"}
                                                   description={"EYP"}
                                    /></li>
                                </ul>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </>

    )
}