import Navbar from "../components/Navbar";
import Events from "../components/Events";
import {Test} from "../components/Test";
import React, {useState} from "react";

export default function MainPage() {

    const [sortBy, setSortBy] = useState("name");

    return (
        <div>
            <Navbar/>
            <h1>Newest Events</h1>
            <Events sortBy={"nic xD"}/>
            <h1>Recommended Events</h1>
            <h1>All events</h1>
            <nav className="nav">
                <div>
                    <label htmlFor="name">Sort by: </label>
                    <select name="sort-by" id="sort-by" onChange={(event) => setSortBy(event.currentTarget.value)}>
                        <option value="name">name</option>
                        <option value="price">price</option>
                        <option value="category">category</option>
                    </select>
                </div>
            </nav>
            <Test sortBy={sortBy} />
        </div>
    )

}