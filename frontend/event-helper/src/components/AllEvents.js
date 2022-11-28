import {Events} from "./Events";
import React, {useState} from "react";

export default function AllEvents() {

    const [sortBy, setSortBy] = useState("name");
    const [asc, setAsc] = useState("ascending");
    const [phrase, setPhrase] = useState("");



    return (<>
        <div className="all-events-tools">
            <div>
                <div className="search-bar">
                    <input type="text" id="search" placeholder="🔎︎  Search" name="search" multiple
                        onChange={(e) => setPhrase(e.currentTarget.value)}></input>

                    <select name="sort-by" id="sort-by" onChange={(event) => setSortBy(event.currentTarget.value)}>
                        <option value="name">name</option>
                        <option value="price">price</option>
                        <option value="eventType">category</option>
                    </select>
                    <select name="asc" id="asc" onChange={(event) => setAsc(event.currentTarget.value)}>
                        <option selected disabled>Order</option>
                        <option value="ascending">ascending</option>
                        <option value="descending">descending</option>
                    </select>
                </div>
            </div>
        </div>
    <Events sortBy={sortBy} asc={asc} phrase={phrase}/>
    </>)
}