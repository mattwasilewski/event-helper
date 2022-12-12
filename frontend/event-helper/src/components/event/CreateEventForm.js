import React, {useState} from "react";
import img from "../assets/login-img.png";
import switchMode from "../assets/dark-switch.png"
import "../App.css"
import {useNavigate} from "react-router-dom";
import axios from "axios";

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


    const handleInputChange = (e) => {
        const {id , value} = e.target;
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
        }
    }

    const handleSubmit  = (e) => {
        e.preventDefault()
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json; charset=UTF-8'},
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
                eventStatus: "TO_VERIFICATION"})   // TODO add userId after login implementation
        };
        // fetch('http://localhost:3000/api/events/create-event', requestOptions)
        //     .then(response => console.log(response.status))
        // navigate('/home');
        return axios.post(API_URl + "create-event", requestOptions).then(response => console.log(response.status))
    }

    return (
        <div className="form" >
            <div>
                <h1>Add new event</h1>
                <div>
                    <input type="text" value={name} onChange={(e) => handleInputChange(e)}
                        id="name" className="input" placeholder="Name"/>
                </div>
                <div>
                    <select id="eventType" className="input" value={eventType}
                            onChange={(e) => handleInputChange(e)}>
                        <option value="FESTIVAL">Festival</option>
                        <option value="CONCERT">Concert</option>
                        <option value="EXHIBITION">Exhibition</option>
                        <option value="PARTY">Party</option>
                    </select>
                </div>
                <div>
                    <input type="date" value={startDate} onChange={(e) => handleInputChange(e)}
                        id="startDate" className="input"/>
                </div>
                <div>
                    <input type="date" value={endDate} onChange={(e) => handleInputChange(e)}
                           id="endDate" className="input"/>
                </div>
                <div>
                    <input type="text" value={location} onChange={(e) => handleInputChange(e)}
                        id="location" className="input" placeholder="Location"/>
                </div>
                <div>
                    <input type="text" value={link} onChange={(e) => handleInputChange(e)}
                        id="link" className="input" placeholder="Link to event page"/>
                </div>
                <div>
                    <input type="number" value={price} onChange={(e) => handleInputChange(e)}
                        id="price" className="input" placeholder="Price"/>
                </div>
                <div>
                    <select id="publicEvent" className="input" value={publicEvent}
                            onChange={(e) => handleInputChange(e)}>
                        <option value="true">PRIVATE</option>
                        <option value="false">PUBLIC</option>
                    </select>
                </div>
                <div>

                </div>
                <div>
                    <textarea value={description} onChange={(e) => handleInputChange(e)}
                          id="description" className="big-input" placeholder="Description"/>
                </div>
                <div>
                   <button onClick={(e) => handleSubmit(e)} type="submit" id="submit-btn" className="btn">
                        Add Event
                    </button>
                </div>
            </div>
        </div>
    )
}

export default CreateEventForm
