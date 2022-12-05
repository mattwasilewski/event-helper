import React,{useEffect,useState} from "react";
import logo from "../assets/logociemne.png";
import "../EventPage.css"
import switchMode from "../assets/dark-switch.png"
import eventDj from "../assets/dj.png"
import img from "../assets/login-img.png";
import {useParams} from "react-router-dom";

export default function EventPage() {

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
                description: document.getElementById("hah").innerText })
        }
        fetch('http://localhost:3000/api/events/edit-event-description', requestOptions)
            .then(response => console.log(response.status))
        setEditable("false");
        setButton(editButton);
    }

    return (
        <div id="event-page">
            <div id="logo">
                <img src={logo} alt="logo"/>
            </div>
            <div id="frame19">
                <div id="top-nav">
                    <p id="nav-home"><a href="/home">Home</a></p>
                    <p id="nav-events"><a href="/events">Events</a></p>
                    <p id="nav-community"><a href="/community">Community</a></p>
                    <p id="nav-about-us">About us</p>
                    <p id="nav-login">Login</p>
                    <p id="nav-sign-up"><a href="/register">Sign up</a></p>
                    <div id="switch-mode">
                        <img src={switchMode} alt=""/>
                    </div>
                </div>
                <p id="event-name">{event.name}</p>
                {/*<p id="event-description">{event.description}</p>*/}

                <button id="event-description" onClick={(e) => assignToEvent(e)}>Join to event</button>

            </div>
            <div id="destination">
                <img id="rectangle-14" src={eventDj} alt=""/>
                <div id="card-2">
                    <div id="rectangle-15">
                        <p>{event.location}</p>
                        <label id="hah" contentEditable={editable}>{event.description}</label>
                        {button}
                    </div>
                </div>
            </div>
        </div>
    );


}