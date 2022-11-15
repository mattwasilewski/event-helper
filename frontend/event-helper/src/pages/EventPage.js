import React,{useEffect,useState} from "react";
import logo from "../assets/logociemne.png";
import "../EventPage.css"
import switchMode from "../assets/dark-switch.png"
import eventDj from "../assets/dj.png"


import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import WelcomePage from "./WelcomePage";
import MainPage from "./MainPage";
import LoginPage from "./LoginPage";
import RegistrationForm from "../components/RegistrationForm";
import img from "../assets/login-img.png";

export default function EventPage() {

    return (
        <div id="event-page">
            <div id="logo">
                <img src={logo} alt="logo"/>
            </div>
            <div id="frame19">
                <div id="top-nav">
                    <p id="nav-home">Home</p>
                    <p id="nav-events">Events</p>
                    <p id="nav-community">Community</p>
                    <p id="nav-about-us">About us</p>
                    <p id="nav-login">Login</p>
                    <p id="nav-sign-up">Sign up</p>
                    <div id="switch-mode">
                        <img src={switchMode} alt=""/>
                    </div>
                </div>
                <p id="event-name">Event Name</p>
                <p id="event-description">Miejsce na Twój opis, nie wiem co napisać więc napiszę cokowolwiek bo i tak nie ma czasu na wymyślanie głupotek także wybaczcie ten mój piękny opisik</p>
            </div>
            <div id="destination">
                <img id="rectangle-14" src={eventDj} alt=""/>
                <div id="card-2">
                    <div id="rectangle-15">
                        <p>Location</p>
                    </div>
                </div>
            </div>
        </div>
    );

}