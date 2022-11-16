import React,{useEffect,useState} from "react";
import EventTile from "./EventTile";
import EventPage from "../pages/EventPage";

export const Events = (props) => {

    const [event, setEvent] = useState([]);

    useEffect(() => {
        getEvents().then(r => console.log(r))

    }, [props]);

    const getEvents = async (eventId) =>{
        const response = await fetch(`http://localhost:8080/api/events/${eventId}`, {
            method: 'GET',
        });
        const data = await response.json();
        setEvent(data);
    }

    return (
        <>  {event.map((event) => (
            <EventPage name={event.name}
                       location={event.location}
            />
        ))}
        </>
    )
}