import {useState} from "react";
import Calendar from 'react-calendar';

export default function CalendarTile(){

    const [date, setDate] = useState(new Date());

    return (
        <div className="calendar-tile">
            <h1 className="header">Events Calendar</h1>
            <div className="calendar-container">
                <Calendar onChange={setDate} value={date}/>
            </div>
            <div className="text-center">
                Selected date: {date.toDateString()}
            </div>
        </div>
    );
}