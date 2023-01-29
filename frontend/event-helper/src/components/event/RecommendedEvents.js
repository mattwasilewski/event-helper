import {useState} from "react";
import EventTile from "./EventTile";
import React from "react";
import {useEffect} from "react";
import imageDefault from "../../assets/logociemne.png";

export default function RecommendedEvents() {
    const [events, setEvents] = useState([]);
    const [popularButtonStyle, setPopularButtonStyle] = useState("active-button");
    const [musicButtonStyle, setMusicButtonStyle] = useState("disabled-button");
    const [festivalsButtonStyle, setFestivalsButtonStyle] = useState("disabled-button");

    useEffect(() => {
        getEvents().then(r => console.log(r))

    }, []);

    const getEvents = async () =>{
        const response = await fetch(`http://localhost:3000/api/events/getEventByType/FESTIVAL&0&5`);
        const data = await response.json();
        setEvents(data);
    }


    async function popularButtonClick() {
        const response = await fetch(`http://localhost:3000/api/events/getEventByType/FESTIVAL&0&5`);
        const data = await response.json();
        setEvents(data);
        setPopularButtonStyle(function (prevState) {
                if (prevState === "disabled-button")
                    setFestivalsButtonStyle("disabled-button");
                setMusicButtonStyle("disabled-button");
                return "active-button";
            }
        )

    }

    async function musicButtonClick() {
        const response = await fetch(`http://localhost:3000/api/events/getEventByType/CONCERT&0&5`);
        const data = await response.json();
        setEvents(data);
        setMusicButtonStyle( function (prevState) {
                if (prevState === "disabled-button")
                    setPopularButtonStyle("disabled-button");
                setFestivalsButtonStyle("disabled-button");
                return "active-button";
            }
        )

    }

    async function festivalsButtonClick() {
        const response = await fetch(`http://localhost:3000/api/events/getEventByType/PARTY&0&5`);
        const data = await response.json();
        setEvents(data);
        setFestivalsButtonStyle(function (prevState) {
                if (prevState === "disabled-button")
                    setPopularButtonStyle("disabled-button");
                setMusicButtonStyle("disabled-button");
                return "active-button";
            }
        )
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
                           logo={setDefaultImage(event)}
                           startDate={event.startDate}
                           eventType={event.eventType}
                           description={event.description}
                />
            ))}
        </>
    )
}