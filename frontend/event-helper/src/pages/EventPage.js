import React,{useEffect,useState} from "react";
import logo from "../assets/logociemne.png";
import "../EventPage.css"
import switchMode from "../assets/dark-switch.png"
import eventDj from "../assets/dj.png"
import img from "../assets/login-img.png";
import {useParams} from "react-router-dom";
import Navbar from "../components/Navbar";
import {Col, Container, Row, Card} from "react-bootstrap";
import RecommendedEvents from "../components/RecommendedEvents";
import Footer from "../components/Footer";

export default function EventPage() {

    let { id } = useParams()
    const [event, setEvent] = useState([]);

    useEffect(() => {
        getEvents().then(r => console.log(r))

    }, []);

    const getEvents = async () =>{
        const response = await fetch(`http://localhost:8080/api/events/${id}`, {
            method: 'GET',
        });
        const data = await response.json();
        setEvent(data);
    }

    const assignToEvent = async (e) => {
        e.preventDefault()
        console.log("Button działa")
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json; charset=UTF-8',
                'Access-Control-Allow-Origin': 'http://localhost:3000',
                'Access-Control-Allow-Credentials': 'true'},
            body: JSON.stringify({
                userId: id})
        }
        fetch('http://localhost:8080/api/events/assign-user-to-event', requestOptions)
            .then(response => console.log(response.status))
    }

    return (
            <div>
                <Navbar/>
                <Card  id="eventData">
                    <Card.Body>
                <Container>
                    <Row>
                        <Col><p id="eventHeader">{event.name}</p></Col>
                    </Row>
                    <Row>
                        <Col><img id="eventImg" src={eventDj} alt=""/></Col>
                        <Col>
                            <p id="eventText">{event.description}</p>
                        </Col>
                        <Col>
                            <p id="eventText">KOLEJNE DANE TYPU CZAS CENA ITP</p>
                        </Col>
                    </Row>
                </Container>
                    </Card.Body>
                </Card>
                <RecommendedEvents/>
                <Footer/>
        </div>
    );



}