import {Calendar, dateFnsLocalizer, momentLocalizer} from "react-big-calendar";
import format from "date-fns/format";
import parse from "date-fns/parse";
import startOfWeek from "date-fns/startOfWeek";
import getDay from "date-fns/getDay";
import "react-big-calendar/lib/css/react-big-calendar.css";
import React, {useState, useEffect} from "react";
import moment from 'moment';


moment.locale('pl', {
    week: {
        dow: 1,
        doy: 1,
    },
});

const localizer = momentLocalizer(moment)


export default function CalendarTile() {
    const [events, setEvents] = useState([]);

    const getEvents = async () => {
        const response = await fetch(`http://localhost:8080/api/events/sort/date&false&`); //sort?sortBy=name&ascending=true
        const data = await response.json();
        setEvents(data)
    }

    useEffect(() => {
        getEvents().then(r => console.log(r))

    }, []);

    console.log(events);
    return (
        <div className="calendar-tile">
            <h1 className="header">Events Calendar</h1>
            <div className="calendar-container">
                <Calendar localizer={localizer}
                          events={events.map((event) => ({title: event.name, start: event.date, end: event.date}))}
                          startAccessor="start" endAccessor="end"
                          style={{height: 500, width: "800px"}}></Calendar>
                {/*<Calendar onChange={setDate} value={date}/>*/}
            </div>
            {/*<div className="text-center">*/}
            {/*    /!*Selected date: {date.toDateString()}*!/*/}
            {/*</div>*/}
        </div>
    );
}