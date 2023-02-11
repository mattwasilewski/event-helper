import "../css/EventPage.css";
import Navbar from "../components/utils/Navbar";
import EventTile from "../components/event/EventTile";
import React from "react";
import {useEffect, useState} from "react";
import authSerivce from "../auth.serivce";
import CalendarTile from "../components/utils/CalendarTile";
import AuthService from "../auth.serivce";
import UsersChat  from "./UsersChat";

export default function UserPage(props) {

    const [user, setUser] = useState([]);

    const [events, setEvents] = useState([]);
    const [informationButtonStyle, setInformationButtonStyle] = useState("information active");
    const [chatButtonStyle, setChatButtonStyle] = useState("information");
    const [showInformation, setShowInformation]= useState(true);

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
        if (window.confirm("Are you sure that you want permanently remove your account")) {
            await fetch(`http://localhost:3000/api/delete-account/${userDetails.sub}`, {
                method: 'DELETE'
            })
            AuthService.logout();
            window.location.replace("/home")
        }
    }

    const changeButton = async() =>{
        if(informationButtonStyle === "information active"){
            setChatButtonStyle("information active");
            setInformationButtonStyle("information");
            setShowInformation(false);
        }else{
            setInformationButtonStyle("information active")
            setChatButtonStyle("information")
            setShowInformation(true);
        }
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
                        <button className={informationButtonStyle} onClick={changeButton}><h3>Information</h3></button>
                        <button className={chatButtonStyle} onClick={changeButton}><h3>Chat</h3></button>
                        {showInformation?
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
                            : <UsersChat/>}
                    </div>
                    {showInformation?
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
                    </div> :<></>}
                    {showInformation?
                    <div className="calendar-background">
                        <CalendarTile name="user" userId={userDetails.sub}/>
                    </div>
                        :<></>}

                </div>
            </div>
        </>

    )
}