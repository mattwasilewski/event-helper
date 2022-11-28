import {Calendar, dateFnsLocalizer, momentLocalizer} from "react-big-calendar";
import format from "date-fns/format";
import parse from "date-fns/parse";
import startOfWeek from "date-fns/startOfWeek";
import getDay from "date-fns/getDay";
import "react-big-calendar/lib/css/react-big-calendar.css";
import React, {useState} from "react";
import moment from 'moment';


const localizer = momentLocalizer(moment)

const events = [
    {
        title: "Event1",
        id: 2,
        allDay: true,
        start: new Date(2022, 10, 16),
        end: new Date(2022, 10, 19)
    },
    {
        title: "Event2",
        id: 3,
        allDay: true,
        start: new Date(2022, 10, 29),
        end: new Date(2022, 10, 29)
    },
    {
        title: "Event3",
        id: 1,
        allDay: true,
        start: new Date(2022, 10, 27),
        end: new Date(2022, 10, 27)
    }

]


export default function CalendarTile() {


    console.log(events);
    return (
        <div className="calendar-tile">
            <h1 className="header">Events Calendar</h1>
            <div className="calendar-container">
                <Calendar localizer={localizer} events={events} startAccessor="start" endAccessor="end"
                          style={{height: 500, width: "800px"}}></Calendar>
                {/*<Calendar onChange={setDate} value={date}/>*/}
            </div>
            {/*<div className="text-center">*/}
            {/*    /!*Selected date: {date.toDateString()}*!/*/}
            {/*</div>*/}
        </div>
    );
}