import moment from "moment";

export default function EventTile(props) {
    return (<a href={'/event/' + props.eventId} >
        <div className="event-tile">
        <div className="event-photo-tile" style={{background: props.logo}}>
        </div>
            <div>{moment.utc(props.startDate).local().startOf('seconds').fromNow()}</div>
        <div className="event-name-tile">{props.name}</div>
            <h4>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer eget neque eu ligula interdum pretium.</h4>
            <div className="information-bar">
                <div className="location-info"><h3>Kraków<h6>Location</h6></h3></div>
                <div className="price-info"><h3>70$<h6>Price</h6></h3></div>
                <div className="rating-info"><h3>4.4<h6>Rating</h6></h3></div>
            </div>
    </div>
    </a>)


}