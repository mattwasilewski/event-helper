import Navbar from "../components/utils/Navbar";
import EventTile from "../components/event/EventTile";
import React, {useEffect, useState} from "react";
import "../css/EventPage.css";
import {useParams} from "react-router-dom";
import authSerivce from "../auth.serivce";
import EventChat from "./EventChat";
import imageDefault from "../assets/logociemne.png"
import DOMPurify from 'dompurify';
import verifyIcon from "../assets/verify.png";



export default function EventPage() {
    let { id } = useParams()
    const [event, setEvent] = useState([]);
    const [imageFile, setImageFile] = useState(imageDefault)
    const [buttonText, setButtonText] = useState(["Join Event"]);
    const [imageUrl, setImageUrl] = useState("")
    const [numOfAttendees, setNumOfAttendees] = useState([])
    const [buttonVisibility, setButtonVisibility] = useState(false)

    const isLoggedIn = authSerivce.getCurrentUser();
    const userDetails = (isLoggedIn === null) ? null : authSerivce.parseJwt(isLoggedIn.value)
    let isAssign;



    useEffect(() => {
        getEvents().then(r => console.log(r))
        isAssignToEvent().then(r => console.log(r))
        numberOfAttendees().then(r => console.log(r))
    }, []);

    useEffect(() => {
        setButtonsVisibility().then(r => console.log(r))
    }, [event])


    const setButtonsVisibility = async () => {
        if (isLoggedIn) {
            const response = await fetch(`http://localhost:3000/api/user/${userDetails.sub}`);
            response.json().then(r => {
                if (event.userId === r.userId) {
                    setButtonVisibility(true)
                }
            });
        }
    }

    const getEvents = async () => {
        const response = await fetch(`http://localhost:3000/api/events/${id}`, {
            method: 'GET',
        });
        const data = await response.json();
        setEvent(data);
        setImageFile(data.image.imageData)
        setImageUrl(data.image.standard)
    }

    const numberOfAttendees = async  () => {
        const response = await fetch(`http://localhost:3000/api/events/get-num-attendees/${id}`, {
            method: 'GET',
        });
        const data = await response.json()
        setNumOfAttendees(data)
    }

    const isAssignToEvent = async () => {
        if (isLoggedIn) {
            const response = await fetch(`http://localhost:3000/api/events/is-assign/${id}&${userDetails.sub}`, {
                method: 'GET'
            })
            const data = await response.json();
            isAssign = data
            if (data) {
                setButtonText("Leave Event")
            } else {
                setButtonText("Join Event")
            }
        } else {
            setButtonText("Login to Join Event")
        }
    }

    const assignLeaveEvent = async (e) => {
        if (isLoggedIn) {
            e.preventDefault()
            const requestOptions = {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json; charset=UTF-8',
                    'Access-Control-Allow-Origin': 'http://localhost:3000',
                    'Access-Control-Allow-Credentials': 'true'},
                body: JSON.stringify({
                    eventId: id,
                    userEmail: userDetails.sub })
            }
            fetch('http://localhost:3000/api/events/assign-user-to-event', requestOptions)
                .then(response => {
                    console.log(response.status)
                    isAssignToEvent().then(r => console.log(r))
                    numberOfAttendees().then(r => console.log(r))
                })
        } else {
            window.location.href = "/login"
        }
    }

    const deleteEvent = async (e) => {
        if (isLoggedIn && window.confirm("Are you sure you want delete your event?")) {
            await fetch(`http://localhost:3000/api/events/delete-event/${userDetails.sub}&${id}`, {
                method: 'DELETE'
            })
            window.location.replace("/home")
        }
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
                description: document.getElementById("event-descs").innerText
            })
        }
        fetch('http://localhost:3000/api/events/edit-event-description', requestOptions)
            .then(response => console.log(response.status))
        setEditable("false");
        setButton(editButton);
    }

    function setDefaultImage() {
        if (imageUrl && imageUrl !== "defaultUrl") {
            return <img src={imageUrl} width="80%"/>
        } else if (imageFile === imageDefault) {
            return <img src={imageFile} width="80%"/>
        } else {
            return <img width="80%" src={`data:image/jpeg;base64,${imageFile}`}/>
        }
    }

    function setVerifyIcon() {
        if(event && event.eventStatus === "VERIFIED") {
            return <img className="verify-icon" src={verifyIcon}/>
        }
    }


    return (
        <>
            <Navbar/>
            <div className="wrapper">
                <div className="left">
                    {setDefaultImage()}
                    <h4 className="name">{event.name}</h4>
                    <button type="submit" id="submit-btn" className="btn" onClick={(e) => assignLeaveEvent(e)}>
                        {buttonText}
                    </button>
                    {buttonVisibility &&
                        <button type="submit" id="submit-btn" className="btn" onClick={(e) => deleteEvent(e)}>
                            Delete event
                        </button>}
                </div>
                <div className="right">
                    <div className="info">
                        <h3>Information    {setVerifyIcon()}</h3>
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
                                <p><a href={event.linkToEventPage}>Link to official page</a></p>
                            </div>
                            <div className="data">
                                <h4>Number of attendees</h4>
                                <p>{numOfAttendees}</p>
                            </div>
                        </div>
                    </div>

                    <div className="projects">
                        <h3 id="description">
                            Description
                            {buttonVisibility && <div className="edit-button">{button}</div>}
                        </h3>
                        <div className="projects_data">
                            <div className="data">
                                {/*<label id="event-descs" contentEditable={editable}>{event.description}</label>*/}
                                <p id="event-descs" contentEditable={editable}
                                   dangerouslySetInnerHTML={{__html: DOMPurify.sanitize(event.description)}}></p>
                            </div>
                            {/*<ChatRoom eventId={event.eventId}/>*/}
                        </div>
                        {isLoggedIn?
                            <EventChat eventId={event.eventId}/>
                            :<p>You must be logged In to join chat</p>}
                    </div>
                </div>
            </div>
        </>)
}
