import React, {useState,useEffect} from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import "swiper/css/pagination";
import "swiper/css/navigation";
import {Pagination, Navigation, Autoplay} from "swiper";
import EventTile from "./EventTile"
import imageDefault from "../../assets/logociemne.png";
export default function AllEvents() {

    const [events, setEvents] = useState([]);
    const [sortBy, setSortBy] = useState("name");
    const [asc, setAsc] = useState("ascending");
    const [phrase, setPhrase] = useState("");

    const getEvents = async () =>{
        let ascending = true;
        if (asc === "descending")  ascending = false;
        const response = await fetch(`http://localhost:3000/api/events/sort/${sortBy}&${ascending}&${phrase}&0&100`);
        const data = await response.json();
        setEvents(data);
    }

    useEffect(() => {
        getEvents().then(r => console.log(r))

    }, [sortBy, asc, phrase]);


    function setDefaultImage(event) {
        if(event.image && event.image.standard && event.image.standard !== "defaultUrl") {
            return <img src={event.image.standard}/>
        } else if (event.image && event.image.imageData) {
            return <img src={`data:image/jpeg;base64,${event.image.imageData}`}/>
        } else {
            return <img src={imageDefault}/>
        }
    }


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
        <div className="all-events">
            <Swiper
                slidesPerView={5}
                spaceBetween={-15}
                slidesPerGroup={5}
                loop={true}
                loopFillGroupWithBlank={true}
                autoplay={{
                    delay: 5000,
                    disableOnInteraction: false,
                }}
                pagination={{
                    clickable: true,
                }}
                navigation={true}
                modules={[Autoplay,Pagination, Navigation]}
                className="mySwiper"
            >
                <>  {events.map((event) => (
                    <SwiperSlide><EventTile name={event.name}
                                            location={event.location}
                                            eventId={event.eventId}
                                            logo={setDefaultImage(event)}
                                            startDate={event.startDate}
                                            eventType={event.eventType}
                                            description={event.description}
                    /></SwiperSlide>
                ))}</>
            </Swiper>
        </div></>)
}