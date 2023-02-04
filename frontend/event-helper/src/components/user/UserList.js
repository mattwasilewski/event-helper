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


export default function UserList() {
    const [users, setUsers] = useState([]);

    const getUsers = async () => {
        const response = await fetch(`http://localhost:3000/api/users`);
        const data = await response.json();
        setUsers(data);
        console.log(users)
    };

    useEffect(() => {
        getUsers().then(r => console.log(r));
    }, []);


    function cutHtmlTags(html){
        const parser = new DOMParser();
        const parsedHtml = parser.parseFromString(html, "text/html");
        return parsedHtml.body.textContent;
    }

    return (
        <div>
            <AdminNavbar/>
            <div className="list-header"><h1>User List</h1></div>
            <div className="event-list-form">
                <TableContainer component={Paper}>
                    <Table className="event-table" aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell>User ID</TableCell>
                                <TableCell align="right">Name</TableCell>
                                <TableCell align="right">Email</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {users.map((user) => (
                                <TableRow key={user.userId}>
                                    <TableCell component="th" scope="row">
                                        <a href={'/user/' + user.userId}>{user.userId}</a>
                                    </TableCell>
                                    <TableCell align="right">{user.name}</TableCell>
                                    <TableCell align="right">{cutHtmlTags(user.email)}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
        </div>
    );
}



