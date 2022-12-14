import "./EventPage.css";
import Navbar from "../components/utils/Navbar";
import EventTile from "../components/event/EventTile";
import React from "react";
import {useEffect, useState} from "@types/react";

export default function UserPage(props) {

    const [user, setUser] = useState([]);

    useEffect(() => {
        getUser().then(r => console.log(r))

    }, [props.userId]);

    const getUser = async () =>{
        const response = await fetch(`http://localhost:3000/api/user/${props.userId}`);
        const data = await response.json();
        setUser(data);

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
                                    {user.events.map((event) => (
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

                </div>
            </div>
        </>

    )
}