import React,{useEffect,useState} from "react";
import EventTile from "./EventTile";

export default function NewestEvents() {
    const [events,setEvents] = useState([]);

    useEffect(()=>{getEvents().then(r=> console.log(r));},[]);

    const getEvents = async () =>{
        const response = await fetch(`http://localhost:8080/api/events/`);
        const data = await response.json();
        setEvents(data);
    }

    return (
        <>  {events.map((event) => (
            <EventTile name={event.name}
                       location={event.location}
            />
        ))}
        </>
    )
}