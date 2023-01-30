import "../css/EventPage.css";
import Navbar from "../components/utils/Navbar";
import EventTile from "../components/event/EventTile";
import React from "react";
import {useEffect, useState} from "react";
import authSerivce from "../auth.serivce";
import CalendarTile from "../components/utils/CalendarTile";
import AuthService from "../auth.serivce";

export default function UserPage(props) {

    const [user, setUser] = useState([]);

    const [events, setEvents] = useState([]);

    useEffect(() => {
        getUser().then(getEvents)

    }, [props.userId]);

    const isLoggedIn = authSerivce.getCurrentUser();
    const userDetails = authSerivce.parseJwt(isLoggedIn.value)

    const getUser = async () =>{
        const response = await fetch(`http://localhost:3000/api/user/${userDetails.sub}`);
        const data = await response.json();
        setUser(data);

    }

    const getEvents = async() =>{
        const response2 = await fetch(`http://localhost:3000/api/events/assign-to-user/${userDetails.sub}&10&10`);
        const data2 = await response2.json();
        setEvents(data2);
    }

    const deleteAccount = async () => {
        await fetch(`http://localhost:3000/api/delete-account/${userDetails.sub}`, {
            method: 'DELETE'
        })
        AuthService.logout();
        window.location.replace("/home")
    }

    return (
        <>
            <Navbar/>
            <div className="wrapper">
                <div className="left">
                    <img src="https://i.imgur.com/cMy8V5j.png" alt="user" width="80%"/>
                    <h4>{user.name}</h4>
                    <div className="social_media">
                        <ul>
                            <li><a href="#"><i className="fab fa-facebook-f"></i></a></li>
                            <li><a href="#"><i className="fab fa-twitter"></i></a></li>
                            <li><a href="#"><i className="fab fa-instagram"></i></a></li>
                        </ul>
                    </div>
                    <button type="submit" id="submit-btn" className="btn" onClick={(e) => deleteAccount(e)}>
                        Delete account
                    </button>
                </div>
                <div className="right">
                    <div className="info">
                        <h3>Information</h3>
                        <div className="info_data">
                            <div className="data">
                                <h4>Email</h4>
                                <p>{user.email}</p>
                            </div>
                            <div className="data">
                                <h4>Location</h4>
                                <p>Krakow</p>
                            </div>
                            <div className="data">
                                <h4>Age</h4>
                                <p>{user.age}</p>
                            </div>
                        </div>
                    </div>

                    <div className="projects">
                        <h3>Events</h3>
                        <div className="projects_data">
                            <div className="serv">
                                <ul>
                                    {events.map((event) => (
                                        <li><EventTile name={event.name}
                                                       location={event.location}
                                                       eventId={event.eventId}
                                                       logo={event.logo}
                                                       startDate={event.startDate}
                                                       eventType={event.eventType}
                                                       description={event.description}
                                        /></li>
                                    ))}
                                </ul>
                            </div>

                        </div>
                    </div>
                    <div className="calendar-background">
                        <CalendarTile name="user" userId={userDetails.sub}/>
                    </div>

                </div>
            </div>
        </>

    )
}