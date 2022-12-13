import React, {useEffect, useState} from "react";
import "../css/EventPage.css"
import img from "../assets/login-img.png";
import {useParams} from "react-router-dom";
import Navbar from "../components/utils/Navbar";
import Footer from "../components/utils/Footer";
import CalendarTile from "../components/utils/CalendarTile";
import SubscribePanel from "../components/utils/SubscribePanel"
import {Container} from "@mui/material";


export default function EventPage() {


    let {id} = useParams()
    const [event, setEvent] = useState([]);


    useEffect(() => {
        getEvents().then(r => console.log(r))

    }, []);

    const getEvents = async () => {
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
            headers: {
                'Content-Type': 'application/json; charset=UTF-8',
                'Access-Control-Allow-Origin': 'http://localhost:3000',
                'Access-Control-Allow-Credentials': 'true'
            },
            body: JSON.stringify({
                eventId: id,
                userId: "af8afa53-4d00-4482-9758-c174b238dddb"
            })
        }
        fetch('http://localhost:3000/api/events/assign-user-to-event', requestOptions)
            .then(response => console.log(response.status))
    }

    const [editable, setEditable] = useState(["false"]);
    const saveButton = <button onClick={(e) => editEventDescription(e)}>Save</button>;
    const enableEditing = () => {
        setEditable("true");
        setButton(saveButton)
    }
    const editButton = <button onClick={enableEditing}>Edit</button>;
    const [button, setButton] = useState([editButton]);
    const editEventDescription = async () => {
        const requestOptions = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8',
                'Access-Control-Allow-Origin': 'http://localhost:3000',
                'Access-Control-Allow-Credentials': 'true'
            },
            body: JSON.stringify({
                eventId: id,
                description: document.getElementById("hah").innerText
            })
        }
        fetch('http://localhost:3000/api/events/edit-event-description', requestOptions)
            .then(response => console.log(response.status))
        setEditable("false");
        setButton(editButton);
    }

    let count = 0;
    function showMore() {
        if (count % 2 === 0){
            document.querySelector("#eventDataSmall").id = "eventDataBig"
            count += 1;
        } else {
            document.querySelector("#eventDataBig").id = "eventDataSmall"
            count += 1;
        }


    }



    return (
        <div>
            <Navbar/>
            <div className="data-container">
            <p className="event-header white-container">{event.name}</p>
            <Container id="eventDataSmall" className="box-data box">

                <button id="event-join" onClick={(e) => assignToEvent(e)}>Join to event</button>

                <div className="card">
                    <img id="eventImg" src={event.logo} alt=""/>
                    <p>{event.location}</p>
                </div>

                <Container id="eventText">
                <p contentEditable={editable} dangerouslySetInnerHTML={{__html: event.description}}></p>
                    <button id="edit-description">{button}</button>
                </Container>
            </Container>
                <div onClick={showMore} className="swiper-button-next show-more-button"></div>
            </div>
            <CalendarTile/>
            <SubscribePanel/>
            <Footer/>
        </div>
    );


}