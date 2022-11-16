import Navbar from "../components/Navbar";
import React, {useState} from "react";
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