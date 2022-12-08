import Navbar from "../components/utils/Navbar";
import React from "react";
import CreateEventForm from "../components/event/CreateEventForm";
import "../css/App.css"

export default function AddEventPage() {

    return (
        <div>
            <Navbar/>
                <CreateEventForm/>
        </div>
    )

}