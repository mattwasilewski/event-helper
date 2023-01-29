import React,{useEffect,useState} from "react";
import EventTile from "./EventTile";
import imageDefault from "../../assets/logociemne.png";

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

    function setDefaultImage(event) {
        if(event.image && event.image.standard && event.image.standard !== "defaultUrl") {
            return <img src={event.image.standard}/>
        } else if (event.image && event.image.imageData) {
            return <img src={`data:image/jpeg;base64,${event.image.imageData}`}/>
        } else {
            return <img src={imageDefault}/>
        }
    }

    return (
        <>  {events.map((event) => (
            <EventTile name={event.name}
                       location={event.location}
                       eventId={event.eventId}
                       logo={setDefaultImage(event)}
                       startDate={event.startDate}
                       eventType={event.eventType}
                       description={event.description}
            />
        ))}
        </>
    )
}