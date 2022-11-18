import {useState} from "react";
import EventTile from "./EventTile";
import React from "react";
import {useEffect} from "react";

export default function RecommendedEvents() {
    const [events, setEvents] = useState([]);
    const [popularButtonStyle, setPopularButtonStyle] = useState("active-button");
    const [musicButtonStyle, setMusicButtonStyle] = useState("disabled-button");
    const [festivalsButtonStyle, setFestivalsButtonStyle] = useState("disabled-button");

    useEffect(() => {
        getEvents().then(r => console.log(r))

    }, []);

    const getEvents = async () =>{
        const response = await fetch(`http://localhost:8080/api/events/`); //sort?sortBy=name&ascending=true
        const data = await response.json();
        setEvents(data);
    }


    async function popularButtonClick() {
        setPopularButtonStyle(function (prevState) {
                if (prevState === "disabled-button")
                    setFestivalsButtonStyle("disabled-button");
                setMusicButtonStyle("disabled-button");
                return "active-button";
            }
        )
        // const response = await fetch(`http://localhost:8080/api/events/`); //sort?sortBy=name&ascending=true
        // const data = await response.json();
        // console.log(data)
        // setEvents(data);

    }

    async function musicButtonClick() {
        setMusicButtonStyle( function (prevState) {
                if (prevState === "disabled-button")
                    setPopularButtonStyle("disabled-button");
                setFestivalsButtonStyle("disabled-button");
                return "active-button";
            }
        )
        // const response = await fetch(`http://localhost:8080/api/events/getEventByType/2`); //sort?sortBy=name&ascending=true
        // const data = await response.json();
        // setEvents(data);

    }

    async function festivalsButtonClick() {
        setFestivalsButtonStyle(function (prevState) {
                if (prevState === "disabled-button")
                    setPopularButtonStyle("disabled-button");
                setMusicButtonStyle("disabled-button");
                return "active-button";
            }
        )
        // const response = await fetch(`http://localhost:8080/api/events/getEventByType/3`); //sort?sortBy=name&ascending=true
        // const data = await response.json();
        // setEvents(data);
    }


    return (
        <>
            <div className="recommended-events-buttons">
                <button className={popularButtonStyle} onClick={popularButtonClick}>Festivals</button>
                <button className={musicButtonStyle} onClick={musicButtonClick}>Concerts</button>
                <button className={festivalsButtonStyle} onClick={festivalsButtonClick}>Parties</button>
            </div>
            {events.map((event) => (
                <EventTile name={event.name}
                           location={event.location}
                           eventId={event.eventId}
                           logo={event.logo}
                />
            ))}
        </>
    )
}