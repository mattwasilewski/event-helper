import React,{useEffect,useState} from "react";

export const AssignedEvents = (props) => {

    const [events, setEvents] = useState([]);

    useEffect(() => {
        getEvents().then(r => console.log(r))

    }, [props.userId]);

    const getEvents = async () =>{
        const response = await fetch(`http://localhost:3000/api/events/assigned-events/${props.userId}`);
        const data = await response.json();
        setEvents(data);
        console.log(data)
    }

    return (
        <>
            <h1>Events</h1>
        </>
    )
}