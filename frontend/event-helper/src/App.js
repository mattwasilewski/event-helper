import './App.css';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import WelcomePage from "./pages/WelcomePage";
import MainPage from "./pages/MainPage";

function App() {
    return (
        <div className="App">
            <Router>
                <Routes>
                    <Route path="/" element={<WelcomePage/>}/>
                    <Route path="/home" element={<MainPage/>}/>
                </Routes>
            </Router>
        </div>
    );
}

export default App;
