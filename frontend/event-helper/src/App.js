import './css/App.css';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import MainPage from "./pages/MainPage";
import LoginPage from "./pages/LoginPage";
import AdminPage from "./pages/AdminPage";
import * as Sentry from "@sentry/react";
import { CaptureConsole } from '@sentry/integrations';
import AddEventPage from "./pages/AddEventPage";
import UserPage from "./pages/UserPage";
import EventPage from "./pages/EventPage";
import RegisterForm from "./components/RegisterForm";
import authSerivce from "./auth.serivce";
import EventChat from "./pages/EventChat";
import ForgotPasswordForm from "./components/ForgotPasswordForm";
import ResetPasswordForm from "./components/ResetPasswordForm";
import EventList from "./components/event/EventList";
import UserList from "./components/user/UserList";

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
    const isLoggedIn = authSerivce.getCurrentUser();
    let path = "#";
    if(isLoggedIn){
        const userDetails = authSerivce.parseJwt(isLoggedIn.value)
        path = '/user/'+userDetails.sub;
    }
    return (
        <div className="App">
            <Router>
                <Routes>
                    <Route path="/" element={<MainPage/>}/>
                    <Route path="/home" element={<MainPage/>}/>
                    <Route path="/login" element={<LoginPage/>}/>
                    <Route path="/register" element={<RegisterForm/>}/>
                    <Route path="/add-event" element={<AddEventPage/>}/>
                    <Route path="/admin" element={<AdminPage/>}/>
                    <Route path="/user" element={<UserPage/>}/>
                    <Route path="/event/:id" element={<EventPage/>}/>
                    <Route path="/chat" element={<EventChat/>}/>
                    <Route path="/forgot-password" element={<ForgotPasswordForm/>}/>
                    <Route path="/reset-password/:token" element={<ResetPasswordForm/>}/>
                    <Route path="/event/event-list" element={<EventList/>}/>
                    <Route path="/user/user-list" element={<UserList/>}/>
                </Routes>
            </Router>
        </div>
    );
}

export default App;
