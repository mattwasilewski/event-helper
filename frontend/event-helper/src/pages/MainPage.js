import Navbar from "../components/Navbar";
import Events from "../components/Events";
import React from "react";

export default function MainPage() {

    let sortBy;

    const submitHandler = e => {
        e.preventDefault()
        sortBy = document.getElementById("sort-by").value
    }

    return (
        <div>
            <Navbar/>
            <h1>Newest Events</h1>
            <Events/>
            <h1>Recommended Events</h1>
            <h1>All events</h1>
            <nav className="nav">
                <form onSubmit={submitHandler}>
                    <div>
                        <label htmlFor="name">Sort by</label>
                        <select name="sort-by" id="sort-by">
                            <option value="name">name</option>
                            <option value="price">price</option>
                            <option value="category">category</option>
                        </select>
                        <input type="submit" value="SORT" />
                    </div>
                </form>
            </nav>
            <Events sortBy={sortBy} />
        </div>
    )

}