import React, { useEffect, useState } from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import { TableCell } from '@mui/material'
import { TableContainer } from '@mui/material';
import {TableHead} from  '@mui/material';
import {TableRow} from  '@mui/material';
import {Paper} from  '@mui/material';


export default function EventList() {
    const [events, setEvents] = useState([]);

    const getEvents = async () => {
        const response = await fetch(`http://localhost:3000/api/events/`);
        const data = await response.json();
        setEvents(data);
        console.log(events)
    };

    useEffect(() => {
        getEvents().then(r => console.log(r));
    }, []);

    return (
        <TableContainer component={Paper}>
            <Table aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell>Event ID</TableCell>
                        <TableCell align="right">Name</TableCell>
                        <TableCell align="right">Description</TableCell>
                        <TableCell align="right">Link To Event Page</TableCell>
                        <TableCell align="right">Price</TableCell>
                        <TableCell align="right">Location</TableCell>
                        <TableCell align="right">Event Status</TableCell>
                        <TableCell align="right">Start Date</TableCell>
                        <TableCell align="right">End Date</TableCell>
                        <TableCell align="right">Public Event</TableCell>
                        <TableCell align="right">Event Type</TableCell>
                        <TableCell align="right">User ID</TableCell>
                        <TableCell align="right">Longitude</TableCell>
                        <TableCell align="right">Source</TableCell>
                        {/*<TableCell align="right">Image</TableCell>*/}
                        <TableCell align="right">Assigned Users</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {events.map((event) => (
                        <TableRow key={event.eventId}>
                            <TableCell component="th" scope="row">
                                {event.eventId}
                            </TableCell>
                            <TableCell align="right">{event.name}</TableCell>
                            <TableCell align="right">{event.description}</TableCell>
                            <TableCell align="right">{event.linkToEventPage}</TableCell>
                            <TableCell align="right">{event.price}</TableCell>
                            <TableCell align="right">{event.location}</TableCell>
                            <TableCell align="right">{event.eventStatus}</TableCell>
                            <TableCell align="right">{event.startDate}</TableCell>
                            <TableCell align="right">{event.endDate}</TableCell>
                            <TableCell align="right">{event.publicEvent ? 'Yes' : 'No'}</TableCell>
                            <TableCell align="right">{event.eventType}</TableCell>
                            <TableCell align="right">{event.userId}</TableCell>
                            <TableCell align="right">{event.longitude}</TableCell>
                            <TableCell align="right">{event.source}</TableCell>
                            {/*<TableCell align="right">{event.image}</TableCell>*/}
                            <TableCell align="right">{event.assignedUsers.length}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}



