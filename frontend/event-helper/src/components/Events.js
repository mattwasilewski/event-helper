import React,{useEffect,useState} from "react";
import EventTile from "./EventTile";

export const Events = (props) => {

    const [events, setEvents] = useState([]);

    useEffect(() => {
        getEvents().then(r => console.log(r))

    }, [props.sortBy, props.asc]);

    const getEvents = async () =>{
        let ascending = true;
        if (props.asc === "descending")  ascending = false;
        const response = await fetch(`http://localhost:8080/api/events/sort/${props.sortBy}&${ascending}`); //sort?sortBy=name&ascending=true
        const data = await response.json();
        setEvents(data);
    }

    return (
        <>  {events.map((event) => (
            <EventTile name={event.name}
                       location={event.location}
                       eventId={event.eventId}
                       logo={event.logo}
            />
        ))}
        </>
    )
}