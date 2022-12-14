import './css/App.css';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import WelcomePage from "./pages/WelcomePage";
import MainPage from "./pages/MainPage";
import LoginPage from "./pages/LoginPage";
import AdminPage from "./pages/AdminPage";
import * as Sentry from "@sentry/react";
import { CaptureConsole } from '@sentry/integrations';
import RegistrationForm from "./components/RegistrationForm";
import AddEventPage from "./pages/AddEventPage";
import EventPage from "./pages/EventPage";
import axios from "axios";
import ResetPasswordPage from "./pages/ResetPasswordPage";

Sentry.init({
    dsn: "https://1efe12e9375549e598bbf29b1b609468@o4504165382815744.ingest.sentry.io/4504165401100288",
    integrations: [
        new CaptureConsole({
            levels: ['error']
        })
    ],
    tracesSampleRate: 1.0,
});




function App() {

    return (
        <div className="App">
            <Router>
                <Routes>
                    <Route path="/" element={<WelcomePage/>}/>
                    <Route path="/home" element={<MainPage/>}/>
                    <Route path="/login" element={<LoginPage/>}/>
                    <Route path="/register" element={<RegistrationForm/>}/>
                    <Route path="/add-event" element={<AddEventPage/>}/>
                    <Route path="/admin" element={<AdminPage/>}/>
                    <Route path="/event/:id" element={<EventPage/>}/>
                    <Route path="/reset-password/:token" element={<ResetPasswordPage/>}/>
                </Routes>
            </Router>
        </div>
    );
}

export default App;
