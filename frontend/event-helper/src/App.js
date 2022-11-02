import './App.css';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import WelcomePage from "./pages/WelcomePage";

function App() {
    return (
        <div className="App">
            <Router>
                <Routes>
                    <Route path="/" element={<WelcomePage/>}/>
                </Routes>
            </Router>
        </div>
    );
}

export default App;
