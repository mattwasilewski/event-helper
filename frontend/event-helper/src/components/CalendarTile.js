import {Calendar, momentLocalizer} from "react-big-calendar";
import "react-big-calendar/lib/css/react-big-calendar.css";
import React, {useState, useEffect} from "react";
import moment from 'moment';
import { useNavigate } from 'react-router-dom' ;

moment.locale('pl', {
    week: {
        dow: 1,
        doy: 1,
    },
});

const localizer = momentLocalizer(moment)


export default function CalendarTile() {
    const [events, setEvents] = useState([]);
    let navigate = useNavigate();
    const getEvents = async () => {
        const response = await fetch(`http://localhost:8080/api/events/sort/date&false&`); //sort?sortBy=name&ascending=true
        const data = await response.json();
        setEvents(data)
    }

    const [selected, setSelected] = useState();

    const handleSelected = (event) => {
        setSelected(event);
        navigate("/event/"+event.eventId)
    };

    useEffect(() => {
        getEvents().then(r => console.log(r))

    }, []);

    return (
        <div className="calendar-tile">
            <h1 className="header">Events Calendar</h1>
            <div className="calendar-container">
                <Calendar
                            selected={selected}
                            onSelectEvent={handleSelected}
                            localizer={localizer}
                            events={events.map((event) => ({title: event.name, start: event.startDate, end: event.endDate, eventId: event.eventId}))}
                            startAccessor="start" endAccessor="end"
                            style={{height: 700, width: "1000px"}}></Calendar>
            </div>
        </div>
    );
}