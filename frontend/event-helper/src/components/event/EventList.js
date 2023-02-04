import React, { useEffect, useState } from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import { TableCell } from '@mui/material'
import { TableContainer } from '@mui/material';
import {TableHead} from  '@mui/material';
import {TableRow} from  '@mui/material';
import {Paper} from  '@mui/material';
import "../../css/App.css"
import AdminNavbar from "../AdminNavbar";


export default function EventList() {
    const [events, setEvents] = useState([]);
    const [statuses, setStatuses] = useState({});
    const EventStatus = {
        TO_VERIFICATION: "TO_VERIFICATION",
        VERIFIED: "VERIFIED",
        BANNED: "BANNED",
        PROMOTED: "PROMOTED"
    };


    const getEvents = async () => {
        const response = await fetch(`http://localhost:3000/api/events/`);
        const data = await response.json();
        setEvents(data);
        console.log(events)
    };

    useEffect(() => {
        getEvents().then(r => console.log(r));
    }, []);


    function cutHtmlTags(html){
        const parser = new DOMParser();
        const parsedHtml = parser.parseFromString(html, "text/html");
        return parsedHtml.body.textContent;
    }


    const handleStatusChange = (eventId, status) => {
        setEvents(prevEvents => prevEvents.map(event => {
            if (event.eventId === eventId) {
                return { ...event, status };
            }
            return event;
        }));
        setStatuses({ ...statuses, [eventId]: status })
        editEventStatus(eventId, status).then(r => console.log(r))
    };


    const editEventStatus = async (eventId, status) => {
        const requestOptions = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8',
                'Access-Control-Allow-Origin': 'http://localhost:3000',
                'Access-Control-Allow-Credentials': 'true'
            },
            body: JSON.stringify({
                eventId: eventId,
                eventStatus: status
            })
        }
        fetch('http://localhost:3000/api/events/set-status', requestOptions)
            .then(response => console.log(response.status))
    }



    return (
        <div>
            <AdminNavbar/>
            <div className="list-header"><h1>Event List</h1></div>
        <div className="event-list-form">
        <TableContainer component={Paper}>
            <Table className="event-table" aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell>Event ID</TableCell>
                        <TableCell align="right">Name</TableCell>
                        <TableCell align="right">Description</TableCell>
                        <TableCell align="right">Price</TableCell>
                        <TableCell align="right">Location</TableCell>
                        <TableCell align="right">Start Date</TableCell>
                        <TableCell align="right">End Date</TableCell>
                        <TableCell align="right">Event Type</TableCell>
                        <TableCell align="right">User ID</TableCell>
                        {/*<TableCell align="right">Image</TableCell>*/}
                        <TableCell align="right">Event Status</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {events.map((event) => (
                        <TableRow key={event.eventId}>
                            <TableCell component="th" scope="row">
                                <a href={'/event/' + event.eventId}>{event.eventId.substring(0, 15)}...</a>
                            </TableCell>
                            <TableCell align="right">{event.name}</TableCell>
                            <TableCell align="right">{cutHtmlTags(event.description).substring(0, 20)}...</TableCell>
                            <TableCell align="right">{event.price}</TableCell>
                            <TableCell align="right">{event.location.substring(0, 30)}...</TableCell>
                            <TableCell align="right">{event.startDate}</TableCell>
                            <TableCell align="right">{event.endDate}</TableCell>
                            <TableCell align="right">{event.eventType}</TableCell>
                            <TableCell align="right">{event.userId}</TableCell>
                            {/*<TableCell align="right">{event.image}</TableCell>*/}
                            <TableCell align="right">

                                <div>
                                    <select value={statuses[event.eventId] || event.eventStatus} onChange={e => handleStatusChange(event.eventId, e.target.value)}>
                                        <option value={EventStatus.TO_VERIFICATION}>To Verification</option>
                                        <option value={EventStatus.VERIFIED}>Verified</option>
                                        <option value={EventStatus.BANNED}>Banned</option>
                                        <option value={EventStatus.PROMOTED}>Promoted</option>
                                    </select>
                                </div>

                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
        </div>
        </div>
    );
}



