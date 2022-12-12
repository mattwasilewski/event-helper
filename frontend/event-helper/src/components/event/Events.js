import React,{useEffect,useState} from "react";
import EventTile from "./EventTile";

export const Events = (props) => {

    const [events, setEvents] = useState([]);

    useEffect(() => {
        getEvents().then(r => console.log(r))

    }, [props.sortBy, props.asc, props.phrase]);

    const getEvents = async () =>{
        let ascending = true;
        if (props.asc === "descending")  ascending = false;
        const response = await fetch(`http://localhost:3000/api/events/sort/${props.sortBy}&${ascending}&${props.phrase}&0&5`);
        const data = await response.json();
        setEvents(data);
    }

    return (
        <>  {events.map((event) => (
            <EventTile name={event.name}
                       location={event.location}
                       eventId={event.eventId}
                       logo={event.logo}
                       startDate={event.startDate}
                       eventType={event.eventType}
                       description={event.description}
            />
        ))}
        </>
    )
}