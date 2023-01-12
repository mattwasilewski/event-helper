import moment from "moment";
import "../../css/eventTile.css";

export default function EventTile(props) {

    const regex = /(<([^>]+)>)/ig;
    const result = props.description.replace(regex, '');

    return (
        <a href={'/event/' + props.eventId}>
            <div className="card">
                <div className="card-header">
                    <img src={props.logo} alt="rover"/>
                </div>
                <div className="card-body">
                    <span className={`tag ${props.eventType}`} id="event-type">{props.eventType}</span>
                    <h4 id="event-names">
                        {props.name}
                    </h4>
                    <p id="event-desc">
                        {result}
                    </p>
                    <div className="user">
                        <div className="user-info">
                            <h5 id="event-location">{props.location}</h5>
                            <small
                                id="event-date">{moment.utc(props.startDate).local().startOf('seconds').fromNow()}</small>
                        </div>
                    </div>
                </div>
            </div>
        </a>

    )


}