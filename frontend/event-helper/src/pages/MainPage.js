import Navbar from "../components/Navbar";
import {Events} from "../components/Events";
import React, {useState} from "react";
import RecommendedEvents from "../components/RecommendedEvents";
import CalendarTile from "../components/CalendarTile";
import SubscribePanel from "../components/SubscribePanel";
import Footer from "../components/Footer";

export default function MainPage() {

    const [sortBy, setSortBy] = useState("name");
    const [asc, setAsc] = useState("ascending");

    return (
        <div>
            <Navbar/>
            <h1>Newest Events</h1>
            <Events sortBy={sortBy} asc="ascending"/>
            <h1>All events</h1>
            <nav className="nav">
                <div>
                    <label htmlFor="name">Sort by: </label>
                    <select name="sort-by" id="sort-by" onChange={(event) => setSortBy(event.currentTarget.value)}>
                        <option value="name">name</option>
                        <option value="price">price</option>
                        <option value="category">category</option>
                    </select>
                    <select name="asc" id="asc" onChange={(event) => setAsc(event.currentTarget.value)}>
                        <option value="ascending">ascending</option>
                        <option value="descending">descending</option>
                    </select>
                </div>
            </nav>
            <Events sortBy={sortBy} asc={asc} />
            <h1>Recommended Events</h1>
            {/*<RecommendedEvents/>*/}
            <CalendarTile/>
            <SubscribePanel/>
            <Footer/>
        </div>
    )

}