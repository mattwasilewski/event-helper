
export default function EventTile(props) {
    return <div className="event-tile">
        <div className="event-photo-tile">
            <div className="event-rating-tile">
                <div className="star">★</div>
                <div className="rating">4.4</div>
            </div>
        </div>
        <div className="event-name-tile">{props.name}</div>
        <div className="event-location-tile">
            <span className="material-symbols-outlined location-symbol">location_on</span>
            <div className="location-text">{props.location}</div>
        </div>
    </div>


}