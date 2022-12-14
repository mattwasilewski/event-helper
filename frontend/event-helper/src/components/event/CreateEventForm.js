import React, {useState} from "react";

import "../../css/App.css"
import {useNavigate} from "react-router-dom";
import axios from "axios";
import EventTile from "./EventTile";
import Footer from "../utils/Footer";
import authSerivce from "../../auth.serivce";

const API_URl = "http://localhost:3000/api/events/";

function CreateEventForm() {
    let navigate = useNavigate();
    const [name, setName] = useState("");
    const [eventType, setEventType] = useState("FESTIVAL");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [location, setLocation] = useState("");
    const [link, setLink] = useState("");
    const [description, setDescription] = useState("");
    const [price, setPrice] = useState("");
    const [publicEvent, setPublicEvent] = useState("");
    const [logo, setLogo] = useState("");


    const handleInputChange = (e) => {
        const {id, value} = e.target;
        switch (id) {
            case "name":
                setName(value)
                break;
            case "eventType":
                setEventType(value)
                break;
            case "startDate":
                setStartDate(value)
                break;
            case "endDate":
                setEndDate(value)
                break;
            case "location":
                setLocation(value)
                break;
            case "link":
                setLink(value)
                break;
            case "description":
                setDescription(value)
                break;
            case "price":
                setPrice(value)
                break;
            case "publicEvent":
                setPublicEvent(value)
                break;
            case "logo":
                setLogo(value);
                break;
        }
    }

    const handleSubmit  = (e) => {
        if (!authSerivce.getCurrentUser()){
            navigate('/login')
            return
        }
        console.log("przeszlo jest user")
        e.preventDefault()
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json; charset=UTF-8'

            },
            body: JSON.stringify({
                name: name,
                startDate: startDate,
                endDate: endDate,
                eventType: eventType,
                location: location,
                linkToEventPage: link,
                description: description,
                price: price,
                publicEvent: publicEvent,
                logo:logo,
                eventStatus: "TO_VERIFICATION"})   // TODO add userId after login implementation
        };
        fetch('http://localhost:3000/api/events/create-event', requestOptions)
            .then(response => console.log(response.status))
        navigate('/home');
    }

    return (
        <>
            <div className="add-event-form">
                <h1>Create New Event</h1>
                {/*<div>*/}
                {/*    <div>*/}
                <input type="text" value={name} onChange={(e) => handleInputChange(e)}
                       id="name" className="input" placeholder="Name"/>
                {/*    </div>*/}
                {/*    <div>*/}
                <select id="eventType" className="input" value={eventType}
                        onChange={(e) => handleInputChange(e)}>
                    <option value="FESTIVAL">Festival</option>
                    <option value="CONCERT">Concert</option>
                    <option value="EXHIBITION">Exhibition</option>
                    <option value="PARTY">Party</option>
                </select>
                {/*    </div>*/}
                {/*    <div>*/}
                <input type="datetime-local" value={startDate} onChange={(e) => handleInputChange(e)}
                       id="startDate" className="input"/>
                {/*    </div>*/}
                {/*    <div>*/}
                <input type="datetime-local" value={endDate} onChange={(e) => handleInputChange(e)}
                       id="endDate" className="input"/>
                <input type="number" value={price} onChange={(e) => handleInputChange(e)}
                       id="price" className="input" placeholder="Price"/>
                {/*    </div>*/}
                {/*    <div>*/}
                <input type="text" value={location} onChange={(e) => handleInputChange(e)}
                       id="location" className="input" placeholder="Location"/>
                {/*    </div>*/}
                {/*    <div>*/}
                <input type="text" value={link} onChange={(e) => handleInputChange(e)}
                       id="link" className="input" placeholder="Link to event page"/>
                <input type="text" value={logo} onChange={(e) => handleInputChange(e)}
                       id="logo" className="input" placeholder="Image URL"/>
                {/*    </div>*/}
                {/*    <div>*/}

                {/*    </div>*/}
                {/*    <div>*/}
                <select id="publicEvent" className="input" value={publicEvent}
                        onChange={(e) => handleInputChange(e)}>
                    <option value="true">PRIVATE</option>
                    <option value="false">PUBLIC</option>
                </select>
                {/*    </div>*/}
                {/*    <div>*/}

                {/*    </div>*/}
                {/*    <div>*/}
                <textarea value={description} onChange={(e) => handleInputChange(e)}
                          id="description" className="big-input" placeholder="Description"/>
                {/*    </div>*/}
                {/*    <div>*/}
                <button onClick={(e) => handleSubmit(e)} type="submit" id="submit-btn" className="btn">
                    Create Event
                </button>
                {/*    </div>*/}
                {/*</div>*/}
            </div>
        </>
    )
}

export default CreateEventForm
