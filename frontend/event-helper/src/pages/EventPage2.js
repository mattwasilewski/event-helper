import Navbar from "../components/utils/Navbar";
import EventTile from "../components/event/EventTile";
import React, {useEffect, useState} from "react";
import "./EventPage.css";
import {useParams} from "react-router-dom";
export default function EventPage2() {
    let { id } = useParams()
    const [event, setEvent] = useState([]);

    useEffect(() => {
        getEvents().then(r => console.log(r))

    }, []);

    const getEvents = async () =>{
        const response = await fetch(`http://localhost:3000/api/events/${id}`, {
            method: 'GET',
        });
        const data = await response.json();
        setEvent(data);

    }

    const assignToEvent = async (e) => {
        e.preventDefault()
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json; charset=UTF-8',
                'Access-Control-Allow-Origin': 'http://localhost:3000',
                'Access-Control-Allow-Credentials': 'true'},
            body: JSON.stringify({
                eventId: id,
                userId: "af8afa53-4d00-4482-9758-c174b238dddb" })
        }
        fetch('http://localhost:3000/api/events/assign-user-to-event', requestOptions)
            .then(response => console.log(response.status))
    }

    const [editable, setEditable] = useState(["false"]);
    const saveButton = <button onClick={(e) => editEventDescription(e)}>Save</button>;
    const enableEditing = () =>{
        setEditable("true");
        setButton(saveButton)
    }
    const editButton = <button onClick={enableEditing}>Edit</button>;
    const [button, setButton] = useState([editButton]);
    const editEventDescription = async () => {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json; charset=UTF-8',
                'Access-Control-Allow-Origin': 'http://localhost:3000',
                'Access-Control-Allow-Credentials': 'true'},
            body: JSON.stringify({
                eventId: id,
                description: document.getElementById("event-descs").innerText })
        }
        fetch('http://localhost:3000/api/events/edit-event-description', requestOptions)
            .then(response => console.log(response.status))
        setEditable("false");
        setButton(editButton);
    }



    return (
        <>
            <Navbar/>
            <div className="wrapper">
                <div className="left">
                    <img src="https://i.imgur.com/cMy8V5j.png" alt="user" width="80%"/>
                    <h4>{event.name}</h4>
                    <button  type="submit" id="submit-btn" className="btn">
                        Join Event
                    </button>
                </div>
                <div className="right">
                    <div className="info">
                        <h3>Information</h3>
                        <div className="info_data">
                            <div className="data">
                                <h4>Start Date</h4>
                                <p>{event.startDate}</p>
                            </div>
                            <div className="data">
                                <h4>Location</h4>
                                <p>{event.location}</p>
                            </div>
                            <div className="data">
                                <h4>Link</h4>
                                <p><a href={event.linkToEventPage}>Link to official page</a> </p>
                            </div>
                        </div>
                    </div>

                    <div className="projects">
                        <h3>Events</h3>
                        <div className="projects_data">
                            <div className="data">
                                <h4>Description {button}</h4>
                                <label id="event-descs" contentEditable={editable}>{event.description}</label>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </>)
}