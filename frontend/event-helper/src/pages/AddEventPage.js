import Navbar from "../components/Navbar";
import React from "react";
import CreateEventForm from "../components/CreateEventForm";
import "../App.css"

export default function AddEventPage() {

    return (
        <div>
            <Navbar/>
                <CreateEventForm/>
        </div>
    )

}