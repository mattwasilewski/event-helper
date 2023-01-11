import './css/App.css';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import WelcomePage from "./pages/WelcomePage";
import MainPage from "./pages/MainPage";
import LoginPage from "./pages/LoginPage";
import AdminPage from "./pages/AdminPage";
import * as Sentry from "@sentry/react";
import { CaptureConsole } from '@sentry/integrations';
import AddEventPage from "./pages/AddEventPage";
import UserPage from "./pages/UserPage";
import EventPage2 from "./pages/EventPage2";
import RegisterForm2 from "./components/RegisterForm2";
import authSerivce from "./auth.serivce";
import ForgotPasswordForm from "./components/ForgotPasswordForm";
import ResetPasswordForm from "./components/ResetPasswordForm";

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
                    <Route path="/" element={<WelcomePage/>}/>
                    <Route path="/home" element={<MainPage/>}/>
                    <Route path="/login" element={<LoginPage/>}/>
                    <Route path="/register" element={<RegisterForm2/>}/>
                    <Route path="/add-event" element={<AddEventPage/>}/>
                    <Route path="/admin" element={<AdminPage/>}/>
                    <Route path="/user" element={<UserPage/>}/>
                    <Route path="/event/:id" element={<EventPage2/>}/>
                    <Route path="/forgot-password" element={<ForgotPasswordForm/>}/>
                    <Route path="/reset-password/:token" element={<ResetPasswordForm/>}/>
                </Routes>
            </Router>
        </div>
    );
}

export default App;
