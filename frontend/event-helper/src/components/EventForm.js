import React,{useEffect,useState} from "react";
import EventTile from "./EventTile";
import EventPage from "../pages/EventPage";

export const Events = () => {

    const [event, setEvent] = useState([]);

    useEffect(() => {
        getEvents().then(r => console.log(r))

    }, []);

    const getEvents = async () =>{
        const response = await fetch(`http://localhost:8080/api/events/8ab04eb6-731a-4ad4-b0f5-1ffffd72ccef`, {
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