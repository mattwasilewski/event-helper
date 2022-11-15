import React, {useEffect, useState} from "react";

export const Test = (props) => {

    const [events, setEvents] = useState([]);

    useEffect(() => {
        getEvents().then(r => console.log(r))

        console.log("odświeżyłem się: " + props.sortBy);
    }, [props.sortBy]);

    const getEvents = async () =>{
        console.log("Log z test: " + props.sortBy)
        const response = await fetch(`http://localhost:8080/api/events/sort/${props.sortBy}&false`); //sort?sortBy=name&ascending=true
        const data = await response.json();
        setEvents(data);
        console.log(events.map((event) => event.name))
    }

    return (
        <>  {events.map((event) => {return <div className="event-tile">
            <div className="event-photo-tile">
                <div className="event-rating-tile">
                    <div className="star">★</div>
                    <div className="rating">4.4</div>
                </div>
            </div>
            <div className="event-name-tile">{event.name}</div>
            <div className="event-location-tile">
                <span className="material-symbols-outlined location-symbol">location_on</span>
                <div className="location-text">{event.location}</div>
            </div>
        </div>})}
        </>
    )
};
