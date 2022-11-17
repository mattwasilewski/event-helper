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
            <Events sortBy="date" asc="descending"/>
            <h1>All events</h1>
            <div className="all-events-tools">
                    <div>
                        <div className="search-bar"><input type="text" id="search" placeholder="🔎︎  Search" name="search" multiple></input>
                        <select name="sort-by" id="sort-by" onChange={(event) => setSortBy(event.currentTarget.value)}>
                            <option selected disabled>Sort By</option>
                            <option value="name">name</option>
                            <option value="price">price</option>
                            <option value="category">category</option>
                        </select>
                        <select name="asc" id="asc" onChange={(event) => setAsc(event.currentTarget.value)}>
                            <option selected disabled>Order</option>
                            <option value="ascending">ascending</option>
                            <option value="descending">descending</option>
                        </select>
                    </div>
                </div>
            </div>
            <Events sortBy={sortBy} asc={asc}/>
            <h1>Recommended Events</h1>
            <RecommendedEvents/>
            <CalendarTile/>
            <SubscribePanel/>
            <Footer/>
        </div>
    )

}